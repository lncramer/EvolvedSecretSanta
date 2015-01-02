package com.lncramer.secretsanta.bootstrapper;

import com.lncramer.secretsanta.activities.DrawNamesActivity;
import com.lncramer.secretsanta.services.IDrawNames;
import com.lncramer.secretsanta.services.NameDrawer;

import dagger.Module;
import dagger.Provides;

@Module(
        injects = DrawNamesActivity.class,
        complete = false
)
public class DrawNamesActivityModule {
    @Provides
    IDrawNames provideNameDrawer() {
        return new NameDrawer();
    }
}
