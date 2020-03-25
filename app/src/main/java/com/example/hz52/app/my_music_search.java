package com.example.hz52.app;

import android.content.Context;
import android.content.SharedPreferences;
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

import com.example.hz52.app.Adapter.MusicViewAdapter;
import com.example.hz52.app.Entity.Familysea;
import com.example.hz52.app.Entity.MyApp;
import com.example.hz52.app.Entity.Mymusic;
import com.example.hz52.app.cofig.Preview;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.hz52.app.List.tipDialog;

public class my_music_search extends AppCompatActivity {

    @BindView(R.id.imageView37)
    ImageView imageView37;
    @BindView(R.id.search_view)
    SearchView searchView;
    @BindView(R.id.recycler3)
    RecyclerView recycler3;
    private ArrayList<Mymusic> mArrayList;
    private String queryTexts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_music_search);
        ButterKnife.bind(this);

        tipDialog = new QMUITipDialog.Builder(this)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                .setTipWord("正在加载")
                .create();
        tipDialog.show();

        init();

        initData();
        //适配器
        MusicViewAdapter mAdapter = new MusicViewAdapter(this, mArrayList);
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

        mAdapter.setOnItemClickListener(new MusicViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(my_music_search.this, position + " click", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {

                Toast.makeText(my_music_search.this, position + " Long click", Toast.LENGTH_SHORT).show();
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
        searchView.setQueryHint("搜索音乐名称");//设置查询提示字符串

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String queryText) {
                System.out.println("onQueryTextChange:" + queryText);
                return true;
            }

            @Override
            public boolean onQueryTextSubmit(String queryText) {
                //点击搜索
                queryTexts = queryText;
                System.out.println("onQueryTextSubmit:" + queryText);
                return true;
            }
        });

    }

    private void initData() {
        mArrayList = new ArrayList<Mymusic>();
        for (int i = 0; i < 10; i++) {
            Mymusic i1 = new Mymusic((long) i, "星坠-天空的幻想-林晓夜", "03.00", "2", "100%", "");
            mArrayList.add(i1);
        }
    }

    private void okgo() {
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
                            for(int i=0;i<music.size();i++){
                                String size = music.get(i).getAsJsonObject().get("size").getAsString();
                                String duration = music.get(i).getAsJsonObject().get("duration").getAsString();
                                String md5 = music.get(i).getAsJsonObject().get("md5").getAsString();
                                String musicName = music.get(i).getAsJsonObject().get("musicName").getAsString();
                                String musicId = music.get(i).getAsJsonObject().get("musicId").getAsString();
                                String toTopTime = music.get(i).getAsJsonObject().get("toTopTime").getAsString();
                                String id = music.get(i).getAsJsonObject().get("id").getAsString();
                            }
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
        this.finish();
        overridePendingTransition(R.animator.anim_left_in, R.animator.anim_right_out);
    }


    @Override
    public void onBackPressed() {
        this.finish();
        overridePendingTransition(R.animator.anim_left_in, R.animator.anim_right_out);
    }
}
