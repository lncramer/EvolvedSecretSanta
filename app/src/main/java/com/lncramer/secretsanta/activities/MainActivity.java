package com.lncramer.secretsanta.activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.lncramer.secretsanta.R;
import com.lncramer.secretsanta.activities.DrawNamesActivity;
import com.lncramer.secretsanta.bootstrapper.App;
import com.lncramer.secretsanta.services.ICreateRow;

import java.util.ArrayList;

import javax.inject.Inject;

public class MainActivity extends ActionBarActivity {
    @Inject ICreateRow _rowCreator;

    public static final String EXTRA_NAMES = "com.lncramer.secretsanta.NAMES";
    private static ArrayList<String> _names = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((App) getApplication()).inject(this);
    }

    public void addName(View view) {
        EditText editText = (EditText) findViewById(R.id.name);
        String name = editText.getText().toString().trim();

        Toast toast = validateName(name);
        if (toast != null) {
            toast.show();
            return;
        }

        editText.setText("");
        _names.add(name);

        TableRow row = _rowCreator.createRow(name, this);
        addToRowsTable(row);
    }

    public void beginButtonClick(View view) {
        if (_names.size() < 2) {
            Toast toast = Toast.makeText(this, "Add at least two names before starting.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

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

    private Toast validateName(String name) {
        Toast toast = null;

        if (name.isEmpty()) {
            toast = Toast.makeText(this, "Name cannot be empty.", Toast.LENGTH_SHORT);
        }
        else if (_names.contains(name)) {
            toast = Toast.makeText(this, name + " has already been added.", Toast.LENGTH_SHORT);
        }

        return toast;
    }
}
