package com.nilasoftwares.rm.base;

import android.app.Activity;

import com.nilasoftwares.rm.home.MainActivity;
import com.nilasoftwares.rm.home.MainActivityComponent;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

/**
 * Created by alexvijayrajamalaraj on 12/11/17.
 */

@Module(subcomponents ={
        MainActivityComponent.class,
})
public abstract class ActivityBindingModule {

    @Binds
    @IntoMap
    @ActivityKey(MainActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> provideMainActivityInjector(MainActivityComponent.Builder builder);

}
