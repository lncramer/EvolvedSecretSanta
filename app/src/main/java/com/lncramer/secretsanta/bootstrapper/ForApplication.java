package com.lncramer.secretsanta.bootstrapper;

import java.lang.annotation.Retention;
import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Lucas on 1/1/2015.
 */
@Qualifier @Retention(RUNTIME)
public @interface ForApplication {
}
