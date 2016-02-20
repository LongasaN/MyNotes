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

public class ViewNote extends AppCompatActivity {

    // Declare the Intent
    Intent intent;
    // Declare the bundle
    Bundle bundle;
    // Declare ID
    long id;
    // Declare DBHandler
    DBHandler dbHandler;
    // Declare EditText fields
    EditText titleEditText;
    EditText notesEditText;
    EditText dateEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_note);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Initialize the data

        bundle = this.getIntent().getExtras();
        id = bundle.getLong("_id");

        dbHandler = new DBHandler(this, null);

        titleEditText = (EditText) findViewById(R.id.titleEditText);
        notesEditText = (EditText) findViewById(R.id.notesEditText);
        dateEditText = (EditText) findViewById(R.id.dateEditText);

        // Call new DBHandler
        Notes notes = dbHandler.getNotes((int) id);

        titleEditText.setText(notes.getTitle().toString());
        notesEditText.setText(notes.getText().toString());
        dateEditText.setText(notes.getDate().toString());

    }

        // This method will delete the selected note
    public void deleteNotes(MenuItem menuItem){

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_note, menu);
        return true;
    }

    // This Override Method is used for opening the pages of the app
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
