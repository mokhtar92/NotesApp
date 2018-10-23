package iti.gov.eg.livedata_mvvm_room.repository.database;

import java.util.ArrayList;
import java.util.List;

import iti.gov.eg.livedata_mvvm_room.entities.Note;

public class DbNoteMapper {
    public static Note mapToNote(DbNote dbNote) {
        return new Note(
                dbNote.getId(),
                dbNote.getNote(),
                dbNote.getTimestamp(),
                dbNote.getImageUrl()
        );
    }

    public static List<Note> mapToNoteList(List<DbNote> list) {
        List<Note> noteList = new ArrayList<>();
        for (DbNote note : list) {
            noteList.add(mapToNote(note));
        }
        return noteList;
    }

    public static DbNote mapToDbNote(Note note) {
        return new DbNote(
                note.getId(),
                note.getNote(),
                note.getTimestamp(),
                note.getImageUrl()
        );
    }

    public static List<DbNote> mapToDbNoteList(List<Note> list) {
        List<DbNote> noteList = new ArrayList<>();
        for (Note note : list) {
            noteList.add(mapToDbNote(note));
        }
        return noteList;
    }
}
