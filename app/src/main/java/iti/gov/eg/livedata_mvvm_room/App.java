package iti.gov.eg.livedata_mvvm_room;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import iti.gov.eg.livedata_mvvm_room.di.DaggerAppComponent;
import timber.log.Timber;

public class App extends DaggerApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().create(this);
    }
}
