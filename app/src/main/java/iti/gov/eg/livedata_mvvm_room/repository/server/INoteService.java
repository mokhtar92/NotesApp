package iti.gov.eg.livedata_mvvm_room.repository.server;

import java.util.List;

import iti.gov.eg.livedata_mvvm_room.entities.Note;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface INoteService {

    // Create note
    @FormUrlEncoded
    @POST("notes/new")
    Call<ApiNote> createNote(@Field("note") String note);

    // Fetch all notes
    @GET("notes/all")
    Call<List<ApiNote>> fetchAllNotes();

    // Update single note
    @FormUrlEncoded
    @PUT("notes/{id}")
    Call<Void> updateNote(@Path("id") int noteId, @Field("note") String note);

    // Delete note
    @DELETE("notes/{id}")
    Call<Void> deleteNote(@Path("id") int noteId);
}