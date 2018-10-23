package iti.gov.eg.livedata_mvvm_room.presentation.features.homeScreen;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import iti.gov.eg.livedata_mvvm_room.R;
import iti.gov.eg.livedata_mvvm_room.entities.Note;

import static iti.gov.eg.livedata_mvvm_room.HelperClass.formatDateTimeFromServer;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private Context context;
    private List<Note> noteList;
    private NoteItemClickListener clickListener;


    NoteAdapter(Context context, List<Note> noteList, NoteItemClickListener clickListener) {
        this.context = context;
        this.noteList = noteList;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_card_view, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        final Note note = noteList.get(position);
        Glide.with(context)
                .load(note.getImageUrl())
                .apply(new RequestOptions()
                        .placeholder(R.drawable.placeholder)
                        .centerCrop())
                .into(holder.noteImage);
        holder.noteTextView.setText(note.getNote());
        holder.noteDateTextView.setText(formatDateTimeFromServer(note.getTimestamp()));
        holder.deleteNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.itemClicked(note.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    static class NoteViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.note_image_view)
        ImageView noteImage;

        @BindView(R.id.note_content_text_view)
        TextView noteTextView;

        @BindView(R.id.note_date_text_view)
        TextView noteDateTextView;

        @BindView(R.id.btn_delete_note)
        ImageButton deleteNoteButton;

        NoteViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setNoteList(List<Note> noteList) {
        this.noteList = noteList;
        notifyDataSetChanged();
    }

    interface NoteItemClickListener {
        void itemClicked(int noteId);
    }
}