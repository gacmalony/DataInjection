package com.example.datainjection.di;

import android.app.Application;

import com.example.datainjection.model.MyRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {
    @Singleton
    @Provides
    MyRepository providertherepository(Application application){
        return new MyRepository(application);
    }
}
