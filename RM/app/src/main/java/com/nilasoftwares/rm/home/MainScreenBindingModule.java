package com.nilasoftwares.rm.home;

import com.bluelinelabs.conductor.Controller;
import com.nilasoftwares.rm.di.ControllerKey;
import com.nilasoftwares.rm.trending.TrendingReposComponent;
import com.nilasoftwares.rm.trending.TrendingReposController;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {
        TrendingReposComponent.class,
})
public abstract class MainScreenBindingModule {

    @Binds
    @IntoMap
    @ControllerKey(TrendingReposController.class)
    abstract AndroidInjector.Factory<? extends Controller> bindTrendingReposInjector(TrendingReposComponent.Builder builder);
}
