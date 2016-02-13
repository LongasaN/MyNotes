package com.example.nina.mynotes;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    // Declare intent
    Intent intent;
    // Declare DBHandler
    DBHandler dbHandler;
    MyNotes myNotesAdapter;
    // Reference the list view
    ListView notesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Initialized the activity
        dbHandler = new DBHandler(this, null);
        notesListView = (ListView) findViewById(R.id.notesListView);
        myNotesAdapter = new MyNotes(this, dbHandler.getMyNotes(), 0);

        // Set the Cursor Adapter
        notesListView.setAdapter(myNotesAdapter);

        // Setting a subtitle to display the number of notes the user
            // has written in the App
        toolbar.setSubtitle("Notes: " + dbHandler.getNotesTotal());

    }

    // Java Method where another window opens
    // displaying an open notes menu
    public void openCreateNotes (View view){
        intent = new Intent(this, CreateNotes.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
