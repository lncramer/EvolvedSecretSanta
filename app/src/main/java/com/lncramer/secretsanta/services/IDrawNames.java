package com.lncramer.secretsanta.services;

import java.util.List;
import java.util.Map;

public interface IDrawNames {
    String getNextDrawer(List<String> names, Map<String, String> pairings);
    String getPairing(String drawer, List<String> names, Map<String, String> pairings);
}
