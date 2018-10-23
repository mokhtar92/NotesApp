package iti.gov.eg.livedata_mvvm_room.repository.server;

import java.util.ArrayList;
import java.util.List;

import iti.gov.eg.livedata_mvvm_room.entities.Note;
import iti.gov.eg.livedata_mvvm_room.repository.database.DbNote;

public class ApiNoteMapper {
    public static Note mapToNote(ApiNote apiNote) {
        return new Note(
                apiNote.getId(),
                apiNote.getNote(),
                apiNote.getTimestamp(),
                apiNote.getImageUrl()
        );
    }

    public static List<Note> mapToNoteList(List<ApiNote> list) {
        List<Note> noteList = new ArrayList<>();
        for (ApiNote note : list) {
            noteList.add(mapToNote(note));
        }
        return noteList;
    }

    public static ApiNote mapToApiNote(Note Note) {
        return new ApiNote(
                Note.getNote(),
                Note.getImageUrl()
        );
    }
}