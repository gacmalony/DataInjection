package com.example.datainjection;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.datainjection.R;
import com.example.datainjection.databinding.ActivityAddEditBinding;
import com.example.datainjection.model.Karlsruhe;
import com.example.datainjection.AddEditActivity;

public class AddEditActivity extends AppCompatActivity {

    private Karlsruhe karl;
    public static final String KARL_ID = "karlId";
    public static final String KARL_NAME = "karlName";
    public static final String UNIT_PRICE = "unitPrice";
    private ActivityAddEditBinding activityAddEditBinding;
    private AddAndEditActivityClickHandlers clickHandlers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);

        karl = new Karlsruhe();
        activityAddEditBinding = DataBindingUtil.setContentView(this,
                R.layout.activity_add_edit);
        activityAddEditBinding.setKarlsruhe(karl);


        clickHandlers = new AddAndEditActivityClickHandlers(this);
        activityAddEditBinding.setClickHandler(clickHandlers);


        Intent i = getIntent();
        if (i.hasExtra(KARL_ID)){
            // RecyclerView item Clicked

            setTitle("Edit Karlsruhe");
            karl.setKarlName(i.getStringExtra(KARL_NAME));
            karl.setUnitPrice(i.getStringExtra(UNIT_PRICE));

        }else{

            // FAB is Clicked
            setTitle("Create New Karlsruhe");
        }


    }



    public class AddAndEditActivityClickHandlers{
        Context context;

        public AddAndEditActivityClickHandlers(Context context) {
            this.context = context;
        }

        public void onSubmitButtonClicked(View view){
            if (karl.getKarlName() == null){
                Toast.makeText(context, "Karlsruhe Name is Empty", Toast.LENGTH_SHORT).show();
            }else{
                Intent i = new Intent();
                i.putExtra(KARL_NAME, karl.getKarlName());
                i.putExtra(UNIT_PRICE, karl.getUnitPrice());
                setResult(RESULT_OK, i);
                Log.v("TAG", karl.getKarlName());
                finish();
            }
        }
    }
}