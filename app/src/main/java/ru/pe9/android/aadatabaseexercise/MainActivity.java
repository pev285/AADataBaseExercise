package ru.pe9.android.aadatabaseexercise;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import java.util.GregorianCalendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText noteTitle;
    private EditText noteBody;
    private Button addNoteButton;

    private DataBaseController dbController;
    MyNoteEntityListAdapter recyclerViewAdapter = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbController = new DataBaseController(this);

        noteTitle = findViewById(R.id.noteTitleEditText);
        noteBody = findViewById(R.id.noteBodyEditText);
        addNoteButton = findViewById(R.id.addNoteButton);
        addNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddNoteButtonClick();
            }
        });


        initializeRecyclerView();
    } // onCreate() /////////

    @Override
    protected void onDestroy() {
        super.onDestroy();

        dbController.onDestroy();
    }

        @Override
    protected void onStart() {
        super.onStart();
        AsyncTask<Integer, Integer, Integer> asyncTask = new AsyncTask<Integer, Integer, Integer>() {
            @Override
            protected Integer doInBackground(Integer[] objects) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
            @Override
            protected void onPostExecute(Integer o) {
                super.onPostExecute(o);
                hideKeyboard();
            }
        };

        asyncTask.execute(1);
    } // onStart() ////

    private void initializeRecyclerView() {
        final RecyclerView recyclerView = findViewById(R.id.notesRecyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        dbController.getNotesFromDatabase(new IAction<List<MyNoteEntity>>() {
            @Override
            public void action(List<MyNoteEntity> myNoteEntities) {
                recyclerViewAdapter = new MyNoteEntityListAdapter(myNoteEntities, dbController);
                recyclerView.setAdapter(recyclerViewAdapter);
            }
        });
    }

    private void onAddNoteButtonClick() {
        long time = new GregorianCalendar().getTimeInMillis();
        String title = noteTitle.getText().toString();
        String body = noteBody.getText().toString();

        if (!title.isEmpty() || !body.isEmpty()) {
            recyclerViewAdapter.addItem(new MyNoteEntity(time, title, body));

            noteTitle.setText("");
            noteBody.setText("");
            hideKeyboard();
        }

    }


    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        //View view = addNoteButton;
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


} // End of class /////
