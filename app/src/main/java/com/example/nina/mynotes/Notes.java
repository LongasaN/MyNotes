package com.example.nina.mynotes;

/**
 * Created by Nina Longasa on 2/20/2016.
 */

// This is an object for the MyNotes database
    // to get and set the information

public class Notes {

    private int id;
    private String title;
    private String text;
    private String date;

    // Notes Constructor
    public Notes(int id, String title, String text, String date) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.date = date;
    }

        // This method gets the MyNotes Database ID
    public int getId() {
        return id;
    }

    // This method sets the MyNotes Database ID
    public void setId(int id) {
        this.id = id;
    }

    // This method gets the MyNotes Database Title
    public String getTitle() {
        return title;
    }

    // This method sets the MyNotes Database Title
    public void setTitle(String title) {
        this.title = title;
    }

    // This method gets the MyNotes Database Text/Notes
    public String getText() {
        return text;
    }

    // This method sets the MyNotes Database Text/Notes
    public void setText(String text) {
        this.text = text;
    }

    // This method gets the MyNotes Database Date
    public String getDate() {
        return date;
    }

    // This method sets the MyNotes Database Date
    public void setDate(String date) {
        this.date = date;
    }
}
