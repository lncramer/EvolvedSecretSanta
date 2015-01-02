package com.lncramer.secretsanta.bootstrapper;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

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
