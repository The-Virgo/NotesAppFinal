package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class NewNoteActivity extends AppCompatActivity {

    Button add_note_button;
    EditText note_title, note_text;
    Switch note_switch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);

        getSupportActionBar().hide();

        add_note_button = findViewById(R.id.add_note_button);
        note_title = findViewById(R.id.note_title);
        note_text = findViewById(R.id.note_text);
        note_switch = findViewById(R.id.note_switch);

        add_note_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                if(note_title.getText().toString().trim().equals("")){
                    Toast.makeText(NewNoteActivity.this, "Please add a note title", Toast.LENGTH_SHORT).show();
                }
                if(note_text.getText().toString().trim().equals("")){
                    Toast.makeText(NewNoteActivity.this, "No note text found", Toast.LENGTH_SHORT).show();
                }else{
                    NoteModel note = new NoteModel(-1, note_title.getText().toString(), note_text.getText().toString(), note_switch.isChecked());

                    DatabaseHelper databaseHelper = new DatabaseHelper(NewNoteActivity.this);
                    boolean success = databaseHelper.addOne(note);
                    Toast.makeText(NewNoteActivity.this, "Note Added", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(NewNoteActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }



            }
        });

    }

}