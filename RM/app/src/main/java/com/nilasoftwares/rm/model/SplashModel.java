package com.nilasoftwares.rm.model;

import com.nilasoftwares.rm.constants.Constants;
import com.nilasoftwares.rm.presenter.SplashPresenter;
import com.nilasoftwares.rm.view.SplashView;

import android.os.Handler;

/**
 * Created by alexvijayrajamalaraj on 12/12/17.
 */

/**
 * Model class of the Splash Activity
 *
 *  - Dismisses activity after timeout
 *
 * @author alexvijayrajamalaraj
 */
public class SplashModel implements SplashPresenter {

    private SplashView splashView;

    private android.os.Handler mHandler;
    private Runnable mRunnable;

    public SplashModel(SplashView splashView){

        this.splashView = splashView;

        //initialize handler
        initHandler();
    }

    @Override
    public void dismissLoading() {

        splashView.dismissLoading();

    }

    /**
     * method to initialize the handler for the splash activity dismiss timeout
     *
     */
    private void initHandler(){
        mHandler = new Handler();

        mRunnable = new Runnable() {
            @Override
            public void run() {
                splashView.dismissLoading();
            }
        };

        mHandler.postDelayed(mRunnable, Constants.SPLASH_ACTIVITY_TIMEOUT);

    }
}
