package com.example.hz52.app.Model;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hz52.app.Adapter.OnlinepeopleAdapter;
import com.example.hz52.app.Entity.Onlinepeople;
import com.qmuiteam.qmui.widget.dialog.QMUIBottomSheet;

import java.util.ArrayList;

public class OnlineModel {

    public static ArrayList<Onlinepeople> mArrayList = new ArrayList<Onlinepeople>();
    private static OnlinepeopleAdapter mAdapter;
    private static Context context;



    public static void initrecycler(Context contexts, RecyclerView recycler13) {
        context = contexts;
        //适配器
        mAdapter = new OnlinepeopleAdapter(context, mArrayList);
        //设置适配器adapter
        recycler13.setAdapter(mAdapter);

        /*LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mListView.setLayoutManager(mLinearLayoutManager);*/

        LinearLayoutManager layoutManager = new LinearLayoutManager(context) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recycler13.setLayoutManager(layoutManager);
        recycler13.setItemAnimator(new DefaultItemAnimator());

        mAdapter.setOnItemClickListener(new OnlinepeopleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if(position>0){
                    showSimpleBottomSheetList(
                            true, true, false, "选择您的操作？",
                            2, true, false);
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
        recycler13.setItemAnimator(defaultItemAnimator);


    }


    public static void Add(Context contexts, RecyclerView recycler13,Onlinepeople entity){
        if(mAdapter != null){
            mAdapter.addData(mArrayList.size(), entity);
        }else {
            initrecycler(contexts,recycler13);
            mAdapter.addData(mArrayList.size(), entity);
        }

    }


    private static void showSimpleBottomSheetList(boolean gravityCenter,
                                                  boolean addCancelBtn,
                                                  boolean withIcon,
                                                  CharSequence title,
                                                  int itemCount,
                                                  boolean allowDragDismiss,
                                                  boolean withMark) {
        QMUIBottomSheet.BottomListSheetBuilder builder = new QMUIBottomSheet.BottomListSheetBuilder(context);
        builder.setGravityCenter(gravityCenter)
                .setTitle(title)
                .setAddCancelBtn(addCancelBtn)
                .setAllowDrag(allowDragDismiss)
                .setNeedRightMark(withMark)
                .setOnSheetItemClickListener(new QMUIBottomSheet.BottomListSheetBuilder.OnSheetItemClickListener() {
                    @Override
                    public void onClick(QMUIBottomSheet dialog, View itemView, int position, String tag) {
                        dialog.dismiss();
                        if (position == 0) {

                        } else if (position == 1) {

                        }
                    }
                });
        if (withMark) {
            builder.setCheckedIndex(40);
        }
        builder.addItem("设为管理");
        builder.addItem("禁麦");
        builder.addItem("拉黑");
        builder.build().show();
    }

}
