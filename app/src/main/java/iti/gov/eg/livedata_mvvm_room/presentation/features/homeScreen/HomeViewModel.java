package iti.gov.eg.livedata_mvvm_room.presentation.features.homeScreen;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import java.util.List;

import iti.gov.eg.livedata_mvvm_room.entities.Note;
import iti.gov.eg.livedata_mvvm_room.repository.Repository;

import static iti.gov.eg.livedata_mvvm_room.HelperClass.isConnectedToInternet;

public class HomeViewModel extends ViewModel {

    private final Context context;
    private final Repository repository;

    public HomeViewModel(Context context, Repository repository) {
        this.context = context;
        this.repository = repository;
    }

    public LiveData<List<Note>> fetchAllData() {
        if (isConnectedToInternet(context)) {
            return repository.fetchAllNotesOnline();

        } else {
            return repository.fetchAllNotesOffline();
        }
    }

    public LiveData<Note> insertNote(Note note) {
        return repository.insertNewNoteApi(note);
    }

    public void backupAllNotesInDb(List<Note> notes){
        repository.insertAllNotesToDb(notes);
    }

    public void deleteNoteFromServer(int id){
        repository.deleteNoteApi(id);
    }

    public void deleteNoteFromDb(int id){
        repository.deleteNoteDb(id);
    }
}