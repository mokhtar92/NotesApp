package iti.gov.eg.livedata_mvvm_room.repository.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import static iti.gov.eg.livedata_mvvm_room.Constants.COLUMN_ID;
import static iti.gov.eg.livedata_mvvm_room.Constants.COLUMN_TIMESTAMP;
import static iti.gov.eg.livedata_mvvm_room.Constants.TABLE_NAME;


@Dao
public interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNewNote(DbNote note);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllNotes(List<DbNote> notes);

    @Query("SELECT * FROM " + TABLE_NAME)
    LiveData<List<DbNote>> getAllNotes();

    @Query("SELECT * FROM " + TABLE_NAME + " ORDER BY " + COLUMN_TIMESTAMP + " ASC")
    LiveData<List<DbNote>> getAllNotesOldestFirst();

    @Query("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = :local_id")
    LiveData<DbNote> getSingleNote(int local_id);

    @Query("DELETE FROM " + TABLE_NAME)
    void deleteAllNotes();

    @Query("DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = :local_id")
    void deleteSingleNote(int local_id);
}