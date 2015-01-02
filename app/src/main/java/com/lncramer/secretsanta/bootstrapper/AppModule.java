package com.lncramer.secretsanta.bootstrapper;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
* Created by Lucas on 1/1/2015.
*/
@Module(library=true)
public class AppModule {
    private final App app;

    public AppModule(App app) {
        this.app = app;
    }

    @Provides @Singleton @ForApplication public Context provideApplicationContext() {
        return app;
    }
}
