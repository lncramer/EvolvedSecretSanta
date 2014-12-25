package com.lncramer.secretsanta;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    public static final String EXTRA_NAMES = "com.lncramer.secretsanta.NAMES";

    private ArrayList<String> _names = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void addName(View view) {
        EditText editText = (EditText) findViewById(R.id.name);
        String name = editText.getText().toString();
        editText.setText("");

        _names.add(name);
    }

    public void beginButtonClick(View view) {
        Intent intent = new Intent(this, DrawNamesActivity.class);

        intent.putStringArrayListExtra(EXTRA_NAMES, _names);
        startActivity(intent);
    }
}
