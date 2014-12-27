package com.lncramer.secretsanta;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.lncramer.secretsanta.services.ICreateRow;
import com.lncramer.secretsanta.services.RowCreator;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    public static final String EXTRA_NAMES = "com.lncramer.secretsanta.NAMES";

    private ICreateRow _nameRowCreator = new RowCreator();
    private static ArrayList<String> _names = new ArrayList();

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

        removeName(row);
        table.removeView(row);
    }

    private static void removeName(TableRow row) {
        TextView textView = (TextView) row.getChildAt(0);
        String name = textView.getText().toString();

        _names.remove(name);
    }

    private void addToRowsTable(TableRow row) {
        TableLayout table = (TableLayout) findViewById(R.id.names_table);
        table.addView(row);
    }
}
