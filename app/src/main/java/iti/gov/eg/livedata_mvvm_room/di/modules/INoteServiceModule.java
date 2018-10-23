package iti.gov.eg.livedata_mvvm_room.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import iti.gov.eg.livedata_mvvm_room.repository.server.ApiClient;
import iti.gov.eg.livedata_mvvm_room.repository.server.INoteService;

@Module
public class INoteServiceModule {

    @Provides
    @Singleton
    INoteService provideINoteService() {
        return ApiClient.getClient().create(INoteService.class);
    }
}