package com.nilasoftwares.rm.base;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by alexvijayrajamalaraj on 12/11/17.
 */

@Singleton
@Component(modules = {
        ApplicationModule.class,
})
public interface ApplicationComponent {
}
