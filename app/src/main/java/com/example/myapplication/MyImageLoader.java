package com.example.myapplication;


import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;

public class MyImageLoader extends ImageLoader {

    @Override
    public void displayImage(Context context, Object path, ImageView imageView){
        //Uri

        Glide.with(context).load((String)path).into(imageView);
    }


}