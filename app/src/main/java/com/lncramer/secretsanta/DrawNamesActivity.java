package com.lncramer.secretsanta;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.Random;


public class DrawNamesActivity extends ActionBarActivity {

    private List<String> _names;
    private List<String> _drawers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeNames();

        setContentView(R.layout.activity_draw_names);

        String currentDrawer = getRandomName(_drawers);
        updateName(currentDrawer, R.id.current_drawer);
    }

    private void updateName(String currentDrawer, int id) {
        TextView textView = (TextView) findViewById(id);
        textView.setText(currentDrawer);
    }

    private void initializeNames() {
        Intent intent = getIntent();
        List<String> allNames = intent.getStringArrayListExtra(MainActivity.EXTRA_NAMES);

        _names = allNames;
        _drawers = allNames;
    }

    public void drawName(View view) {
        String name = getRandomName(_names);

        updateName(name, R.id.drawn_name);
        updateConfirmButton(R.id.confirm_button, ButtonOptions.Show);
        updateConfirmButton(R.id.draw_button, ButtonOptions.Hide);
    }

    private void updateConfirmButton(int id, ButtonOptions option) {
        Button button = (Button) findViewById(id);

        if (option == ButtonOptions.Show) {
            button.setVisibility(View.VISIBLE);
        }
        else if (option == ButtonOptions.Hide) {
            button.setVisibility(View.INVISIBLE);
        }
    }

    public void clearNameAndUpdateDrawer(View view) {
        // Clear last drawn name
        updateName("", R.id.drawn_name);

        // Update drawer
        String newDrawer = getRandomName(_drawers);
        updateName(newDrawer, R.id.current_drawer);

        // Update button
        updateConfirmButton(R.id.confirm_button, ButtonOptions.Hide);
        updateConfirmButton(R.id.draw_button, ButtonOptions.Show);
    }

    private String getRandomName(List<String> names) {
        int numberOfNames = names.size();

        if (namesAreRemaining(numberOfNames)) {
            int randomIndex = new Random().nextInt(numberOfNames);
            String name = names.get(randomIndex);
            names.remove(randomIndex);

            return name;
        }

        updateConfirmButton(R.id.draw_button, ButtonOptions.Hide);
        updateConfirmButton(R.id.confirm_button, ButtonOptions.Hide);
        updateName("", R.id.current_drawer);
        return "Everyone has a secret santa!";
    }

    private boolean namesAreRemaining(int numberOfNames) {
        return numberOfNames > 0;
    }
}
