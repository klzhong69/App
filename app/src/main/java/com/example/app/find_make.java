package com.example.app;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.Adapter.FindgiftAdapter;
import com.example.app.Adapter.FindmakeAdapter;
import com.example.app.Entity.Findgift;
import com.example.app.Entity.Findmake;
import com.example.app.Model.ChatModel;
import com.example.app.Sqlentity.Chat;
import com.example.app.dao.mChatDao;
import com.qmuiteam.qmui.layout.QMUIPriorityLinearLayout;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class find_make extends AppCompatActivity {


    @BindView(R.id.imageView40)
    ImageView imageView40;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.textView6)
    TextView textView6;
    @BindView(R.id.imageView33)
    ImageView imageView33;
    @BindView(R.id.relativeLayout10)
    RelativeLayout relativeLayout10;
    @BindView(R.id.recycler15)
    RecyclerView recycler15;
    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.but)
    QMUIRoundButton but;
    @BindView(R.id.relativeLayout11)
    RelativeLayout relativeLayout11;
    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.textView7)
    TextView textView7;
    private ArrayList<Findmake> mArrayList;
    private ArrayList<Findgift> mArrayLists;
    private FindmakeAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_make);
        ButterKnife.bind(this);
        initData();
        init();
        textView2.setVisibility(View.GONE);
        textView3.setVisibility(View.VISIBLE);
        textView6.setVisibility(View.VISIBLE);
        textView7.setVisibility(View.GONE);

    }

    private void init() {
        //适配器
         mAdapter = new FindmakeAdapter(this, mArrayList);
        //设置适配器adapter
        recycler15.setAdapter(mAdapter);

        /*LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mListView.setLayoutManager(mLinearLayoutManager);*/

        LinearLayoutManager layoutManager = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recycler15.setLayoutManager(layoutManager);
        recycler15.setItemAnimator(new DefaultItemAnimator());

        mAdapter.setOnItemClickListener(new FindmakeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }

            @Override
            public void onItemLongClick(View view, int position) {

                Toast.makeText(find_make.this, position + " Long click", Toast.LENGTH_SHORT).show();
            }
        });

        /**
         * 既然是动画，就会有时间，我们把动画执行时间变大一点来看一看效果
         */
        DefaultItemAnimator defaultItemAnimator = new DefaultItemAnimator();
        defaultItemAnimator.setAddDuration(200);
        defaultItemAnimator.setRemoveDuration(200);
        recycler15.setItemAnimator(defaultItemAnimator);
    }

    private void inits() {
        //适配器
        FindgiftAdapter mAdapter = new FindgiftAdapter(this, mArrayLists);
        //设置适配器adapter
        recycler15.setAdapter(mAdapter);

        /*LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mListView.setLayoutManager(mLinearLayoutManager);*/

        LinearLayoutManager layoutManager = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recycler15.setLayoutManager(layoutManager);
        recycler15.setItemAnimator(new DefaultItemAnimator());

        mAdapter.setOnItemClickListener(new FindgiftAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(find_make.this, position + " click", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {

                Toast.makeText(find_make.this, position + " Long click", Toast.LENGTH_SHORT).show();
            }
        });

        /**
         * 既然是动画，就会有时间，我们把动画执行时间变大一点来看一看效果
         */
        DefaultItemAnimator defaultItemAnimator = new DefaultItemAnimator();
        defaultItemAnimator.setAddDuration(200);
        defaultItemAnimator.setRemoveDuration(200);
        recycler15.setItemAnimator(defaultItemAnimator);
    }

    private void initData() {
        mArrayList = new ArrayList<Findmake>();
        for (int i = 0; i < 4; i++) {

            Findmake i1 = new Findmake("https://momeak.oss-cn-shenzhen.aliyuncs.com/h3.jpg", "", "", "我是大大大", "我是大大大大");
            mArrayList.add(i1);
        }

    }

    private void initDatas() {

        mArrayLists = new ArrayList<Findgift>();

        for (int i = 0; i < 4; i++) {
            Findgift i2 = new Findgift("https://momeak.oss-cn-shenzhen.aliyuncs.com/h3.jpg", "https://momeak.oss-cn-shenzhen.aliyuncs.com/h4.jpg", "苗苗", "李描绘", "0", "1", "x50", "", "ID2563487");
            mArrayLists.add(i2);
        }

    }

    @OnClick({R.id.imageView40, R.id.textView2, R.id.textView3,R.id.textView7,R.id.textView6, R.id.but})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView40:
                this.finish();
                overridePendingTransition(R.animator.anim_left_in, R.animator.anim_right_out);
                break;
            case R.id.textView2:
                initData();
                init();
                textView2.setVisibility(View.GONE);
                textView3.setVisibility(View.VISIBLE);
                textView6.setVisibility(View.VISIBLE);
                textView7.setVisibility(View.GONE);
                break;
            case R.id.textView6:
                initDatas();
                inits();
                textView2.setVisibility(View.VISIBLE);
                textView3.setVisibility(View.GONE);
                textView6.setVisibility(View.GONE);
                textView7.setVisibility(View.VISIBLE);
                break;
            case R.id.textView3:
                initData();
                init();
                break;
            case R.id.textView7:
                initDatas();
                inits();
                break;
            case R.id.but:
                Findmake i1 = new Findmake("https://momeak.oss-cn-shenzhen.aliyuncs.com/h3.jpg", "", "", "我是大大大", editText.getText().toString());
                mAdapter.addData(0,i1);
                editText.setText("");
                break;
        }
    }

    @Override
    public void onBackPressed() {
        this.finish();
        overridePendingTransition(R.animator.anim_left_in, R.animator.anim_right_out);
    }
}
