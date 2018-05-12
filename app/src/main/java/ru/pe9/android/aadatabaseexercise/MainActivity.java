package ru.pe9.android.aadatabaseexercise;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText noteTitle;
    private EditText noteBody;
    private Button addNoteButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        noteTitle = findViewById(R.id.noteTitleEditText);
        noteBody = findViewById(R.id.noteBodyEditText);
        addNoteButton = findViewById(R.id.addNoteButton);

        initializeRecyclerView();
    }

    private void initializeRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.notesRecyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        RecyclerView.Adapter adapter = new MyNoteEntityListAdapter(getTestNotes());
        recyclerView.setAdapter(adapter);
    }



    private List<MyNoteEntity> getTestNotes() {
        List<MyNoteEntity> notes = new ArrayList<MyNoteEntity>();

        notes.add(new MyNoteEntity("title 01", "Body body body body body body 01"));
        notes.add(new MyNoteEntity("title 02", "Body body body body body body 02"));
        notes.add(new MyNoteEntity("title 03", "Body body body body body body 03"));
        notes.add(new MyNoteEntity("title 04", "Body body body body body body 04"));
        notes.add(new MyNoteEntity("title 05", "Body body body body body body 05"));
        notes.add(new MyNoteEntity("title 06", "Body body body body body body 06"));
        notes.add(new MyNoteEntity("title 07", "Body body body body body body 07"));
        notes.add(new MyNoteEntity("title 08", "Body body body body body body 08"));
        notes.add(new MyNoteEntity("title 09", "Body body body body body body 09"));
        notes.add(new MyNoteEntity("title 01", "Body body body body body body 10"));
        notes.add(new MyNoteEntity("title 11", "Body body body body body body 11"));
        notes.add(new MyNoteEntity("title 12", "Body body body body body body 12"));

        return notes;
    }
}
