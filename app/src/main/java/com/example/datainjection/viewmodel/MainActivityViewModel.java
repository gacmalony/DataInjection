package com.example.datainjection.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;


import com.example.datainjection.model.Category;
import com.example.datainjection.model.Karlsruhe;
import com.example.datainjection.model.MyRepository;

import java.util.List;

public class MainActivityViewModel extends ViewModel {

    // RepositoryModule
    private MyRepository repository;

    // Live Data
    private LiveData<List<Category>> allCategories;
    private LiveData<List<Karlsruhe>> selectedCategory;


    public MainActivityViewModel(MyRepository myRepository) {
        this.repository = myRepository;
    }

    public LiveData<List<Category>> getAllCategories(){
        allCategories = repository.getCategories();
        return allCategories;
    }

    public LiveData<List<Karlsruhe>> getSelectedCategory(int categoryId){
        selectedCategory = repository.getData(categoryId);
        return selectedCategory;
    }

    public void addNewData(Karlsruhe karlsruhe){
        repository.insertData(karlsruhe);
    }

    public void updateData(Karlsruhe karlsruhe){
        repository.updateData(karlsruhe);
    }

    public void deleteData (Karlsruhe karlsruhe){
        repository.deleteData(karlsruhe);
    }



}
