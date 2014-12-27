package com.lncramer.secretsanta;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;


public class DrawNamesActivity extends ActionBarActivity {

    private List<String> _names;
    private Map<String, String> _pairings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeNames();

        setContentView(R.layout.activity_draw_names);

        String currentDrawer = getNextDrawer();
        updateName(R.id.current_drawer, currentDrawer);
    }

    public void drawName(View view) {
        String currentDrawer = getCurrentDrawer();
        String name = getPairing(currentDrawer);

        _pairings.put(currentDrawer, name);

        updateName(R.id.drawn_name, name);
        updateButton(R.id.confirm_button, ButtonOptions.Show);
        updateButton(R.id.draw_button, ButtonOptions.Hide);
    }

    public void confirmButtonClick(View view) {
        // Clear last drawn name
        updateName(R.id.drawn_name, "");

        if (!namesAreRemaining()) {
            updateName(R.id.current_drawer, "Everyone has a santa!");
            updateButton(R.id.confirm_button, ButtonOptions.Hide);
            updateButton(R.id.draw_button, ButtonOptions.Hide);
            return;
        }

        // Update drawer
        String newDrawer = getNextDrawer();
        updateName(R.id.current_drawer, newDrawer);

        // Update buttons
        updateButton(R.id.confirm_button, ButtonOptions.Hide);
        updateButton(R.id.draw_button, ButtonOptions.Show);
    }

    private void updateName(int id, String currentDrawer) {
        TextView textView = (TextView) findViewById(id);
        textView.setText(currentDrawer);
    }

    private void initializeNames() {
        Intent intent = getIntent();
        List<String> allNames = intent.getStringArrayListExtra(MainActivity.EXTRA_NAMES);

        _names = allNames;
        _pairings = new HashMap<>();
    }

    private void updateButton(int id, ButtonOptions option) {
        Button button = (Button) findViewById(id);

        if (option == ButtonOptions.Show) {
            button.setVisibility(View.VISIBLE);
        }
        else if (option == ButtonOptions.Hide) {
            button.setVisibility(View.INVISIBLE);
        }
    }

    private boolean namesAreRemaining() {
        return _pairings.size() != _names.size();
    }

    private String getCurrentDrawer() {
        TextView textView = (TextView) findViewById(R.id.current_drawer);
        return textView.getText().toString();
    }

    private String getPairing(String currentDrawer) {
        // Draw a name from _names that is not the same as currentDrawer or exists as a value in map
        Collection<String> values = _pairings.values();

        // Filter values out of names
        List<String> names = makeCopyOfList(_names);
        names.removeAll(values);
        names.remove(currentDrawer);

        // Get random name
        int randomIndex = new Random().nextInt(names.size());
        return names.get(randomIndex);
    }

    private String getNextDrawer() {
        // Draw a name from _names that doesn't exist as a key in the map (otherwise already drawn)
        Set<String> keys = _pairings.keySet();

        // Filter keys out of names
        List<String> names = makeCopyOfList(_names);
        names.removeAll(keys);

        // Get random name
        int randomIndex = new Random().nextInt(names.size());
        return names.get(randomIndex);
    }

    private List<String> makeCopyOfList(List<String> list) {
        List<String> listCopy = new ArrayList<>();

        for (String str : list) {
            listCopy.add(str);
        }

        return listCopy;
    }
}
