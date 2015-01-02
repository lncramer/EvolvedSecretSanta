package com.lncramer.secretsanta;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.lncramer.secretsanta.bootstrapper.App;
import com.lncramer.secretsanta.services.IDrawNames;
import com.lncramer.secretsanta.services.NameDrawer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;


public class DrawNamesActivity extends ActionBarActivity {
    @Inject IDrawNames _nameDrawer;

    private List<String> _names;
    private Map<String, String> _pairings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((App) getApplication()).inject(this);

        initializeNames();

        setContentView(R.layout.activity_draw_names);

        String currentDrawer = _nameDrawer.getNextDrawer(_names, _pairings);
        updateName(R.id.current_drawer, currentDrawer);
    }

    public void drawName(View view) {
        String currentDrawer = getCurrentDrawer();
        String name = _nameDrawer.getPairing(currentDrawer, _names, _pairings);

        _pairings.put(currentDrawer, name);

        updateName(R.id.drawn_name, name);
        updateButton(ButtonOptions.OK);
    }

    public void confirmButtonClick(View view) {
        // Clear last drawn name
        updateName(R.id.drawn_name, "");

        if (!namesAreRemaining()) {
            initializeEndState();
            return;
        }

        String newDrawer = _nameDrawer.getNextDrawer(_names, _pairings);
        updateName(R.id.current_drawer, newDrawer);
        updateButton(ButtonOptions.Draw);
    }

    private void updateButton(ButtonOptions option) {
        Button button = (Button) findViewById(R.id.button);

        switch (option) {
            case OK:
                button.setText("OK");
                button.setBackgroundColor(Color.parseColor("#7CFC00"));
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        confirmButtonClick(v);
                    }
                });
                break;
            case Draw:
                button.setText("DRAW");
                button.setBackgroundColor(Color.parseColor("#ADD8E6"));
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        drawName(v);
                    }
                });
                break;
            case Hide:
                button.setVisibility(View.INVISIBLE);
                break;
        }
    }

    private void initializeEndState() {
        updateName(R.id.current_drawer, "Happy Holidays!");
        updateButton(ButtonOptions.Hide);
    }

    private void updateName(int id, String currentDrawer) {
        TextView textView = (TextView) findViewById(id);
        textView.setText(currentDrawer);
        textView.startAnimation(AnimationUtils.loadAnimation(this, R.anim.abc_fade_in));
    }

    private void initializeNames() {
        Intent intent = getIntent();
        List<String> allNames = intent.getStringArrayListExtra(MainActivity.EXTRA_NAMES);

        _names = allNames;
        _pairings = new HashMap<>();
    }

    private boolean namesAreRemaining() {
        return _pairings.size() != _names.size();
    }

    private String getCurrentDrawer() {
        TextView textView = (TextView) findViewById(R.id.current_drawer);
        return textView.getText().toString();
    }
}
