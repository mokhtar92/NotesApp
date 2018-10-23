package iti.gov.eg.livedata_mvvm_room.repository;

import android.arch.lifecycle.LiveData;

import java.util.List;

import iti.gov.eg.livedata_mvvm_room.entities.Note;

interface IRepository {
    LiveData<List<Note>> fetchAllNotesOnline();

    LiveData<List<Note>> fetchAllNotesOffline();

    LiveData<Note> insertNewNoteApi(Note note);

    void insertNewNoteDb(Note note);

    void insertAllNotesToDb(List<Note> notes);

    void deleteNoteApi(int id);

    void deleteNoteDb(int id);
}