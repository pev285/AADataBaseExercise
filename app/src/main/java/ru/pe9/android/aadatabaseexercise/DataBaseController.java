package ru.pe9.android.aadatabaseexercise;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class DataBaseController {

//    private Context context;
    private MyApplicationDatabase dbInstance;
    private MyNotesDao dao;

    public DataBaseController(Context context) {
//        this.context = context;
        MyApplicationDatabase dbInstance = MyApplicationDatabase.getInstance(context);
        this.dao = dbInstance.getMyNotesDao();
    }


    public void Delete(MyNoteEntity note) {
        dao.delete(note);
    }

    public void Add(MyNoteEntity note) {
        dao.insert(note);
    }


    public List<MyNoteEntity> getNotesFromDatabase() {
        return dao.getAllNotes();
    }

    public void clearDataBase() {
        dao.deleteAll();
    }

    public int getId(MyNoteEntity note) {
        return dao.getId(note.getTitle(), note.getTime());
    }


//////////////////////////////////////////////////////////////////////////



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




}
