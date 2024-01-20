package com.example.datainjection;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import androidx.lifecycle.viewmodel.ViewModelInitializer;
import com.example.datainjection.databinding.ActivityMainBinding;
import com.example.datainjection.model.Category;
import com.example.datainjection.model.Karlsruhe;
import com.example.datainjection.viewmodel.MainActivityViewModel;
import com.example.datainjection.viewmodel.MainActivityViewModelFactory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel mainActivityViewModel;
    private ArrayList<Category> categoriesList;
    private ActivityMainBinding activityMainBinding;
    private MainActivityClickHandlers handlers;
    private Category selectedCategory;

    // RecyclerView
    private RecyclerView karlRecyclerview;
    private MyAdapter myAdapter;
    private ArrayList<Karlsruhe> karlsruheArrayList;
    private static final int ADD_KARL_REQUEST_CODE =1;
    private static final int EDIT_KARL_REQUEST_CODE =2;
    public int selectedKarlId;

    
    @Inject
    public MainActivityViewModelFactory mainActivityViewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        handlers = new MainActivityClickHandlers();
        activityMainBinding.setClickHandlers(handlers);

        // Before the dagger
        //mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        //Dagger2
        mainActivityViewModel = new ViewModelProvider(this, mainActivityViewModelFactory)
                .get(MainActivityViewModel.class);
        App.getApp().getMyComponent().inject(this);









        mainActivityViewModel.getAllCategories().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                categoriesList = (ArrayList<Category>) categories;


                for (Category c: categories){

                    Log.i("TAG", c.getCategoryName());
                }


                showOnSpinner();
            }
        });






    }

    private void showOnSpinner() {
        ArrayAdapter<Category> categoryArrayAdapter = new ArrayAdapter<>(
                this,
                R.layout.spinner_item,
                categoriesList
        );

        categoryArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        activityMainBinding.setSpinnerAdapter(categoryArrayAdapter);





    }

    public void LoadCoursesArrayList(int categoryID){
        mainActivityViewModel.getSelectedCategory(categoryID).observe(this, new Observer<List<Karlsruhe>>() {
            @Override
            public void onChanged(List<Karlsruhe> kars) {
                karlsruheArrayList = (ArrayList<Karlsruhe>) kars;
                LoadRecyclerView();
            }
        });
    }

    private void LoadRecyclerView() {
        karlRecyclerview = activityMainBinding.secondaryLayout.recyclerView;
        karlRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        karlRecyclerview.setHasFixedSize(true);

        myAdapter = new MyAdapter();
        karlRecyclerview.setAdapter(myAdapter);

        myAdapter.setKarl(karlsruheArrayList);


        // EDIT THE COURSE
        myAdapter.setListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Karlsruhe karlsruhe) {
                selectedKarlId = karlsruhe.getKarlId();

                Intent i = new Intent(MainActivity.this, AddEditActivity.class);
                i.putExtra(AddEditActivity.KARL_ID, selectedKarlId);
                i.putExtra(AddEditActivity.KARL_NAME, karlsruhe.getKarlName());
                i.putExtra(AddEditActivity.UNIT_PRICE, karlsruhe.getUnitPrice());

                startActivityIfNeeded(i,EDIT_KARL_REQUEST_CODE);


            }
        });

        // Delete A COURSE
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Karlsruhe karlsruheToDelete = karlsruheArrayList.get(viewHolder.getAdapterPosition());
                mainActivityViewModel.deleteData(karlsruheToDelete);
            }
        }).attachToRecyclerView(karlRecyclerview);


    }


    public class MainActivityClickHandlers{

        public void onFABClicked(View view){

            // CREATE THE COURSE
            Intent i = new Intent(MainActivity.this, AddEditActivity.class);
            startActivityIfNeeded(i, ADD_KARL_REQUEST_CODE);

        }


        public void onSelectItem(AdapterView<?> parent, View view, int pos, long id){
            selectedCategory = (Category) parent.getItemAtPosition(pos);

            String message = "id is: " + selectedCategory.getId() +
                    " \n name is " + selectedCategory.getCategoryName();

            Toast.makeText(parent.getContext(), " "+message, Toast.LENGTH_SHORT).show();

            LoadCoursesArrayList(selectedCategory.getId());

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);



        int selectedCategoryId = selectedCategory.getId();


        if (requestCode == ADD_KARL_REQUEST_CODE && resultCode == RESULT_OK){
            Karlsruhe karlsruhe = new Karlsruhe();

            karlsruhe.setCategoryId(selectedCategoryId);
            karlsruhe.setKarlName(data.getStringExtra(AddEditActivity.KARL_NAME));
            karlsruhe.setUnitPrice(data.getStringExtra(AddEditActivity.UNIT_PRICE));
            mainActivityViewModel.addNewData(karlsruhe);
            Log.v("TAG", "Inserted"+ karlsruhe.getUnitPrice());
        }
        else if(requestCode == EDIT_KARL_REQUEST_CODE && resultCode == RESULT_OK){

            Karlsruhe karlsruhe = new Karlsruhe();
            karlsruhe.setCategoryId(selectedCategoryId);
            karlsruhe.setKarlName(data.getStringExtra(AddEditActivity.KARL_NAME));
            karlsruhe.setUnitPrice(data.getStringExtra(AddEditActivity.UNIT_PRICE));

            karlsruhe.setKarlId(selectedKarlId);

            mainActivityViewModel.updateData(karlsruhe);

        }



    }
}
