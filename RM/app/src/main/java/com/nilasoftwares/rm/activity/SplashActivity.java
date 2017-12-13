package com.nilasoftwares.rm.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

import com.nilasoftwares.rm.R;
import com.nilasoftwares.rm.model.SplashModel;
import com.nilasoftwares.rm.presenter.SplashPresenter;
import com.nilasoftwares.rm.view.SplashView;

/**
 * Splash Activity to load
 *
 * @author alexvijayrajamalaraj
 */

public class SplashActivity extends Activity implements SplashView {

    private SplashPresenter splashPresenter;

    //Handler for loading animation on splash screen
    private boolean mStopHandler = false;
    private Handler mHandler;
    private Runnable mRunnable;

    private ImageView ivSplashLoading;

    private int stateAnimation = 1;

    private static final String TAG = "SPLASH_ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ivSplashLoading = (ImageView) findViewById(R.id.ivSplashLoading);

        splashPresenter = new SplashModel(SplashActivity.this);

        //initialize handler
        initHandler();

        //start Loading Animation
        startAnimation();
    }

    @Override
    public void showLoading() {
        Log.d(TAG, "Splash show loading");

    }

    @Override
    public void dismissLoading() {
        Log.d(TAG, "Splash dismiss loading");

        mStopHandler = true;

        //open the next corresponding activity
        openNextActivity();

    }

    /**
     * method to initialize the handler for the Loading Animation
     *
     */
    private void initHandler(){
        mHandler = new Handler();

        mRunnable = new Runnable() {
            @Override
            public void run() {
                if (!mStopHandler) {
                    updateAnimation();
                    mHandler.postDelayed(this, 200);
                }
            }
        };

    }

    /**
     * method start the Loading Animation
     *
     */
    private void startAnimation(){
        mHandler.post(mRunnable);
    }

    /**
     * method to update the state of the animation
     *
     */
    private void updateAnimation(){

        if(stateAnimation >= 9 || stateAnimation <= 0){
            stateAnimation = 1;
        }

        switch (stateAnimation){
            case 1:
                ivSplashLoading.setImageResource(R.drawable.splash_loading_1);
                break;
            case 2:
                ivSplashLoading.setImageResource(R.drawable.splash_loading_2);
                break;
            case 3:
                ivSplashLoading.setImageResource(R.drawable.splash_loading_3);
                break;
            case 4:
                ivSplashLoading.setImageResource(R.drawable.splash_loading_4);
                break;
            case 5:
                ivSplashLoading.setImageResource(R.drawable.splash_loading_5);
                break;
            case 6:
                ivSplashLoading.setImageResource(R.drawable.splash_loading_4);
                break;
            case 7:
                ivSplashLoading.setImageResource(R.drawable.splash_loading_3);
                break;
            case 8:
                ivSplashLoading.setImageResource(R.drawable.splash_loading_2);
                break;
        }

        stateAnimation++;

    }

    /**
     * method to open the next activity accordingly
     *
     */
    private void openNextActivity(){

        Intent myIntent = new Intent(SplashActivity.this, LoginActivity.class);
        SplashActivity.this.startActivity(myIntent);

    }

}
