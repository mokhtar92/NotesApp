package iti.gov.eg.livedata_mvvm_room.di.modules;

import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import iti.gov.eg.livedata_mvvm_room.repository.database.NoteDao;
import iti.gov.eg.livedata_mvvm_room.repository.database.NoteRoomDatabase;

@Module
public class NoteDaoModule {

    @Provides
    @Singleton
    NoteDao providesNoteDao(@Named("application_context") Context context) {
        return NoteRoomDatabase.getDatabase(context).noteDao();
    }
}
