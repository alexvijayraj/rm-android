package com.nilasoftwares.rm.base;

import android.app.Application;

import com.nilasoftwares.rm.di.ActivityInjector;

import javax.inject.Inject;

/**
 * Created by alexvijayrajamalaraj on 12/11/17.
 */

public class MyApplication extends Application {

    @Inject
    ActivityInjector activityInjector;

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ActivityInjector getActivityInjector() {
        return activityInjector;
    }
}
