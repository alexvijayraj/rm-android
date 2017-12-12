package com.nilasoftwares.rm.base;

import android.app.Application;

/**
 * Created by alexvijayrajamalaraj on 12/11/17.
 */

public class MyApplication extends Application {

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }
}
