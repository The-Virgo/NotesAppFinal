package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

public class EditNoteActivity extends AppCompatActivity {
    Button edit_note_button;
    ImageButton delete_note_button;
    EditText edit_note_title, edit_note_text;
    Switch edit_note_switch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        getSupportActionBar().hide();

        Bundle bundle = getIntent().getExtras();
        String stuff = bundle.getString("stuff");
        int noteId = Integer.parseInt(stuff);

        edit_note_button = findViewById(R.id.edit_note_button);
        delete_note_button = findViewById(R.id.delete_note_button);
        edit_note_title = findViewById(R.id.edit_note_title);
        edit_note_text = findViewById(R.id.edit_note_text);
        edit_note_switch = findViewById(R.id.edit_note_switch);

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        NoteModel note = databaseHelper.getNoteById(noteId);

        edit_note_title.setText(note.getTitle());
        edit_note_text.setText(note.getNote_text());
        edit_note_switch.setChecked(note.isIs_favorite());

        delete_note_button.setOnClickListener(view -> {
            databaseHelper.deleteOne(note);

            Intent intent = new Intent(EditNoteActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        });

        edit_note_button.setOnClickListener(view -> {

            databaseHelper.updateOne(noteId, edit_note_title.getText().toString(), edit_note_text.getText().toString(), edit_note_switch.isChecked());
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(EditNoteActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        });
    }
}