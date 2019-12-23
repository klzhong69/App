package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.Adapter.GridViewAdapter;
import com.example.app.Adapter.ListLeaderAdapter;
import com.example.app.Adapter.ModifyViewAdapter;
import com.example.app.Entity.Modify;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class modify_information extends AppCompatActivity {


    @BindView(R.id.fold)
    ImageView fold;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.subtitle)
    TextView subtitle;
    @BindView(R.id.textView33)
    TextView textView33;
    @BindView(R.id.imageView2)
    QMUIRadiusImageView imageView2;
    @BindView(R.id.relativeLayout6)
    RelativeLayout relativeLayout6;
    @BindView(R.id.textView34)
    TextView textView34;
    @BindView(R.id.textView35)
    TextView textView35;
    @BindView(R.id.relativeLayout7)
    RelativeLayout relativeLayout7;
    @BindView(R.id.textView36)
    TextView textView36;
    @BindView(R.id.textView37)
    TextView textView37;
    @BindView(R.id.relativeLayout8)
    RelativeLayout relativeLayout8;
    @BindView(R.id.textView38)
    TextView textView38;
    @BindView(R.id.recycler2)
    RecyclerView recycler2;
    private ArrayList<Modify> mData;
    private ModifyViewAdapter mAdapters;
    private GridLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_information);
        ButterKnife.bind(this);
        title.setText("修改信息");
        subtitle.setText("");
        init();

        //创建适配器，将数据传递给适配器
        mAdapters = new ModifyViewAdapter(this, mData);
        //设置适配器adapter
        recycler2.setAdapter(mAdapters);

        //多列布局
        mLayoutManager = new GridLayoutManager(this, 5);
        recycler2.setLayoutManager(mLayoutManager);


        recycler2.setItemAnimator(new DefaultItemAnimator());

        mAdapters.setOnItemClickListener(new ModifyViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                int del = mData.size() - 1;
                if (position == del) {
                    Toast.makeText(modify_information.this, "添加图片", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(modify_information.this, position + " click", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });

        /**
         * 既然是动画，就会有时间，我们把动画执行时间变大一点来看一看效果
         */
        DefaultItemAnimator defaultItemAnimator = new DefaultItemAnimator();
        defaultItemAnimator.setAddDuration(200);
        defaultItemAnimator.setRemoveDuration(200);
        recycler2.setItemAnimator(defaultItemAnimator);
    }

    private void init() {
        mData = new ArrayList<Modify>();
        for (int i = 0; i < 12; i++) {
            Modify i1 = new Modify("https://momeak.oss-cn-shenzhen.aliyuncs.com/h2.jpg");
            mData.add(i1);
        }

        Modify i1 = new Modify("0");
        mData.add(mData.size(), i1);


    }

    @OnClick({R.id.fold, R.id.title, R.id.subtitle})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fold:
                this.finish();
                overridePendingTransition(R.animator.anim_left_in, R.animator.anim_right_out);
                break;
            case R.id.title:
                title.setText("修改信息");
                break;
            case R.id.subtitle:
                subtitle.setText("");
                break;
        }
    }
}
