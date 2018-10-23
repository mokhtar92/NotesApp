package iti.gov.eg.livedata_mvvm_room.repository.server;

import com.google.gson.annotations.Expose;

public class ApiNote {

    @Expose
    private int id;

    @Expose
    private String note;

    @Expose
    private String timestamp;

    private String imageUrl;

    public ApiNote() {
    }

    public ApiNote(String note, String imageUrl) {
        this.note = note;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "ApiNote{" +
                "id=" + id +
                ", note='" + note + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
