package iti.gov.eg.livedata_mvvm_room.repository.database;

import android.annotation.SuppressLint;
import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.os.AsyncTask;

import java.util.List;

import javax.inject.Inject;

import iti.gov.eg.livedata_mvvm_room.entities.Note;

import static iti.gov.eg.livedata_mvvm_room.repository.database.DbNoteMapper.*;

public class DatabaseHelper {

    private final NoteDao noteDao;


    @Inject
    public DatabaseHelper(NoteDao noteDao) {
        this.noteDao = noteDao;
    }

    @SuppressLint("StaticFieldLeak")
    public void insertNewNote(Note note) {
        new AsyncTask<Note, Void, Void>() {
            @Override
            protected Void doInBackground(Note... notes) {
                noteDao.insertNewNote(mapToDbNote(notes[0]));
                return null;
            }
        }.execute(note);
    }

    @SuppressLint("StaticFieldLeak")
    public void insertAllNotes(List<Note> notes) {
        new AsyncTask<List<Note>, Void, Void>() {
            @Override
            protected Void doInBackground(List<Note>... voids) {
                noteDao.insertAllNotes(mapToDbNoteList(voids[0]));
                return null;
            }
        }.execute(notes);
    }

    public LiveData<List<Note>> queryAllNotes() {
        return Transformations.map(noteDao.getAllNotes(), new Function<List<DbNote>, List<Note>>() {
            @Override
            public List<Note> apply(List<DbNote> input) {
                return mapToNoteList(input);
            }
        });
    }

    public LiveData<List<Note>> queryAllNotesOldestFirst() {
        return Transformations.map(noteDao.getAllNotesOldestFirst(), new Function<List<DbNote>, List<Note>>() {
            @Override
            public List<Note> apply(List<DbNote> input) {
                return mapToNoteList(input);
            }
        });
    }

    public LiveData<Note> querySingleNote(int id) {
        return Transformations.map(noteDao.getSingleNote(id), new Function<DbNote, Note>() {
            @Override
            public Note apply(DbNote input) {
                return mapToNote(input);
            }
        });
    }

    @SuppressLint("StaticFieldLeak")
    public void deleteAllNotes() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                noteDao.deleteAllNotes();
                return null;
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    public void deleteSingleNote(int id) {
        new AsyncTask<Integer, Void, Void>() {
            @Override
            protected Void doInBackground(Integer... integers) {
                noteDao.deleteSingleNote(integers[0]);
                return null;
            }
        }.execute(id);
    }
}