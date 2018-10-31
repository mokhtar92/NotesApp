package iti.gov.eg.livedata_mvvm_room.repository;

import android.arch.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import iti.gov.eg.livedata_mvvm_room.entities.Note;
import iti.gov.eg.livedata_mvvm_room.repository.database.DatabaseHelper;
import iti.gov.eg.livedata_mvvm_room.repository.server.NoteService;

public class Repository implements IRepository {

    private final NoteService service;

    private final DatabaseHelper database;

    @Inject
    public Repository(NoteService service, DatabaseHelper database) {
        this.service = service;
        this.database = database;
    }

    @Override
    public LiveData<List<Note>> fetchAllNotesOnline() {
        return service.fetchAllNotes();
    }

    @Override
    public LiveData<List<Note>> fetchAllNotesOffline() {
        return database.queryAllNotesLatestFirst();
    }

    @Override
    public LiveData<Note> insertNewNoteApi(Note note) {
        return service.createNote(note);
    }

    @Override
    public void insertNewNoteDb(Note note) {
        database.insertNewNote(note);
    }

    @Override
    public void insertAllNotesToDb(List<Note> notes) {
        database.insertAllNotes(notes);
    }

    @Override
    public void deleteNoteApi(int id) {
        service.deleteNote(id);
    }

    @Override
    public void deleteNoteDb(int id) {
        database.deleteSingleNote(id);
    }
}