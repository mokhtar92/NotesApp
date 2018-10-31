package iti.gov.eg.livedata_mvvm_room.presentation.features.homeScreen;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import iti.gov.eg.livedata_mvvm_room.R;
import iti.gov.eg.livedata_mvvm_room.entities.Note;

import static iti.gov.eg.livedata_mvvm_room.HelperClass.isConnectedToInternet;
import static iti.gov.eg.livedata_mvvm_room.presentation.subFeatures.DialogHelper.getDialogBuilder;

public class HomeActivity extends AppCompatActivity implements NoteAdapter.NoteItemClickListener {

    HomeViewModel viewModel;

    @Inject
    ViewModelProvider.Factory factory;

    @Inject
    RecyclerView.LayoutManager layoutManager;

    @Inject
    NoteAdapter adapter;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.btn_add_new_note)
    FloatingActionButton addNoteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        publishUi();
    }

    private void publishUi() {
        setupRecyclerViewAdapter();

        viewModel = ViewModelProviders.of(this, factory).get(HomeViewModel.class);

        bindAddNoteButton();

        refreshDataSet();
    }

    private void setupRecyclerViewAdapter() {
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(false);
        recyclerView.setAdapter(adapter);
    }

    private void bindAddNoteButton() {
        addNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInputDialog();
            }
        });
    }

    private void refreshDataSet() {
        viewModel.fetchAllData().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(@Nullable List<Note> notes) {
                adapter.setNoteList(notes);
            }
        });
    }

    private void showInputDialog() {
        View mView = LayoutInflater.from(this).inflate(R.layout.user_input_dialog, null);
        final EditText editText = mView.findViewById(R.id.userInputDialog);
        getDialogBuilder(this)
                .setView(mView)
                .setPositiveButton(R.string.str_add, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {
                        String noteContent = editText.getText().toString().trim();
                        if (noteContent.isEmpty()) {
                            Toast.makeText(HomeActivity.this, R.string.str_empty_note_warning, Toast.LENGTH_SHORT).show();

                        } else if (!isConnectedToInternet(HomeActivity.this)) {
                            Toast.makeText(HomeActivity.this, R.string.str_connection_error, Toast.LENGTH_SHORT).show();

                        } else {
                            Note note = new Note(noteContent);
                            viewModel.insertNote(note).observe(HomeActivity.this, new Observer<Note>() {
                                @Override
                                public void onChanged(@Nullable Note successfulNote) {
                                    viewModel.insertNoteToDb(successfulNote);
                                    adapter.insertNoteAtFirstPosition(successfulNote);
                                    recyclerView.scrollToPosition(0);
                                }
                            });
                            editText.setText("");
                        }
                    }
                }).show();
    }

    @Override
    public void itemClicked(final int position, final int noteId) {
        if (isConnectedToInternet(this)) {
            getDialogBuilder(this)
                    .setTitle(R.string.str_delete_note_confirmation)
                    .setPositiveButton(R.string.str_delete, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // delete note
                            viewModel.deleteNoteFromServer(noteId);
                            viewModel.deleteNoteFromDb(noteId);
                            adapter.removeNoteAtPosition(position);
                        }
                    }).show();

        } else {
            Toast.makeText(this, R.string.str_connection_error, Toast.LENGTH_SHORT).show();
        }
    }
}