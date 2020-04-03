package com.example.hz52.app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hz52.app.Adapter.SearchitemAdapter;
import com.example.hz52.app.Entity.Searchitem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class search extends AppCompatActivity {

    @BindView(R.id.imageView37)
    ImageView imageView37;
    @BindView(R.id.search_view)
    SearchView searchView;
    @BindView(R.id.textView173)
    TextView textView173;
    @BindView(R.id.recyclerView3)
    RecyclerView recyclerView3;
    @BindView(R.id.textView178)
    TextView textView178;
    @BindView(R.id.imageView147)
    ImageView imageView147;
    private ArrayList<Searchitem> mArrayList;
    private SharedPreferences sp;
    private String Text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        sp = getSharedPreferences("User_LIST", Context.MODE_PRIVATE);
        init();

    }

    @Override
    protected void onStart(){
        super.onStart();

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
                ImageView imageView =  view.findViewById(R.id.imageView146);
                TextView textView =  view.findViewById(R.id.textView177);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mAdapter.removeData(position);

                        Gson user_gson = new Gson();
                        String user_jsonstr = user_gson.toJson(mArrayList);
                        System.out.println("user_list"+user_jsonstr);
                        if(user_jsonstr.equals("[]")){
                            sp.edit().putString("user_list", "").apply();
                            textView173.setVisibility(View.GONE);
                            recyclerView3.setVisibility(View.GONE);
                            textView178.setVisibility(View.GONE);
                            imageView147.setVisibility(View.GONE);
                        }else{
                            sp.edit().putString("user_list", user_jsonstr).apply();
                        }


                    }
                });

                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent3 = new Intent(search.this, family_search.class);
                        intent3.putExtra("searchtxt",mArrayList.get(position).getTxt());
                        startActivity(intent3);
                        

                    }
                });

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
                 Text = queryText;
                return true;
            }

            @Override
            public boolean onQueryTextSubmit(String queryText) {
                //点击搜索
                Searchitem searchitem = new Searchitem(queryText);
                mArrayList.add(searchitem);

                Gson user_gson = new Gson();
                String user_jsonstr = user_gson.toJson(mArrayList);
                sp.edit().putString("user_list", user_jsonstr).apply();
                Intent intent3 = new Intent(search.this, family_search.class);
                intent3.putExtra("searchtxt",queryText);
                startActivity(intent3);
                
                return true;
            }
        });

    }

    private void initData() {
        mArrayList = new ArrayList<Searchitem>();
        textView173.setVisibility(View.VISIBLE);
        recyclerView3.setVisibility(View.VISIBLE);
        textView178.setVisibility(View.VISIBLE);
        imageView147.setVisibility(View.VISIBLE);
        String user_json = sp.getString("user_list","");
        if(user_json.equals("")){
            textView173.setVisibility(View.GONE);
            recyclerView3.setVisibility(View.GONE);
            textView178.setVisibility(View.GONE);
            imageView147.setVisibility(View.GONE);
        }else{
            Gson user_gson = new Gson();
            mArrayList = user_gson.fromJson(user_json,new TypeToken<ArrayList<Searchitem>>(){}.getType());
        }
    }

    @OnClick({R.id.imageView37,  R.id.textView178, R.id.imageView147})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView37:
                this.finish();
                break;
            case R.id.textView178:
            case R.id.imageView147:
                mArrayList.clear();
                textView173.setVisibility(View.GONE);
                recyclerView3.setVisibility(View.GONE);
                textView178.setVisibility(View.GONE);
                imageView147.setVisibility(View.GONE);
                break;
        }
    }



    @Override
    public void onBackPressed() {
        this.finish();
    }
}
