package com.nilasoftwares.rm.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nilasoftwares.rm.R;
import com.nilasoftwares.rm.model.LoginModel;
import com.nilasoftwares.rm.presenter.LoginPresenter;
import com.nilasoftwares.rm.view.LoginView;

/**
 * Created by alexvijayrajamalaraj on 12/12/17.
 */

/**
 * Login Activity to enable user to login through Facebook or Google
 *
 * @author alexvijayrajamalaraj
 */
public class LoginActivity extends Activity implements LoginView, View.OnClickListener{

    private LoginPresenter loginPresenter;
    private TextView tvLoginLoading;
    private RelativeLayout rlLoginLoading, rlLoginButtons;
    private ImageButton ibLoginFacebook, ibLoginGoogle;
    private ImageView ivLoginLoading;

    //Handler for loading animation on signing screen
    private boolean mStopHandler = true;
    private Handler mHandler;
    private Runnable mRunnable;

    private int stateAnimation = 1;

    private static final String TAG = "LOGIN_ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tvLoginLoading = (TextView) findViewById(R.id.tvLoginLoading);
        rlLoginLoading = (RelativeLayout) findViewById(R.id.rlLoginLoading);
        rlLoginButtons = (RelativeLayout) findViewById(R.id.rlLogin);
        ibLoginFacebook = (ImageButton) findViewById(R.id.ibLoginFacebook);
        ibLoginGoogle = (ImageButton) findViewById(R.id.ibLoginGoogle);
        ivLoginLoading = (ImageView) findViewById(R.id.ivLoginLoading);

        ibLoginFacebook.setOnClickListener(this);
        ibLoginGoogle.setOnClickListener(this);

        loginPresenter = new LoginModel(LoginActivity.this, this);

        //initialize handler
        initHandler();

    }

    @Override
    public void showSigningIn() {
        Log.d(TAG, "Signing in");

        rlLoginLoading.setVisibility(View.VISIBLE);
        rlLoginButtons.setVisibility(View.INVISIBLE);
        tvLoginLoading.setText(getResources().getString(R.string.login_signin));

        //start Loading Animation
        startAnimation();

    }

    @Override
    public void showSigningOut() {
        Log.d(TAG, "Signing out");

        rlLoginLoading.setVisibility(View.VISIBLE);
        rlLoginButtons.setVisibility(View.INVISIBLE);
        tvLoginLoading.setText(getResources().getString(R.string.login_signout));

        //start Loading Animation
        startAnimation();

    }

    @Override
    public void showLoginFacebookFailed() {
        Log.d(TAG, "Facebook Login failed");

        stopAnimation();

        rlLoginLoading.setVisibility(View.INVISIBLE);
        rlLoginButtons.setVisibility(View.VISIBLE);

    }

    @Override
    public void showLoginGoogleFailed() {
        Log.d(TAG, "Google Login failed");

        stopAnimation();

        rlLoginLoading.setVisibility(View.INVISIBLE);
        rlLoginButtons.setVisibility(View.VISIBLE);

    }

    @Override
    public void showLoginSuccess() {
        Log.d(TAG, "Login Success");

        stopAnimation();

        openNextActivity();

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.ibLoginFacebook:
                loginPresenter.loginFacebook();
                break;

            case R.id.ibLoginGoogle:
                loginPresenter.loginGoogle();
                break;

            default:
                break;

        }

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
     * method start the Signin Animation
     *
     */
    private void startAnimation(){
        mStopHandler = false;
        mHandler.post(mRunnable);
    }

    /**
     * method to stop start the Signin Animation
     *
     */
    private void stopAnimation(){
        mStopHandler = true;
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
                ivLoginLoading.setImageResource(R.drawable.splash_loading_1);
                break;
            case 2:
                ivLoginLoading.setImageResource(R.drawable.splash_loading_2);
                break;
            case 3:
                ivLoginLoading.setImageResource(R.drawable.splash_loading_3);
                break;
            case 4:
                ivLoginLoading.setImageResource(R.drawable.splash_loading_4);
                break;
            case 5:
                ivLoginLoading.setImageResource(R.drawable.splash_loading_5);
                break;
            case 6:
                ivLoginLoading.setImageResource(R.drawable.splash_loading_4);
                break;
            case 7:
                ivLoginLoading.setImageResource(R.drawable.splash_loading_3);
                break;
            case 8:
                ivLoginLoading.setImageResource(R.drawable.splash_loading_2);
                break;
        }

        stateAnimation++;

    }

    /**
     * method to open the next activity accordingly
     *
     */
    private void openNextActivity(){

        Intent myIntent = new Intent(LoginActivity.this, HomeActivity.class);
        LoginActivity.this.startActivity(myIntent);

    }

}
