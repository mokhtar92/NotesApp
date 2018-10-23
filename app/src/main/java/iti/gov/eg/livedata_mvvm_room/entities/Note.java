package iti.gov.eg.livedata_mvvm_room.entities;

public class Note {

    private int id;

    private String note;

    private String timestamp;

    private String imageUrl = "";


    public Note(String note) {
        this.note = note;
    }

    public Note(int id, String note, String timestamp, String imageUrl) {
        this.id = id;
        this.note = note;
        this.timestamp = timestamp;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public String getNote() {
        return note;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", note='" + note + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}