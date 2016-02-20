package com.example.nina.mynotes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Nina Longasa on 1/31/2016.
 */

// This Java class contains the database of MyNotes to store
    // the notes of the user

public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "mynotes.db";

    public static final String TABLE_MY_NOTES = "mynotes";
    public static final String COLUMN_NOTE_ID = "_id";
    public static final String COLUMN_NOTE_TITLE = "title";
    public static final String COLUMN_NOTE_TEXT = "text";
    public static final String COLUMN_NOTE_DATE = "date";

    public DBHandler (Context context, SQLiteDatabase.CursorFactory factory){
        super (context, DATABASE_NAME, factory, DATABASE_VERSION);
    }
    // This Database was needed to be updated since the App kept crashing
        // The error message I received was "missing "_id" in the database"

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

        // This override method will remove any old databases in
            // favor of the new database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MY_NOTES);
        onCreate(db);
    }

        // Creates the database of MyNotes
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

        // This method retrieves the MyNotes database
    public Cursor getMyNotes(){

        // Get reference from the database
        SQLiteDatabase db = getWritableDatabase();
        // This executes the database and returns the database in form of a cursor
        return db.rawQuery("SELECT * FROM " + TABLE_MY_NOTES, null);
    }

        // This method keeps track of the number of notes stored
            // in the MyNotes Database
    public String getNotesTotal(){

        String dbString = "";

        String query = "SELECT * FROM " + TABLE_MY_NOTES;

        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery(query, null);

        int numNotes = c.getCount();

        dbString = String.valueOf(numNotes);
        db.close();

        return dbString;
    }

        // This method gets the information from the MyNotes database
            // for the Notes object
    public Notes getNotes (Integer notesId){

        Notes notes = null;

        SQLiteDatabase db = getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_MY_NOTES +
                " WHERE " + COLUMN_NOTE_ID + " = " + notesId;

        Cursor cursor = db.rawQuery(query, null);

        int numItems = cursor.getCount();

        if (numItems >=1){
            cursor.moveToFirst();
                // This retrieves the date from the Database
            notes = new Notes(
                    (cursor.getInt(cursor.getColumnIndex("_id"))),
                    (cursor.getString(cursor.getColumnIndex("title"))),
                    (cursor.getString(cursor.getColumnIndex("text"))),
                    (cursor.getString(cursor.getColumnIndex("date")))
            );
        }

        db.close();
        return notes;
    }
}
