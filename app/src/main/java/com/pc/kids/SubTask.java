package com.pc.kids;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class SubTask extends AppCompatActivity {
    ImageView imageView;
    Drawable drawable;
    ViewPager viewPager;
    int flag = 0;
    ImageView imvolumeon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_task);
        Intent  i = getIntent();
        String task = i.getStringExtra("taskName");
        getSupportActionBar().setTitle(task);
        imageView = findViewById(R.id.imageView);
        getSupportActionBar().setTitle(task);
         viewPager = findViewById(R.id.ViewPager);
        imvolumeon = findViewById(R.id.imvolumeon);


            imvolumeon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (flag == 0) {
                        imvolumeon.setImageResource(R.drawable.volume_off);
                        View v =  viewPager.getFocusedChild();
                        String t =  (v.getTag()).toString();
                        Toast.makeText(SubTask.this, "tag:"+t, Toast.LENGTH_SHORT).show();
                        flag = 1;
                    } else if (flag == 1) {
                        imvolumeon.setImageResource(R.drawable.volume_on);
                        flag = 0;
                    }
                }
            });



        try {
           ImageAdepter adepter = new ImageAdepter(this,task);
           viewPager.setAdapter(adepter);
       } catch (IOException e)
       {

       }
    }


    public void previousButton(View view) {
        //imageFlipper.showPrevious();
        viewPager.setCurrentItem(getItemofviewpager(-1), true);

    }
    public void nextButton(View view) {


        viewPager.setCurrentItem(getItemofviewpager(+1), true);

//        Drawable drawable = this.getResources().getDrawable(viewPager.getBackground());
//        String name = drawable.getCurrent().toString();
//        Toast.makeText(this, ""+name, Toast.LENGTH_SHORT).show();

    }
    private int getItemofviewpager(int i) {
        return viewPager.getCurrentItem() + i;
    }

}