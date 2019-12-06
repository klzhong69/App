package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import com.facebook.litho.Column;
import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.LithoView;
import com.facebook.litho.Row;
import com.facebook.litho.widget.Image;
import com.facebook.litho.widget.Text;

import static com.facebook.yoga.YogaEdge.ALL;

public class wechat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wechat);

        final ComponentContext context = new ComponentContext(this);

        final  Component component = Row.create(context)
                .paddingDip(ALL, 16)
                .backgroundColor(Color.WHITE)
                .child(
                        Text.create(context)
                                .text("Hello world")
                                .textSizeSp(20)
                                .widthDip(50))
                .child(
                        Image.create(context)
                                .drawableRes(R.drawable.a1)
                                .scaleType(ImageView.ScaleType.CENTER))
                .child(
                        Text.create(context)
                                .text("Hello world")
                                .textSizeSp(20)
                                .widthDip(50))
                .build();

        setContentView(LithoView.create(context, component));
    }


}
