package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Field;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class search extends AppCompatActivity {

    @BindView(R.id.search_view)
    SearchView mSearchView;
    @BindView(R.id.textView5)
    TextView textView5;
    @BindView(R.id.relativeLayout4)
    RelativeLayout relativeLayout4;
    @BindView(R.id.textView40)
    TextView textView40;
    @BindView(R.id.relayout)
    RecyclerView relayout;
    @BindView(R.id.imageView47)
    ImageView imageView47;
    @BindView(R.id.textView41)
    TextView textView41;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        mSearchView.setFocusable(false);
        mSearchView.clearFocus();
        mSearchView.setIconifiedByDefault(false);
        mSearchView.setQueryHint("ahdewoi");//设置查询提示字符串
//        mSearchView.setSubmitButtonEnabled(true);//设置是否显示搜索框展开时的提交按钮
        //设置SearchView下划线透明
        setUnderLinetransparent(mSearchView);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String queryText) {
                System.out.println("onQueryTextChange:" + queryText);
                return true;
            }

            @Override
            public boolean onQueryTextSubmit(String queryText) {
                System.out.println("onQueryTextSubmit:" + queryText);
                return true;
            }
        });

    }

    /**
     * 设置SearchView下划线透明
     **/
    private void setUnderLinetransparent(SearchView searchView) {
        try {
            Class<?> argClass = searchView.getClass();
            // mSearchPlate是SearchView父布局的名字
            Field ownField = argClass.getDeclaredField("mSearchPlate");
            ownField.setAccessible(true);
            View mView = (View) ownField.get(searchView);
            mView.setBackgroundColor(Color.TRANSPARENT);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.textView5, R.id.imageView47, R.id.textView41})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.textView5:
                this.finish();
                break;
            case R.id.imageView47:
                break;
            case R.id.textView41:
                break;
        }
    }
}
