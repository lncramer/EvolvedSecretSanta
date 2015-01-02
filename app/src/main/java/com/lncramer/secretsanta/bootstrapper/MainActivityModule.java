package com.lncramer.secretsanta.bootstrapper;

import com.lncramer.secretsanta.activities.MainActivity;
import com.lncramer.secretsanta.services.ICreateRow;
import com.lncramer.secretsanta.services.RowCreator;

import dagger.Module;
import dagger.Provides;

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
