package com.example.notesapp;

public class NoteModel {
    private int id;
    private String title;
    private String note_text;
    private boolean is_favorite;

    //constructors

    public NoteModel(int id, String title, String note_text, boolean is_favorite) {
        this.id = id;
        this.title = title;
        this.note_text = note_text;
        this.is_favorite = is_favorite;
    }

    public NoteModel() {

    }
    // toString

    @Override
    public String toString() {
        return title;
    }


    //getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote_text() {
        return note_text;
    }

    public void setNote_text(String note_text) {
        this.note_text = note_text;
    }

    public boolean isIs_favorite() {
        return is_favorite;
    }

    public void setIs_favorite(boolean is_favorite) {
        this.is_favorite = is_favorite;
    }
}
