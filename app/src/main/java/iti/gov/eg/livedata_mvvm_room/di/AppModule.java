package iti.gov.eg.livedata_mvvm_room.di;

import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import iti.gov.eg.livedata_mvvm_room.App;
import iti.gov.eg.livedata_mvvm_room.di.modules.DatabaseModule;
import iti.gov.eg.livedata_mvvm_room.di.modules.NoteServiceModule;
import iti.gov.eg.livedata_mvvm_room.repository.Repository;
import iti.gov.eg.livedata_mvvm_room.repository.database.DatabaseHelper;
import iti.gov.eg.livedata_mvvm_room.repository.server.NoteService;

@Module(includes = {NoteServiceModule.class, DatabaseModule.class})
public class AppModule {

    @Provides
    @Singleton
    @Named("application_context")
    Context providesApplicationContext(App application) {
        return application;
    }

    @Provides
    @Singleton
    Repository providesRepository(NoteService service, DatabaseHelper database) {
        return new Repository(service, database);
    }
}