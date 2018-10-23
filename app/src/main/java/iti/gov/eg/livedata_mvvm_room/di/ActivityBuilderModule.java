package iti.gov.eg.livedata_mvvm_room.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import iti.gov.eg.livedata_mvvm_room.presentation.features.homeScreen.HomeActivity;
import iti.gov.eg.livedata_mvvm_room.presentation.features.homeScreen.HomeActivityModule;

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = HomeActivityModule.class)
    abstract HomeActivity bindMainActivity();
}