package com.lncramer.secretsanta.bootstrapper;

import com.lncramer.secretsanta.activities.MainActivity;
import com.lncramer.secretsanta.services.ICreateRow;
import com.lncramer.secretsanta.services.RowCreator;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Lucas on 1/1/2015.
 */
@Module(
        injects = MainActivity.class,
        complete = false
)
public class MainActivityModule {
    @Provides
    ICreateRow provideRowCreator() {
        return new RowCreator();
    }
}
