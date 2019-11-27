package com.example.myapplication;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import butterknife.BindView;
import butterknife.ButterKnife;


public class Main3Activity extends AppCompatActivity {


    @BindView(R.id.scroll)
    NestedScrollView scroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        scroll.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {   //scrollY是滑动的距离
                if(scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())){
                    //滑动到底部
                    System.out.println("Scroll UP");
                }
            }
        });
    }
}
