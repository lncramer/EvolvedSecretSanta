package com.lncramer.secretsanta.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class NameDrawer implements IDrawNames {
    @Override
    public String getNextDrawer(List<String> names,
                                Map<String, String> pairings)
    {
        // Draw a name from _names that doesn't exist as a key in the map (otherwise already drawn)
        Set<String> keys = pairings.keySet();

        // Filter keys out of names
        List<String> namesCopy = makeCopyOfList(names);
        namesCopy.removeAll(keys);

        // Get random name
        int randomIndex = new Random().nextInt(namesCopy.size());
        return namesCopy.get(randomIndex);
    }

    @Override
    public String getPairing(String drawer,
                             List<String> names,
                             Map<String, String> pairings)
    {
        // Draw a name from _names that is not the same as currentDrawer or exists as a value in map
        Collection<String> values = pairings.values();

        // Filter values out of names
        List<String> namesCopy = makeCopyOfList(names);
        namesCopy.removeAll(values);
        namesCopy.remove(drawer);

        // Get random name
        int randomIndex = new Random().nextInt(namesCopy.size());
        String drawnName = namesCopy.get(randomIndex);

        if (oneNameIsRemaining(namesCopy) || !willCreateACycle(pairings, drawer, drawnName)) {
            return drawnName;
        }
        return getPairing(drawer, names, pairings);
    }

    private boolean willCreateACycle(Map<String, String> pairings, String drawer, String drawnName) {
        String currentName = drawnName;

        while (pairings.containsKey(currentName)) {
            if (pairings.get(currentName).equals(drawer))
                return true;
            currentName = pairings.get(currentName);
        }

        return false;
    }

    private boolean oneNameIsRemaining(List<String> namesCopy) {
        return namesCopy.size() == 1;
    }

    private List<String> makeCopyOfList(List<String> list) {
        List<String> listCopy = new ArrayList<>();

        for (String str : list) {
            listCopy.add(str);
        }

        return listCopy;
    }
}
