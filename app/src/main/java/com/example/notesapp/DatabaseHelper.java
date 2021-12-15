package com.example.notesapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;


import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String NOTE_TABLE = "NOTE_TABLE";
    public static final String COLUMN_NOTE_TITLE = "NOTE_TITLE";
    public static final String COLUMN_NOTE_TXT = "NOTE_TXT";
    public static final String COLUMN_IS_FAVORITE = "IS_FAVORITE";
    public static final String COLUMN_ID = "ID";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "note.db", null, 1);
    }

    // called first time a database is accessed. Should have code to create new database
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + NOTE_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NOTE_TITLE + " TEXT, " + COLUMN_NOTE_TXT + " TEXT, " + COLUMN_IS_FAVORITE + " BOOL)";

        db.execSQL(createTableStatement);
    }

    // called whenever version number changes
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(NoteModel note){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NOTE_TITLE, note.getTitle());
        cv.put(COLUMN_NOTE_TXT, note.getNote_text());
        cv.put(COLUMN_IS_FAVORITE, note.isIs_favorite());

        long insert = db.insert(NOTE_TABLE, null, cv);

        return insert != -1;
    }

    public void deleteOne(NoteModel note){
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + NOTE_TABLE + " WHERE " + COLUMN_ID + " = " + note.getId();

        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(queryString, null);

        cursor.moveToFirst();

    }

    public void updateOne(int id, String title, String text, Boolean isFavorite){
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "UPDATE " + NOTE_TABLE + " SET "
                + COLUMN_NOTE_TITLE + " = \"" + title + "\", "
                + COLUMN_NOTE_TXT + " =  \"" + text + "\", "
                + COLUMN_IS_FAVORITE + " = " + (isFavorite ? 1: 0)
                + " WHERE " + COLUMN_ID + " == " + id;

        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(queryString, null);

        cursor.moveToFirst();
    }

    public List<NoteModel> getAllNotes(){
        List<NoteModel> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + NOTE_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
            // loop through the cursor
            do{
                int noteId = cursor.getInt(0);
                String noteTitle = cursor.getString(1);
                String noteText = cursor.getString(2);
                boolean isFavorite = cursor.getInt(3) == 1;

                NoteModel note = new NoteModel(noteId, noteTitle, noteText, isFavorite);
                returnList.add(note);

            } while (cursor.moveToNext());
        }
        // failure, don't add anything


        cursor.close();
        db.close();
        return returnList;
    }

    public List<NoteModel> getFavorites(){
        List<NoteModel> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + NOTE_TABLE + " WHERE IS_FAVORITE == 1";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
            // loop through the cursor
            do{
                int noteId = cursor.getInt(0);
                String noteTitle = cursor.getString(1);
                String noteText = cursor.getString(2);
                boolean isFavorite = cursor.getInt(3) == 1;

                NoteModel note = new NoteModel(noteId, noteTitle, noteText, isFavorite);
                returnList.add(note);

            } while (cursor.moveToNext());
        }
        // failure, don't add anything


        cursor.close();
        db.close();
        return returnList;
    }
    public NoteModel getNoteById(int id){

        String queryString = "SELECT * FROM " + NOTE_TABLE + " WHERE ID == " + id;

        SQLiteDatabase db = this.getReadableDatabase();

        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(queryString, null);
        NoteModel note = new NoteModel();

        if(cursor.moveToFirst()){
            // loop through the cursor
            do{
                int noteId = cursor.getInt(0);
                String noteTitle = cursor.getString(1);
                String noteText = cursor.getString(2);
                boolean isFavorite = cursor.getInt(3) == 1;

                note.setId(noteId);
                note.setTitle(noteTitle);
                note.setNote_text(noteText);
                note.setIs_favorite(isFavorite);

            } while (cursor.moveToNext());
        }
        // failure, don't add anything
        return note;
    }
}
