package com.example.hz52.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hz52.app.Adapter.RecordAdapter;
import com.example.hz52.app.Entity.MyApp;
import com.example.hz52.app.Entity.Record;
import com.example.hz52.app.cofig.Preview;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class operation_record extends AppCompatActivity {

    @BindView(R.id.fold)
    ImageView fold;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.subtitle)
    TextView subtitle;
    @BindView(R.id.recyclerc11)
    RecyclerView recyclerc11;
    private ArrayList<Record> mArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operation_record);
        ButterKnife.bind(this);
        title.setText("操作记录");
        subtitle.setText("");
        initData();
        //适配器
        RecordAdapter mAdapter = new RecordAdapter(this, mArrayList);
        //设置适配器adapter
        recyclerc11.setAdapter(mAdapter);

        /*LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mListView.setLayoutManager(mLinearLayoutManager);*/

        LinearLayoutManager layoutManager = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerc11.setLayoutManager(layoutManager);
        recyclerc11.setItemAnimator(new DefaultItemAnimator());

        mAdapter.setOnItemClickListener(new RecordAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(operation_record.this, position + " click", Toast.LENGTH_SHORT).show();
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
        recyclerc11.setItemAnimator(defaultItemAnimator);
    }

    private void initData() {
        mArrayList = new ArrayList<Record>();
        for (int i = 0; i < 6; i++) {
            Record i1 = new Record("600", 0, "2019-10-30 12:30");
            mArrayList.add(i1);
        }


    }

    @OnClick(R.id.fold)
    public void onViewClicked() {
        this.finish();overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }


    private void okgo() {
        MyApp application = ((MyApp) this.getApplicationContext());
        SharedPreferences sp = getSharedPreferences("User", Context.MODE_PRIVATE);
        String userid = sp.getString("userid","");
        String token = sp.getString("token","");
        OkGo.<String>post(application.getUrl()+"/app/user/getMixedRecord?token="+token)
                .params("userId",userid)
                .execute(new StringCallback() {

                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<String> response) {

                        Gson gson = new Gson();
                        Preview prexiew = gson.fromJson(response.body(), Preview.class);
                        JsonArray records = prexiew.getData().getAsJsonArray("records");
                        if(prexiew.getCode()==0){
                            for(int i=0;i<records.size();i++){
                                String createdTime = records.get(i).getAsJsonObject().get("createdTime").getAsString();
                                String total = records.get(i).getAsJsonObject().get("total").getAsString();
                                String type = records.get(i).getAsJsonObject().get("type").getAsString();
                            }
                        }else {
                            Toast.makeText(operation_record.this, prexiew.getMsg()+"", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }

    @Override
    public void onBackPressed() {
        this.finish();overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
}
