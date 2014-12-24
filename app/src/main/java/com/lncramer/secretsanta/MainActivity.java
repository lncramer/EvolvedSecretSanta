package com.lncramer.secretsanta;

import android.content.Intent;
import android.content.pm.PackageInstaller;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.firebase.client.Firebase;
import com.lncramer.secretsanta.services.IAddName;
import com.lncramer.secretsanta.services.IRetrieveNames;
import com.lncramer.secretsanta.services.NameAdder;
import com.lncramer.secretsanta.services.NameRetriever;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    public static final String EXTRA_NAMES = "com.lncramer.secretsanta.NAMES";
    private IAddName _nameAdder = new NameAdder();
    private IRetrieveNames _nameRetriever = new NameRetriever();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        _nameRetriever.setupNameListener();
        setContentView(R.layout.activity_main);
    }

    public void addName(View view) {
        EditText editText = (EditText) findViewById(R.id.name);
        String name = editText.getText().toString();
        editText.setText("");

        _nameAdder.addName(name);
    }

    public void beginButtonClick(View view) {
        Intent intent = new Intent(this, DrawNamesActivity.class);
        ArrayList<String> names = _nameRetriever.getNames();

        intent.putStringArrayListExtra(EXTRA_NAMES, names);
        startActivity(intent);
    }
}
