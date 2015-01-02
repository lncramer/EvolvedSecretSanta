package com.lncramer.secretsanta.bootstrapper;

import android.app.Application;

import java.util.Arrays;
import java.util.List;

import dagger.ObjectGraph;

/**
 * Created by Lucas on 1/1/2015.
 */
public class App extends Application {
    private ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();

        objectGraph = ObjectGraph.create(getModules().toArray());
    }

    public List<Object> getModules() {
        return Arrays.asList(
                new AppModule(this),
                new MainActivityModule(),
                new DrawNamesActivityModule()
        );
    }

    public void inject(Object object) {
        objectGraph.inject(object);
    }
}
