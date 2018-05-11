package ru.pe9.android.aadatabaseexercise;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface MyNotesDao {

    final String NOTES_TABLE_NAME = "MyNotesTable";
    final String UID_COLUMN = "uid";
    final String TITLE_COLUMN = "title";

    @Query("SELECT * FROM " + NOTES_TABLE_NAME)
    List<MyNoteEntity> getAllNotes();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(MyNoteEntity... entities);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(MyNoteEntity entities);

    @Delete
    void delete(MyNoteEntity entity);

    @Query("DELETE FROM " + NOTES_TABLE_NAME)
    void deleteAll();

    @Query("SELECT * FROM " + NOTES_TABLE_NAME + " WHERE " + UID_COLUMN + " = :id")
    MyNoteEntity findById(int id);

    @Query("SELECT * FROM " + NOTES_TABLE_NAME + " WHERE " + TITLE_COLUMN + " LIKE :title LIMIT 1")
    MyNoteEntity findByName(String title);

    @Query("SELECT * FROM " + NOTES_TABLE_NAME + " WHERE " + UID_COLUMN + " IN (:userIds)")
    List<MyNoteEntity> loadAllByIds(int[] userIds);

}
