package com.example.hz52.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hz52.app.Adapter.FindListAdapter;
import com.example.hz52.app.Adapter.FindmakeAdapter;
import com.example.hz52.app.Adapter.MusicViewAdapter;
import com.example.hz52.app.Entity.MyApp;
import com.example.hz52.app.Entity.Mymusic;
import com.example.hz52.app.Sqlentity.Music;
import com.example.hz52.app.cofig.Preview;
import com.example.hz52.app.dao.mMusicDao;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.leefeng.lfrecyclerview.LFRecyclerView;
import me.leefeng.lfrecyclerview.OnItemClickListener;


public class my_music_search extends AppCompatActivity implements OnItemClickListener, LFRecyclerView.LFRecyclerViewListener, LFRecyclerView.LFRecyclerViewScrollChange{

    @BindView(R.id.imageView37)
    ImageView imageView37;
    @BindView(R.id.search_view)
    SearchView searchView;
    @BindView(R.id.recycler3)
    LFRecyclerView recycler3;
    private ArrayList<Mymusic> mArrayList;
    private String queryTexts;
    private QMUITipDialog tipDialog;
    private boolean isKeyUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_music_search);
        ButterKnife.bind(this);

        initData();
    }

    private void init() {
        recycler3.setLoadMore(false);//设置为可上拉加载,默认false,调用这个方法false可以去掉底部的“加载更多”
        recycler3.setRefresh(false);// 设置为可下拉刷新,默认true
        recycler3.setAutoLoadMore(true);//设置滑动到底部自动加载,默认false
        recycler3.setOnItemClickListener(this);// 条目点击,点击和长按监听
        recycler3.setLFRecyclerViewListener(this);//下拉刷新上拉加载监听
        recycler3.setScrollChangeListener(this);//滑动监听
        recycler3.setItemAnimator(new DefaultItemAnimator());

        MusicViewAdapter mAdapter = new MusicViewAdapter(this, mArrayList);
            //设置适配器adapter
        recycler3.setAdapter(mAdapter);

        /**
         * 既然是动画，就会有时间，我们把动画执行时间变大一点来看一看效果
         */
        DefaultItemAnimator defaultItemAnimator = new DefaultItemAnimator();
        defaultItemAnimator.setAddDuration(200);
        defaultItemAnimator.setRemoveDuration(200);
        recycler3.setItemAnimator(defaultItemAnimator);
    }

    private void initData() {

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
        searchView.setQueryHint("搜索音乐名称");//设置查询提示字符串

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String queryText) {
                System.out.println("onQueryTextChange:" + queryText);
                return true;
            }

            @Override
            public boolean onQueryTextSubmit(String queryText) {
                searchView.clearFocus();
                //点击搜索
                okgo(queryText);
                tipDialog = new QMUITipDialog.Builder(my_music_search.this)
                        .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                        .setTipWord("正在加载")
                        .create();
                tipDialog.show();
                System.out.println("onQueryTextSubmit:" + queryText);
                return true;
            }
        });
    }

    private void okgo(String queryTexts) {
        mArrayList = new ArrayList<Mymusic>();
        SharedPreferences sp = getSharedPreferences("User", Context.MODE_PRIVATE);
        String userid = sp.getString("userid", "");
        String token = sp.getString("token", "");
        MyApp application = ((MyApp) getApplicationContext());

        OkGo.<String>post(application.getUrl() + "/app/user/getPackage?token=" + token)
                .params("userId", userid)
                .params("musiceName", queryTexts)
                .execute(new StringCallback() {

                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<String> response) {

                        Gson gson = new Gson();
                        Preview prexiew = gson.fromJson(response.body(), Preview.class);
                        JsonArray music = prexiew.getData().getAsJsonArray("music");
                        if(prexiew.getCode()==0){
                            /*for(int i=0;i<music.size();i++){
                                String size = music.get(i).getAsJsonObject().get("size").getAsString();
                                String duration = music.get(i).getAsJsonObject().get("duration").getAsString();
                                String md5 = music.get(i).getAsJsonObject().get("md5").getAsString();
                                String musicName = music.get(i).getAsJsonObject().get("musicName").getAsString();
                                String musicId = music.get(i).getAsJsonObject().get("musicId").getAsString();
                                String toTopTime = music.get(i).getAsJsonObject().get("toTopTime").getAsString();
                                String id = music.get(i).getAsJsonObject().get("id").getAsString();
                            }*/

                            for (int i = 0; i < 20; i++) {
                                Mymusic i1 = new Mymusic((long) i, "星坠-天空的幻想-林晓夜", "03.00", "-1", "0%", "");
                                mArrayList.add(i1);
                            }
                            java.util.List<Music> musics = mMusicDao.queryAll();
                            for (int i = 0; i < mArrayList.size(); i++) {
                                for (int j = 0; j < musics.size(); j++) {

                                    if (musics.get(j).getId().toString().equals(mArrayList.get(i).getId().toString())) {
                                        mArrayList.get(i).setType("-2");
                                        mArrayList.get(i).setUrl(musics.get(j).getFile());
                                    }
                                }
                            }
                            init();
                            tipDialog.dismiss();
                        } else {
                            tipDialog.dismiss();
                            Toast.makeText(my_music_search.this, prexiew.getMsg() + "", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }

    @OnClick(R.id.imageView37)
    public void onViewClicked() {
        this.finish();overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }


    @Override
    public void onBackPressed() {
        this.finish();overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onRecyclerViewScrollChange(View view, int i, int i1) {

    }

    @Override
    public void onClick(int i) {

    }

    @Override
    public void onLongClick(int i) {

    }
}
