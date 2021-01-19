package com.pc.kids;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity{



    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerViewAdepter recyclerViewAdepter;
    ImageView imageView;

    int []arr = {R.drawable.alphabet,R.drawable.animal,R.drawable.colors,R.drawable.flower,R.drawable.shapes,R.drawable.vegetable,R.drawable.compass,R.drawable.transportation,R.drawable.week,R.drawable.schedule,R.drawable.bullfinch,R.drawable.cubes};
    String[] kids = {"Alphabet", "Animal", "Color", "Flower","Shapes", "Vegetable","Dirction","Transportation","Week","Month","Bird","Number"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerViewAdepter = new RecyclerViewAdepter(arr,kids,getApplicationContext());
        recyclerView.setAdapter(recyclerViewAdepter);
        recyclerView.setHasFixedSize(true);

    }
}