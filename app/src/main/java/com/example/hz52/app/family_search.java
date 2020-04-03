package com.example.hz52.app;

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

import com.example.hz52.app.Adapter.FamilyViewAdapter;
import com.example.hz52.app.Entity.Familysea;
import com.example.hz52.app.Entity.MyApp;
import com.example.hz52.app.cofig.Preview;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

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
    private String searchtxt;
    private JsonArray users = new JsonArray();
    private JsonArray rooms = new JsonArray();
    private int a=0;
    private QMUITipDialog tipDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_search);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        searchtxt = intent.getStringExtra("searchtxt");
        title.setText("搜索：" + searchtxt);
        subtitle.setText("");
        textView2.setVisibility(View.GONE);
        textView3.setVisibility(View.VISIBLE);
        textView6.setVisibility(View.VISIBLE);
        textView7.setVisibility(View.GONE);
        okgo();
        tipDialog = new QMUITipDialog.Builder(this)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                .setTipWord("正在加载")
                .create();
        tipDialog.show();
    }

    private void init() {
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

                if(a==0){
                    Toast.makeText(family_search.this, position + " room", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(family_search.this, position + " user", Toast.LENGTH_SHORT).show();
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
        recycler3.setItemAnimator(defaultItemAnimator);
    }

    private void initData(int type){
        mArrayList = new ArrayList<Familysea>();
        if(type == 0){
            if (rooms.size() > 0) {
                for (int i = 0; i < rooms.size(); i++) {
                    Familysea i2 = new Familysea(rooms.get(i).getAsJsonObject().get("roomName").getAsString(), "ID" + rooms.get(i).getAsJsonObject().get("uniqueId").getAsString(), a+"", rooms.get(i).getAsJsonObject().get("coverUrl").getAsString(), rooms.get(i).getAsJsonObject().get("collectCount").getAsString());
                    mArrayList.add(i2);

                }
            }

        }else{
            if (users.size() > 0) {
                for (int i = 0; i < users.size(); i++) {
                    Familysea i1 = new Familysea(users.get(i).getAsJsonObject().get("nickname").getAsString(), "ID" + users.get(i).getAsJsonObject().get("uniqueId").getAsString(), a+"",  users.get(i).getAsJsonObject().get("avatarUrl").getAsString(), users.get(i).getAsJsonObject().get("fansCount").getAsString());
                    mArrayList.add(i1);

                }
            }
        }
        init();
    }

    private void okgo() {
        MyApp application = ((MyApp) this.getApplicationContext());
        mArrayList = new ArrayList<Familysea>();
        OkGo.<String>post(application.getUrl() + "/app/api/search")
                .params("content", searchtxt)
                .execute(new StringCallback() {

                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<String> response) {

                        Gson gson = new Gson();
                        Preview prexiew = gson.fromJson(response.body(), Preview.class);

                        if (prexiew.getCode() == 0) {

                            users = prexiew.getData().getAsJsonArray("users");
                            rooms = prexiew.getData().getAsJsonArray("rooms");
                            initData(0);
                            tipDialog.dismiss();
                        } else  {
                            tipDialog.dismiss();
                            Toast.makeText(family_search.this, prexiew.getMsg() + "", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }


    @OnClick({R.id.fold, R.id.textView2, R.id.textView6, R.id.textView3, R.id.textView7})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fold:
                this.finish();
                break;
            case R.id.textView2:
                a=0;
                textView2.setVisibility(View.GONE);
                textView3.setVisibility(View.VISIBLE);
                textView6.setVisibility(View.VISIBLE);
                textView7.setVisibility(View.GONE);
                initData(a);
                break;
            case R.id.textView6:
                a=1;
                textView2.setVisibility(View.VISIBLE);
                textView3.setVisibility(View.GONE);
                textView6.setVisibility(View.GONE);
                textView7.setVisibility(View.VISIBLE);
                initData(a);
                break;
            case R.id.textView3:
                a=0;
                initData(a);
                break;
            case R.id.textView7:
                a=1;
                initData(a);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        this.finish();
    }
}
