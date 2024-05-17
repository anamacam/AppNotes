package com.example.appnotes;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.appnotes.Adapter.NoteListAdapter;
import com.example.appnotes.Database.RoomDb;
import com.example.appnotes.Interface.NotesCheckListener;
import com.example.appnotes.Model.Notes;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    NoteListAdapter noteListAdapter;
    RoomDb database;
    List<Notes> notesList = new ArrayList<>();
    Button fabBtn; 

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.noteRV);
        fabBtn = findViewById(R.id.addBtn);
        database = RoomDb.getInstance(this);
        notesList = database.mainDAO().getAll();
        updateRecycle(notesList);

        fabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void updateRecycle(List<Notes> notes) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
        noteListAdapter = new NoteListAdapter(MainActivity.this, notes, notesCheckListener);
        recyclerView.setAdapter(noteListAdapter);
    }

    private final NotesCheckListener notesCheckListener = new NotesCheckListener() {

        @Override
        public void onClick(Notes notes) {
            super.onClick(notes);
        }

        @Override
        public void onLongPress(Notes notes, CardView cardView) {
            super.onLongPress(notes, cardView);
        }
    };
}
