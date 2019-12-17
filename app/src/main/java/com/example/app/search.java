package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.Adapter.RecordAdapter;
import com.example.app.Adapter.SearchitemAdapter;
import com.example.app.Entity.Record;
import com.example.app.Entity.Searchitem;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class search extends AppCompatActivity {

    @BindView(R.id.imageView37)
    ImageView imageView37;
    @BindView(R.id.search_view)
    SearchView searchView;
    @BindView(R.id.imageView145)
    ImageView imageView145;
    @BindView(R.id.textView173)
    TextView textView173;
    @BindView(R.id.recyclerView3)
    RecyclerView recyclerView3;
    @BindView(R.id.textView178)
    TextView textView178;
    @BindView(R.id.imageView147)
    ImageView imageView147;
    private ArrayList<Searchitem> mArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        init();
        initData();
        //适配器
        SearchitemAdapter mAdapter = new SearchitemAdapter(this, mArrayList);
        //设置适配器adapter
        recyclerView3.setAdapter(mAdapter);

        /*LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mListView.setLayoutManager(mLinearLayoutManager);*/

        LinearLayoutManager layoutManager = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView3.setLayoutManager(layoutManager);
        recyclerView3.setItemAnimator(new DefaultItemAnimator());

        mAdapter.setOnItemClickListener(new SearchitemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(search.this, position + " click", Toast.LENGTH_SHORT).show();
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
        recyclerView3.setItemAnimator(defaultItemAnimator);
    }


    private void init() {

        searchView.setIconifiedByDefault(false);
        if (searchView == null) {
            return;
        } else {
            //获取到TextView的ID
            int id = searchView.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
            //获取到TextView的控件
            TextView textView = (TextView) searchView.findViewById(id);
            //设置字体大小为14sp
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);//14sp
            //设置提示文字颜色
            textView.setHintTextColor(getResources().getColor(R.color.back));

        }
        searchView.setFocusable(false);
        searchView.clearFocus();
        searchView.setIconifiedByDefault(false);
        searchView.setQueryHint("搜索家族名称、ID");//设置查询提示字符串

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String queryText) {
                System.out.println("onQueryTextChange:" + queryText);
                return true;
            }

            @Override
            public boolean onQueryTextSubmit(String queryText) {
                //点击搜索
                Intent intent3 = new Intent(search.this, family_search.class);
                startActivity(intent3);
                System.out.println("onQueryTextSubmit:" + queryText);
                return true;
            }
        });

    }

    private void initData() {
        mArrayList = new ArrayList<Searchitem>();
        for (int i = 0; i < 6; i++) {
            Searchitem i1 = new Searchitem("守望先锋");
            mArrayList.add(i1);
        }
    }

    @OnClick({R.id.imageView37, R.id.imageView145, R.id.textView178, R.id.imageView147})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView37:
                this.finish();
                break;
            case R.id.imageView145:
                searchView.setQuery("",false);
                break;
            case R.id.textView178:
            case R.id.imageView147:
                mArrayList.clear();
                break;
        }
    }
}
