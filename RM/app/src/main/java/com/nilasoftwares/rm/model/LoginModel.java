package com.nilasoftwares.rm.model;

import android.content.Context;
import android.os.Handler;

import com.nilasoftwares.rm.presenter.LoginPresenter;
import com.nilasoftwares.rm.view.LoginView;

/**
 * Created by alexvijayrajamalaraj on 12/12/17.
 */

/**
 * Model class of the Login Activity
 *
 *  - Logs in user to Facebook/ Google
 *
 * @author alexvijayrajamalaraj
 */
public class LoginModel implements LoginPresenter {

    private LoginView loginView;
    private Context context;

    private Handler mHandler;
    private Runnable mRunnable;

    public LoginModel(LoginView loginView, Context context){

        this.loginView = loginView;
        this.context = context;

        //initialize handler
        initHandler();

    }

    @Override
    public void loginFacebook() {

        loginView.showSigningIn();
        mHandler.postDelayed(mRunnable, 5000);

    }

    @Override
    public void loginGoogle() {

        loginView.showSigningIn();
        mHandler.postDelayed(mRunnable, 5000);

    }

    /**
     * method to initialize the handler for the login success timeout
     *
     */
    private void initHandler(){
        mHandler = new Handler();

        mRunnable = new Runnable() {
            @Override
            public void run() {
                loginView.showLoginSuccess();
            }
        };

    }
}
