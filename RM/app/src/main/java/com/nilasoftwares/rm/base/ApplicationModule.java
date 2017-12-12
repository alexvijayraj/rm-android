package com.nilasoftwares.rm.base;

import android.app.Application;
import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alexvijayrajamalaraj on 12/11/17.
 */

@Module
public class ApplicationModule {

    private final Application application;

    ApplicationModule(Application application){
        this.application = application;

    }

    @Provides
    Context provideApplicationContext(){
        return application;
    }
}
