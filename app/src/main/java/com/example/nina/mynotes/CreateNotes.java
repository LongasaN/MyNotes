package com.example.nina.mynotes;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CreateNotes extends AppCompatActivity {

    // Declare intent
    Intent intent;
    //Declare EditTexts
    EditText titleEditText;
    EditText textEditText;
    // Declare calendar
    Calendar calendar;
    // Declare database
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_notes);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Find the ID of the Edit Texts
        titleEditText = (EditText) findViewById(R.id.titleEditText);
        textEditText = (EditText) findViewById(R.id.textEditText);

            //Call the database method
        dbHandler = new DBHandler(this, null);
    }

        // This method is used to validate if there are characters entered in the Edit Text Fields
    public void createNotes (MenuItem menuItem){
        // Create a format for the date
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        // Getting the calendar
        calendar = Calendar.getInstance();

        // Set the variables for the method
        String title = titleEditText.getText().toString();
        String text = textEditText.getText().toString();
        String date = simpleDateFormat.format(calendar.getTime()).toString();

        //Validates if there is something in the edit text box
        if (title.trim().equals("") || text.trim().equals(""))
            Toast.makeText(this, "Please enter title and text", Toast.LENGTH_LONG).show();
        else
            dbHandler.createNotes(title, text, date);
            Toast.makeText(this, "Note added!", Toast.LENGTH_LONG).show();
    } // There has to be a problem with the calendar and the placement of the date
        // caused the MyNotes App to crash

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_notes, menu);
        return true;
    }

    // Method for opening the pages of the app
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_home :
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_create_notes :
                intent = new Intent(this, CreateNotes.class);
                startActivity(intent);
                return true;
            default :
            return super.onOptionsItemSelected(item);
        }
    }
}
