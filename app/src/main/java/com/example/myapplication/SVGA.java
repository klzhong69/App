package com.example.myapplication;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.http.HttpResponseCache;
import android.os.Bundle;
import android.text.TextPaint;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.cofig.PathImageBit;
import com.opensource.svgaplayer.SVGADrawable;
import com.opensource.svgaplayer.SVGADynamicEntity;
import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.SVGAParser;
import com.opensource.svgaplayer.SVGAVideoEntity;

import org.greenrobot.greendao.annotation.NotNull;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class SVGA extends AppCompatActivity {

    @BindView(R.id.svgaima)
    SVGAImageView svgaima;
    private Disposable mDisposable;
    SVGAParser parser;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_svg);
        ButterKnife.bind(this);
        parser = new SVGAParser(this);
        initData();
    }

    private void initData() {

        File cacheDir = new File(this.getCacheDir(), "https://data.meitehudong.com/52hertz/svga/posche.svga");
        System.out.println(cacheDir);
        try {
            HttpResponseCache.install(cacheDir, 1024 * 1024 * 128);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Observable.create(new ObservableOnSubscribe<Bitmap>() {

            @Override
            public void subscribe(ObservableEmitter<Bitmap> emitter) throws Exception {
                emitter.onNext(bitmap("https://momeak.oss-cn-shenzhen.aliyuncs.com/dear1.png"));
                emitter.onComplete();
            }

        }).subscribe(new Observer<Bitmap>() {

            @Override
            public void onSubscribe(Disposable d) {
                mDisposable = d;
            }

            @Override
            public void onNext(Bitmap value) {
                bitmap= value;
                loadAnimation();

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });


    }


    private Bitmap bitmap(String url) {
        return PathImageBit.fetchBitmapByUrl(SVGA.this, url);
    }

    private void loadAnimation() {
        // new URL needs try catch.

        try {
            parser.parse(new URL("https://data.meitehudong.com/52hertz/svga/posche.svga"), new SVGAParser.ParseCompletion() {
                @Override
                public void onComplete(@NotNull SVGAVideoEntity videoItem) {
                    SVGADynamicEntity dynamicEntity = new SVGADynamicEntity();

                    if (bitmap != null) {
                        dynamicEntity.setDynamicImage(bitmap, "99"); // Here is the KEY implementation.
                        TextPaint textPaint = new TextPaint();
                        textPaint.setColor(Color.WHITE);
                        textPaint.setTextSize(28);
                        dynamicEntity.setDynamicText("Pony send Kitty flowers.", textPaint, "banner");

                    }
                    SVGADrawable drawable = new SVGADrawable(videoItem, dynamicEntity);
                    svgaima.setImageDrawable(drawable);
                    svgaima.startAnimation();
                }

                @Override
                public void onError() {

                }
            });
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
