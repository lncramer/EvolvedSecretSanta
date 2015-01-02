package com.lncramer.secretsanta.tests;

import android.test.InstrumentationTestCase;

import com.lncramer.secretsanta.services.NameDrawer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Lucas on 1/2/2015.
 */
public class NameDrawerTests extends InstrumentationTestCase {
    private NameDrawer _nameDrawer;

    public NameDrawerTests() {
        _nameDrawer = new NameDrawer();
    }

    public void test_should_get_next_drawer() throws Exception {
        List<String> theNames = new ArrayList<String>() {{
            add("A");
            add("B");
        }};

        String theNextDrawer = _nameDrawer.getNextDrawer(theNames, new HashMap<String, String>());

        assertTrue(theNames.contains(theNextDrawer));
    }

    public void test_should_get_correct_pairing() throws Exception {
        String theDrawer = "A";
        List<String> theNames = new ArrayList<String>() {{
            add("A");
            add("B");
        }};

        String thePairing = _nameDrawer.getPairing(theDrawer, theNames, new HashMap<String, String>());

        assertEquals(thePairing, "B");
    }
}
