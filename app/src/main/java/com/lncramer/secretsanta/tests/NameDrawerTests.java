package com.lncramer.secretsanta.tests;

import android.test.InstrumentationTestCase;

import com.lncramer.secretsanta.services.NameDrawer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            add("C");
        }};

        String thePairing = _nameDrawer.getPairing(theDrawer, theNames, new HashMap<String, String>());

        assertTrue( (thePairing.equals("B")) || thePairing.equals("C") );
    }

    public void test_should_filter_out_already_drawn_names() throws Exception {
        String theDrawer = "C";
        List<String> theNames = new ArrayList<String>() {{
            add("A");
            add("B");
            add("C");
        }};
        Map<String, String> thePairings = new HashMap<String, String>() {{
            put("A", "B");
            put("B", "C");
        }};

        String thePairing = _nameDrawer.getPairing(theDrawer, theNames, thePairings);

        assertEquals(thePairing, "A");
    }
}
