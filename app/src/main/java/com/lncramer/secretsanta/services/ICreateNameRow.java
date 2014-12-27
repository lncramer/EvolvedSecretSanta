package com.lncramer.secretsanta.services;

import android.content.Context;
import android.widget.TableRow;

/**
 * Created by Lucas on 12/26/2014.
 */
public interface ICreateNameRow {
    TableRow createRow(String name, Context context);
}
