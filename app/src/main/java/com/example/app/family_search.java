package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.Adapter.FamilyViewAdapter;
import com.example.app.Entity.Familysea;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class family_search extends AppCompatActivity {


    @BindView(R.id.fold)
    ImageView fold;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.subtitle)
    TextView subtitle;
    @BindView(R.id.recycler3)
    RecyclerView recycler3;
    @BindView(R.id.imageView148)
    ImageView imageView148;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.textView6)
    TextView textView6;
    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.textView7)
    TextView textView7;
    private List<Familysea> mArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_search);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String searchtxt = intent.getStringExtra("searchtxt");
        title.setText("搜索："+searchtxt);
        subtitle.setText("");
        textView2.setVisibility(View.GONE);
        textView3.setVisibility(View.VISIBLE);
        textView6.setVisibility(View.VISIBLE);
        textView7.setVisibility(View.GONE);

        initData();
        init();
    }

    private void init(){
        //适配器
        FamilyViewAdapter mAdapter = new FamilyViewAdapter(this, mArrayList);
        //设置适配器adapter
        recycler3.setAdapter(mAdapter);

        /*LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mListView.setLayoutManager(mLinearLayoutManager);*/

        LinearLayoutManager layoutManager = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recycler3.setLayoutManager(layoutManager);
        recycler3.setItemAnimator(new DefaultItemAnimator());

        mAdapter.setOnItemClickListener(new FamilyViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(family_search.this, position + " click", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {

                Toast.makeText(family_search.this, position + " Long click", Toast.LENGTH_SHORT).show();
            }
        });

        /**
         * 既然是动画，就会有时间，我们把动画执行时间变大一点来看一看效果
         */
        DefaultItemAnimator defaultItemAnimator = new DefaultItemAnimator();
        defaultItemAnimator.setAddDuration(200);
        defaultItemAnimator.setRemoveDuration(200);
        recycler3.setItemAnimator(defaultItemAnimator);
    }
    private void initData() {
        mArrayList = new ArrayList<Familysea>();
        Familysea i1 = new Familysea("芭比UU王国", "ID" + "25634896", "1", "0", "https://momeak.oss-cn-shenzhen.aliyuncs.com/dear1.png","2569");
        mArrayList.add(i1);
        Familysea i2 = new Familysea("芭比UU王国", "ID" + "25634896", "0", "0", "https://momeak.oss-cn-shenzhen.aliyuncs.com/dear2.png","2569");
        mArrayList.add(i2);
        Familysea i3 = new Familysea("芭比UU王国", "ID" + "25634896", "0", "1", "https://momeak.oss-cn-shenzhen.aliyuncs.com/dear3.png","2569");
        mArrayList.add(i3);
    }


    @OnClick({R.id.fold, R.id.textView2, R.id.textView6, R.id.textView3, R.id.textView7})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fold:
                this.finish();
                break;
            case R.id.textView2:
                textView2.setVisibility(View.GONE);
                textView3.setVisibility(View.VISIBLE);
                textView6.setVisibility(View.VISIBLE);
                textView7.setVisibility(View.GONE);
                break;
            case R.id.textView6:
                textView2.setVisibility(View.VISIBLE);
                textView3.setVisibility(View.GONE);
                textView6.setVisibility(View.GONE);
                textView7.setVisibility(View.VISIBLE);
                break;
            case R.id.textView3:
                break;
            case R.id.textView7:
                break;
        }
    }
}
