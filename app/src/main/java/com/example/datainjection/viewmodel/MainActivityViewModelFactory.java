package com.example.datainjection.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.datainjection.model.MyRepository;


import javax.inject.Singleton;



@Singleton
public class MainActivityViewModelFactory implements ViewModelProvider.Factory {

    private final MyRepository myRepository;

    public MainActivityViewModelFactory(MyRepository myRepository) {
        this.myRepository = myRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MainActivityViewModel(myRepository);
    }
}
