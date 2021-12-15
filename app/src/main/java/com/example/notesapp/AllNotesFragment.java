package com.example.notesapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class AllNotesFragment extends Fragment {

    ListView all_notes_list;
    ArrayAdapter<NoteModel> notesArrayAdapter;

    public AllNotesFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static AllNotesFragment newInstance() {
        AllNotesFragment fragment = new AllNotesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.all_notes_fragment, container, false);
        DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());

        List<NoteModel> allNotes = databaseHelper.getAllNotes();

        all_notes_list = root.findViewById(R.id.all_note_list);
        notesArrayAdapter = new ArrayAdapter<>(getActivity(), R.layout.list_views, allNotes);
        all_notes_list.setAdapter(notesArrayAdapter);

        all_notes_list.setOnItemClickListener(((parent, view, position, id) -> {

            NoteModel clickedNote = (NoteModel) parent.getItemAtPosition(position);
            int noteID = clickedNote.getId();

            Intent i = new Intent(getContext(), EditNoteActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("stuff", String.valueOf(noteID));
            i.putExtras(bundle);
            startActivity(i);


        }));

        return root;
    }
}