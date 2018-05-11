package ru.pe9.android.aadatabaseexercise;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class MyNoteEntity {
    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name="title")
    private String title;

    @ColumnInfo(name="noteBody")
    private String noteBody;


    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNoteBody() {
        return noteBody;
    }

    public void setNoteBody(String noteBody) {
        this.noteBody = noteBody;
    }

    public MyNoteEntity(String title, String noteBody) {
        this(0, title, noteBody);
    }

    private MyNoteEntity(int uid, String title, String noteBody) {
        this.uid = uid;
        this.title = title;
        this.noteBody = noteBody;
    }

} // MyNoteEntity ///
