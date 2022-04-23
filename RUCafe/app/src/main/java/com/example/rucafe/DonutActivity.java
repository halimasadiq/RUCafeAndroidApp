package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;

public class DonutActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private ArrayList<Donut> donuts = new ArrayList<>();

    private int[] donutHoleImages = {R.drawable.glazedholes, R.drawable.chocolateholes, R.drawable.powderedholes};

    private Spinner spinner;
    private ArrayAdapter<String> adapter;
    private String [] types = {"yeast donuts","cake donuts", "donut holes"};
    private ImageView donutTypeIV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donut);
        donutTypeIV = findViewById(R.id.donutTypeIV);
        donutTypeIV.setImageResource(R.drawable.yeastdonut);
        RecyclerView rcview = findViewById(R.id.rcView_menu);
        setupMenuItems(); //add the list of items to the ArrayList
        for(int i = 0; i< donuts.size(); i++){
            donuts.get(i).setImageID(donutHoleImages[i]);
        }
        DonutsAdapter donutsAdapter = new DonutsAdapter(this, donuts); //create the adapter
        rcview.setAdapter(donutsAdapter); //bind the list of items to the RecyclerView
        //use the LinearLayout for the RecyclerView
        rcview.setLayoutManager(new LinearLayoutManager(this));

        spinner = findViewById(R.id.donutType);
        adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item, types);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(adapterView.getSelectedItem().toString().equals("yeast donuts")){
                    donutTypeIV.setImageResource(R.drawable.yeastdonut);
                }
                if(adapterView.getSelectedItem().toString().equals("cake donuts")){
                    donutTypeIV.setImageResource(R.drawable.cakedonut);
                }
                if(adapterView.getSelectedItem().toString().equals("donut holes")){
                    donutTypeIV.setImageResource(R.drawable.donutholes);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                donutTypeIV.setImageResource(R.drawable.yeastdonut);
            }
        });

    }

    /**
     * Helper method to set up the data (the Model of the MVC).
     */
    private void setupMenuItems() {
        /*
         * Item names are defined in a String array under res/string.xml.
         * Your item names might come from other places, such as an external file, or the database
         * from the backend.
         */

        Donut d = new Donut();
        ArrayList<String> holeFlavors = d.getHoleFlavors();

        /* Add the items to the ArrayList.
         * Note that I use hardcoded prices for demo purpose. This should be replace by other
         * data sources.
         */
        for (int i = 0; i < holeFlavors.size(); i++) {
            donuts.add(new Donut("donut holes", holeFlavors.get(i)));
        }

        System.out.println(donuts.size());
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}