package iti.gov.eg.livedata_mvvm_room.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import iti.gov.eg.livedata_mvvm_room.repository.database.DatabaseHelper;
import iti.gov.eg.livedata_mvvm_room.repository.database.NoteDao;

@Module(includes = NoteDaoModule.class)
public class DatabaseModule {

    @Provides
    @Singleton
    DatabaseHelper providesDatabaseHelper(NoteDao noteDao) {
        return new DatabaseHelper(noteDao);
    }
}
