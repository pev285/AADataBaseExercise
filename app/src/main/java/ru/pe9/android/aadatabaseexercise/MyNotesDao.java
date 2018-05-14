package ru.pe9.android.aadatabaseexercise;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface MyNotesDao {

    final String NOTES_TABLE_NAME = "MyNoteEntity";
    final String UID_COLUMN = "uid";
    final String TITLE_COLUMN = "title";
    final String TIME_COLUMN = "time";

    @Query("SELECT * FROM " + NOTES_TABLE_NAME + " ORDER BY " + TIME_COLUMN + " DESC")
    List<MyNoteEntity> getAllNotes();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(MyNoteEntity... entities);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(MyNoteEntity entity);

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

    @Query("SELECT " + UID_COLUMN + " FROM " + NOTES_TABLE_NAME + " WHERE " + TIME_COLUMN + " = :time AND " + TITLE_COLUMN + " LIKE :title LIMIT 1")
    int getId(String title, long time);
}
