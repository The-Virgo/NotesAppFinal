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


public class FavoritesFragment extends Fragment {

    ListView favorites_list;
    ArrayAdapter<NoteModel> notesArrayAdapter;

    public FavoritesFragment() {
        // Required empty public constructor
    }

    public static FavoritesFragment newInstance() {
        FavoritesFragment fragment = new FavoritesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.favorites_fragment, container, false);

        DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());

        List<NoteModel> favorites = databaseHelper.getFavorites();

        favorites_list = root.findViewById(R.id.favorites_list);
        notesArrayAdapter = new ArrayAdapter<>(getActivity(), R.layout.list_views, favorites);
        favorites_list.setAdapter(notesArrayAdapter);

        favorites_list.setOnItemClickListener(((parent, view, position, id) -> {

            NoteModel clickedNote = (NoteModel) parent.getItemAtPosition(position);
            int noteID = clickedNote.getId();

            Intent i = new Intent(getContext(), EditNoteActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("stuff", String.valueOf(noteID));
            i.putExtras(bundle);
            startActivity(i);


        }));

        notesArrayAdapter.notifyDataSetChanged();

        return root;
    }
}