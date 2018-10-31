package iti.gov.eg.livedata_mvvm_room.presentation.features.homeScreen;

import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import iti.gov.eg.livedata_mvvm_room.presentation.components.ViewModelProviderFactory;
import iti.gov.eg.livedata_mvvm_room.entities.Note;
import iti.gov.eg.livedata_mvvm_room.repository.Repository;

@Module
public class HomeActivityModule {

    @Provides
    @Named("activity_context")
    Context providesContext(HomeActivity homeActivity) {
        return homeActivity;
    }

    @Provides
    NoteAdapter providesNoteAdapter(@Named("activity_context") Context context, NoteAdapter.NoteItemClickListener clickListener) {
        return new NoteAdapter(context, new ArrayList<Note>(), clickListener);
    }

    @Provides
    RecyclerView.LayoutManager providesLayoutManager(@Named("activity_context") Context context) {
        return new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
    }

    @Provides
    NoteAdapter.NoteItemClickListener providesNoteItemClickListener(HomeActivity homeActivity) {
        if (NoteAdapter.NoteItemClickListener.class.isAssignableFrom(homeActivity.getClass())) {
            return homeActivity;
        }
        throw new IllegalArgumentException(HomeActivity.class.getSimpleName() + " Must implement NoteItemClickListener.");
    }

    @Provides
    ViewModelProvider.Factory mainViewModelProvider(HomeViewModel homeViewModel) {
        return new ViewModelProviderFactory<>(homeViewModel);
    }

    @Provides
    HomeViewModel provideMainViewModel(@Named("application_context") Context context, Repository repository) {
        return new HomeViewModel(context, repository);
    }
}