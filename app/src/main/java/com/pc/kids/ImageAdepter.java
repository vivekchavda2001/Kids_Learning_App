package com.pc.kids;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class ImageAdepter extends PagerAdapter {
    private Context ctx;
    Drawable drawable;
    ArrayList<String> listImages;
    String task;
    TextToSpeech t1;


    ImageAdepter(Context context,String task) throws IOException {
        ctx = context;
        this.task = task;
        String[] images = ctx.getAssets().list(task);
        listImages = new ArrayList<String>(Arrays.asList(images));
        initilizeTtoS();
    }

   @Override
    public int getCount() {
      return listImages.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = new ImageView(ctx);
        try {

            InputStream inputstream=ctx.getAssets().open(task+"/"
                    +listImages.get(position));
            drawable = Drawable.createFromStream(inputstream, null);


        } catch (IOException e) {
            e.printStackTrace();
        }
        imageView.setTag(listImages.get(position));
        imageView.setImageDrawable(drawable);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        container.addView(imageView,0);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String toSpeak = listImages.get(position);
                toSpeak = toSpeak.substring(0,toSpeak.lastIndexOf("p")-1);
                Toast.makeText(ctx, ""+toSpeak, Toast.LENGTH_SHORT).show();
                t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
            }
        });
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ImageView) object);
    }
    public void initilizeTtoS(){
        t1=new TextToSpeech(ctx, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);
                }
            }
        });
    }
}
