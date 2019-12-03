package com.example.myapplication.cofig;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class PathImageBit {

    public static Bitmap fetchBitmapByFilePath(Context context,String path) {
        Bitmap bmp = null;
        try {
            bmp = Glide.with(context).asBitmap().load(path).submit().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return bmp;
    }


    public static Bitmap fetchBitmapByUrl(Context context,String url) {
        Bitmap bmp = null;
        try {
            bmp = Glide.with(context).asBitmap().load(url).submit().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return bmp;
    }

    public static Bitmap fetchBitmapByBase64(Context context,String encoded) {
        byte[] decode;
        encoded = encoded.split(",")[1];
        decode = Base64.decode(encoded, Base64.DEFAULT);
        Bitmap bmp = null;
        try {
            bmp = Glide.with(context).asBitmap().load(decode).submit().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return bmp;
    }

}

