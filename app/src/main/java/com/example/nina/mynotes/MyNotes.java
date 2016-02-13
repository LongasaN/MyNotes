package com.example.nina.mynotes;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by Nina Longasa on 2/13/2016.
 */

    // This class is the sub class of the CursorAdapter
public class MyNotes extends CursorAdapter {

    public MyNotes (Context context, Cursor cursor, int flags){
        // Calls the super class, the Cursor Adapter
        super (context, cursor, flags);
    }

    @Override //This inflates the MyNotes Custom Row (li_notes)
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.li_notes, parent, false);
    }

    @Override //Bind data in the text view in the Custom Row (li_notes)
    public void bindView(View view, Context context, Cursor cursor) {
        // Get the Title of the notes to the Text view
        ((TextView) view.findViewById(R.id.titleTextView)).
                setText(cursor.getString(cursor.getColumnIndex("title")));
        // Get the date of the notes written to the Text View
        ((TextView) view.findViewById(R.id.dateTextView)).
                setText(cursor.getString(cursor.getColumnIndex("date")));
    }
}
