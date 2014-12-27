package com.lncramer.secretsanta.services;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.lncramer.secretsanta.MainActivity;
import com.lncramer.secretsanta.R;

/**
 * Created by Lucas on 12/26/2014.
 */
public class RowCreator implements ICreateRow {
    @Override
    public TableRow createRow(String name, Context context) {
        TableRow row = new TableRow(context);
        row.setBaselineAligned(true);
        row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                                                      TableRow.LayoutParams.WRAP_CONTENT,
                                                      1f));

        EditText textView = createTextView(name, context);
        Button buttonView = createRemoveButton(context);

        addViewsToRow(row, textView, buttonView);

        return row;
    }

    private EditText createTextView(String text, Context context) {
        EditText textView = new EditText(context);

        textView.setText(text);
        textView.setBackgroundResource(R.drawable.edit_text);
        textView.setGravity(Gravity.CENTER);
        textView.setKeyListener(null);

        return textView;
    }

    private void addViewsToRow(TableRow row, TextView nameView, Button buttonView) {
        row.addView(nameView, 0);
        row.addView(buttonView, 1);
    }

    private Button createRemoveButton(Context context) {
        Button buttonView = new Button(context);
        TableRow.LayoutParams lp = new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 1f);
        lp.setMargins(30, 10, 0, 0);

        buttonView.setText("REMOVE");
        buttonView.setLayoutParams(lp);
//        buttonView.setGravity(Gravity.CENTER);
//        buttonView.getBackground().setColorFilter(Color.parseColor("#FF3333"), PorterDuff.Mode.LIGHTEN);
        buttonView.setBackgroundColor(Color.parseColor("#FF3333"));
        buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.removeRow(view);
            }
        });

        return buttonView;
    }
}
