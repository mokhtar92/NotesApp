package iti.gov.eg.livedata_mvvm_room.repository.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import static iti.gov.eg.livedata_mvvm_room.Constants.COLUMN_CONTENT;
import static iti.gov.eg.livedata_mvvm_room.Constants.COLUMN_ID;
import static iti.gov.eg.livedata_mvvm_room.Constants.COLUMN_IMAGE_URL;
import static iti.gov.eg.livedata_mvvm_room.Constants.COLUMN_TIMESTAMP;
import static iti.gov.eg.livedata_mvvm_room.Constants.TABLE_NAME;

@Entity(tableName = TABLE_NAME)
public class DbNote {

    @PrimaryKey
    @ColumnInfo(name = COLUMN_ID)
    private int id;

    @ColumnInfo(name = COLUMN_CONTENT)
    private String note;

    @ColumnInfo(name = COLUMN_TIMESTAMP)
    private String timestamp;

    @Ignore //not to be stored at current stage
    @ColumnInfo(name = COLUMN_IMAGE_URL)
    private String imageUrl;


    public DbNote() {
    }

    public DbNote(int id, String note, String timestamp, String imageUrl) {
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

    public void setId(int id) {
        this.id = id;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "DbNote{" +
                "id=" + id +
                ", noteContent='" + note + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}