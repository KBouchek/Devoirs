package com.example.k.devoirs;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by k on 25/03/2016.
 */
public class DevoirCursorAdapter extends CursorAdapter {
    public DevoirCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_todo, parent, false);
    }
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find fields to populate in inflated template
        TextView laclasse = (TextView) view.findViewById(R.id.classexml);
        TextView datepour = (TextView) view.findViewById(R.id.datexml);
        TextView letravail = (TextView) view.findViewById(R.id.travailxml);
        TextView datedu = (TextView) view.findViewById(R.id.dateduxml);
        // Extract properties from cursor
        String classex = cursor.getString(cursor.getColumnIndexOrThrow("classe"));
        String dux = cursor.getString(cursor.getColumnIndexOrThrow("du"));
        String pourx = cursor.getString(cursor.getColumnIndexOrThrow("pour"));
        String travailx = cursor.getString(cursor.getColumnIndexOrThrow("travail"));
        // Populate fields with extracted properties
        laclasse.setText(classex);
        datedu.setText(dux);
        datepour.setText("Pour le "+pourx);
        letravail.setText(travailx);
    }


}
