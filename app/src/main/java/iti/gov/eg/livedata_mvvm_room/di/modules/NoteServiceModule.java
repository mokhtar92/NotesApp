package iti.gov.eg.livedata_mvvm_room.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import iti.gov.eg.livedata_mvvm_room.repository.server.INoteService;
import iti.gov.eg.livedata_mvvm_room.repository.server.NoteService;

@Module(includes = INoteServiceModule.class)
public class NoteServiceModule {

    @Provides
    @Singleton
    NoteService providesNoteService(INoteService iNoteService) {
        return new NoteService(iNoteService);
    }
}
