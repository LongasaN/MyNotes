package com.example.nina.mynotes;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Nina Longasa on 1/31/2016.
 */

// This Java class contains the database of MyNotes to store
    // the notes of the user

public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "mynotes.db";

    public static final String TABLE_MY_NOTES = "mynotes";
    public static final String COLUMN_NOTE_ID = "id";
    public static final String COLUMN_NOTE_TITLE = "title";
    public static final String COLUMN_NOTE_TEXT = "text";
    public static final String COLUMN_NOTE_DATE = "date";

    public DBHandler (Context context, SQLiteDatabase.CursorFactory factory){
        super (context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_MY_NOTES + "(" +
                COLUMN_NOTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_NOTE_TITLE + " TEXT," +
                COLUMN_NOTE_TEXT + " TEXT," +
                COLUMN_NOTE_DATE + " TEXT" +
                ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MY_NOTES);
        onCreate(db);
    }

    public void createNotes(String title, String text, String date){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_NOTE_TITLE, title);
        values.put(COLUMN_NOTE_TEXT, text);
        values.put(COLUMN_NOTE_DATE, date);

        // Adds the information in the database
        db.insert(TABLE_MY_NOTES, null, values);

        // Closes the database
        db.close();

    }

}
