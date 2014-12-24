package com.lncramer.secretsanta.services;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lucas on 12/22/2014.
 */
public interface IRetrieveNames {
    void setupNameListener();
    ArrayList<String> getNames();
}
