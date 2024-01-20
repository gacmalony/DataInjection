package com.example.datainjection.di;

import com.example.datainjection.MainActivity;


import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules= {AppModule.class, RepositoryModule.class})
public interface MyComponent {
    void inject(MainActivity mainActivity);
}
