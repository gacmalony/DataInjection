package com.example.datainjection.model;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyRepository {

    private CategoryDAO categoryDAO;
    private KarlsruheDAO karlsruheDAO;

    private LiveData<List<Category>> categories;
    private LiveData<List<Karlsruhe>> datas;

    public MyRepository(Application application) {
        MyDatabase myDatabase = MyDatabase.getInstance(application);
        categoryDAO = myDatabase.categoryDAO();
        karlsruheDAO = myDatabase.karlsruheDAO();
    }

    public LiveData<List<Category>> getCategories() {
        return categoryDAO.getAllCategories();
    }

    public LiveData<List<Karlsruhe>> getData (int categoryId) {
        return karlsruheDAO.getKarlsruhe(categoryId);
    }


    private void insertCategory(Category category){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(new Runnable() {
            @Override
            public void run() {

                // Inserting Categories
                categoryDAO.insert(category);
            }
        });

    }

    public void insertData(Karlsruhe karlsruhe){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executor.execute(new Runnable() {
            @Override
            public void run() {

                // Inserting Categories
                karlsruheDAO.insert(karlsruhe);

                // Do after background execution is done - post execution
            }
        });

    }

    public void deleteCategory(Category category){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executor.execute(new Runnable() {
            @Override
            public void run() {

                // Inserting Categories
                categoryDAO.delete(category);

                // Do after background execution is done - post execution
            }
        });
    }

    public void deleteData(Karlsruhe karlsruhe){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executor.execute(new Runnable() {
            @Override
            public void run() {

                // Inserting Categories
                karlsruheDAO.delete(karlsruhe);

                // Do after background execution is done - post execution
            }
        });
    }

    public void updateCategory(Category category){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(new Runnable() {
            @Override
            public void run() {

                // Inserting Categories
                categoryDAO.update(category);

                // Do after background execution is done - post execution
            }
        });
    }

    public void updateData(Karlsruhe karlsruhe){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(new Runnable() {
            @Override
            public void run() {

                // Inserting Categories
                karlsruheDAO.update(karlsruhe);

                // Do after background execution is done - post execution
            }
        });
    }
}
