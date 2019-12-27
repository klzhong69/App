package com.example.app.Model;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.app.Adapter.GiftheadAdapter;
import com.example.app.Adapter.RoomheadchatAdapter;
import com.example.app.Entity.Gifthead;
import com.example.app.Entity.Roomheadchat;
import com.example.app.Entity.Roomtxt;
import com.example.app.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GiftheadModel {

    private static ArrayList<Gifthead> mData;
    private static GiftheadAdapter mAdapters;

    private static Map<Integer, Boolean> map;
    private static int a = 0;

    public static void initData() {

        mData = new ArrayList<Gifthead>();
        map = new HashMap<Integer, Boolean>();
        for (int i = 0; i < 6; i++) {
            int sum ;
            if(i>5){
                sum = i-5;
            }else{
                sum = i+1;
            }
            Gifthead i1 = new Gifthead("https://momeak.oss-cn-shenzhen.aliyuncs.com/h"+sum+".jpg", "");
            mData.add(i1);
            map.put(i, false);
        }

    }

    public static void initrecyclers(Context context, RecyclerView gridview, TextView txt) {
        mAdapters = new GiftheadAdapter(context, mData);
        //设置适配器adapter
        gridview.setAdapter(mAdapters);

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        gridview.setLayoutManager(layoutManager);

        gridview.setItemAnimator(new DefaultItemAnimator());

        mAdapters.setOnItemClickListener(new GiftheadAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ImageView imageView = view.findViewById(R.id.imageView107);
                if (map.get(position)) {
                    map.put(position, false);
                    Glide.with(context).load("").into(imageView);
                    a--;
                    txt.setText(a + "");
                    if (a == 0) {
                        txt.setVisibility(View.GONE);
                    } else {
                        txt.setVisibility(View.VISIBLE);
                    }
                } else {
                    map.put(position, true);
                    Glide.with(context).load(R.drawable.qmui_icon_checkbox_checked).into(imageView);
                    a++;
                    txt.setText(a + "");
                    if (a == 0) {
                        txt.setVisibility(View.GONE);
                    } else {
                        txt.setVisibility(View.VISIBLE);
                    }
                }

            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(context, position + " Long click", Toast.LENGTH_SHORT).show();
            }
        });

        /**
         * 既然是动画，就会有时间，我们把动画执行时间变大一点来看一看效果
         */
        DefaultItemAnimator defaultItemAnimator = new DefaultItemAnimator();
        defaultItemAnimator.setAddDuration(200);
        defaultItemAnimator.setRemoveDuration(200);
        gridview.setItemAnimator(defaultItemAnimator);


    }

    public static void Add(RecyclerView mRecyclerView, Gifthead entity) {
        mAdapters.addData(mData.size(), entity);
        mRecyclerView.smoothScrollToPosition(mData.size());
    }
}
