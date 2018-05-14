package ru.pe9.android.aadatabaseexercise;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class DataBaseController {

//    private Context context;
//    private MyApplicationDatabase dbInstance;
    private final MyNotesDao dao;

    private final HandlerThread handlerThread;
    private final Handler handler;

    public DataBaseController(Context context) {
//        this.context = context;
        MyApplicationDatabase dbInstance = MyApplicationDatabase.getInstance(context);
        this.dao = dbInstance.getMyNotesDao();

        handlerThread = new HandlerThread("DB operations handler thread");
        handlerThread.start();
        Looper looper = handlerThread.getLooper();
        handler = new Handler(looper);
    }

    public void onDestroy() {
        if (handlerThread != null) {
            handlerThread.quit();
        }
    }


    // DB Operations //////

    public void delete(MyNoteEntity note) {
        final MyNoteEntity localNote = note;
        handler.post(new Runnable() {
            @Override
            public void run() {
                dao.delete(localNote);
            }
        });
    }

    public void add(final MyNoteEntity note) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                long id = dao.insert(note);
                note.setUid(id);
            }
        });

    }


    public void getNotesFromDatabase(final IAction<List<MyNoteEntity>> putDataToRecyclerView) {
        handler.post(new Runnable() {
            @Override
            public void run() {
               List<MyNoteEntity> notes = dao.getAllNotes();
               putDataToRecyclerView.action(notes);
            }
        });
    }

    public void clearDataBase() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                dao.deleteAll();
            }
        });
    }

//    public int getId(MyNoteEntity note) {
//        return dao.getId(note.getTitle(), note.getTime());
//    }



//////////////////////////////////////////////////////////////////////////
/*


    private List<MyNoteEntity> getTestNotes() {
        List<MyNoteEntity> notes = new ArrayList<MyNoteEntity>();

        notes.add(MyNoteEntity.test("title 01", "Body body body body body body 01"));
        notes.add(MyNoteEntity.test("title 02", "Body body body body body body 02"));
        notes.add(MyNoteEntity.test("title 03", "Body body body body body body 03"));
        notes.add(MyNoteEntity.test("title 04", "Body body body body body body 04"));
        notes.add(MyNoteEntity.test("title 05", "Body body body body body body 05"));
        notes.add(MyNoteEntity.test("title 06", "Body body body body body body 06"));
        notes.add(MyNoteEntity.test("title 07", "Body body body body body body 07"));
        notes.add(MyNoteEntity.test("title 08", "Body body body body body body 08"));
        notes.add(MyNoteEntity.test("title 09", "Body body body body body body 09"));
        notes.add(MyNoteEntity.test("title 01", "Body body body body body body 10"));
        notes.add(MyNoteEntity.test("title 11", "Body body body body body body 11"));
        notes.add(MyNoteEntity.test("title 12", "Body body body body body body 12"));

        return notes;
    }

    public void addFakeDataToDB() {
        List<MyNoteEntity> testData = getTestNotes();
        dao.insertAll(
                testData.get(0),
                testData.get(1),
                testData.get(2),
                testData.get(3),
                testData.get(4),
                testData.get(5),
                testData.get(6),
                testData.get(7),
                testData.get(8)
        );
    }

*/


}
