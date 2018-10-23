package iti.gov.eg.livedata_mvvm_room.repository.server;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import java.util.List;

import javax.inject.Inject;

import iti.gov.eg.livedata_mvvm_room.entities.Note;
import iti.gov.eg.livedata_mvvm_room.repository.database.NoteRoomDatabase;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

import static iti.gov.eg.livedata_mvvm_room.repository.server.ApiNoteMapper.mapToNote;
import static iti.gov.eg.livedata_mvvm_room.repository.server.ApiNoteMapper.mapToNoteList;

public class NoteService {

    private final INoteService noteService;


    @Inject
    public NoteService(INoteService noteService) {
        this.noteService = noteService;
    }

    public LiveData<Note> createNote(Note note) {
        final MutableLiveData<Note> liveData = new MutableLiveData<>();
        noteService.createNote(note.getNote()).enqueue(new Callback<ApiNote>() {
            @Override
            public void onResponse(Call<ApiNote> call, Response<ApiNote> response) {
                liveData.setValue(mapToNote(response.body()));
            }

            @Override
            public void onFailure(Call<ApiNote> call, Throwable t) {
                liveData.setValue(null);
                Timber.e("Error in createNote(String note)");
            }
        });
        return liveData;
    }

    // Fetch all notes
    public LiveData<List<Note>> fetchAllNotes() {
        final MutableLiveData<List<Note>> liveData = new MutableLiveData<>();
        noteService.fetchAllNotes().enqueue(new Callback<List<ApiNote>>() {
            @Override
            public void onResponse(Call<List<ApiNote>> call, Response<List<ApiNote>> response) {
                liveData.setValue(mapToNoteList(response.body()));
            }

            @Override
            public void onFailure(Call<List<ApiNote>> call, Throwable t) {
                liveData.setValue(null);
                Timber.e("Error in fetchAllNotes()");
            }
        });
        return liveData;
    }

    // Update single note
    public void updateNote(int noteId, String note) {
        noteService.updateNote(noteId, note).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Timber.e("Successful note updating");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Timber.e("Error note updating");
            }
        });
    }

    // Delete note
    public void deleteNote(int noteId) {
        noteService.deleteNote(noteId).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Timber.d("Successful note deleting");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Timber.e("Error note deleting");
            }
        });
    }
}