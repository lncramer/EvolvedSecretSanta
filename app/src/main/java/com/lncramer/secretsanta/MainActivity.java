package com.lncramer.secretsanta;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.lncramer.secretsanta.services.ICreateNameRow;
import com.lncramer.secretsanta.services.NameRowCreator;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    public static final String EXTRA_NAMES = "com.lncramer.secretsanta.NAMES";

    private ICreateNameRow _nameRowCreator = new NameRowCreator();
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

        TableRow row = _nameRowCreator.createRow(name, this);
        addToRowsTable(row);
    }

    public void beginButtonClick(View view) {
        Intent intent = new Intent(this, DrawNamesActivity.class);

        intent.putStringArrayListExtra(EXTRA_NAMES, _names);
        startActivity(intent);
    }

    public static void removeRow(View view) {
        TableRow row = (TableRow) view.getParent();
        TableLayout table = (TableLayout) row.getParent();

        table.removeView(row);
    }

    private void addToRowsTable(TableRow row) {
        TableLayout table = (TableLayout) findViewById(R.id.names_table);
        table.addView(row);
    }
}
