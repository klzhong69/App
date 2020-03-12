package com.example.hz52.app.Model;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hz52.app.Adapter.ListLeaderAdapter;
import com.example.hz52.app.Entity.Listleader;
import com.example.hz52.app.Entity.MyApp;
import com.example.hz52.app.List;
import com.example.hz52.app.cofig.Preview;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;

public class ListModel {


    private static ArrayList<Listleader> mArrayList;
    private static ListLeaderAdapter mAdapter;


    public static void okgo(Context context, int rankListCategory, int durationCategory) {
        mArrayList = new ArrayList<Listleader>();
        MyApp application = ((MyApp) context.getApplicationContext());
        JSONArray json = null;
        OkGo.<String>post(application.getUrl() + "/app/page/ranklist")
                .params("rankListCategory", rankListCategory)
                .params("durationCategory", durationCategory)
                .execute(new StringCallback() {

                    @Override
                    public void onSuccess(Response<String> response) {

                        Gson gson = new Gson();
                        Preview prexiew = gson.fromJson(response.body(), Preview.class);

                        if (prexiew.getCode() == 0) {

                                JsonArray jsonArray =  prexiew.getData().getAsJsonArray("users");
                                for (int i = 0; i < jsonArray.size(); i++) {
                                    String type = "";
                                    if (rankListCategory == 1) {
                                        type = "财富值";
                                    } else if(rankListCategory == 2){
                                        type = "魅力值";
                                    }
                                    if (i >2) {
                                        Listleader i1 = new Listleader(i+1 + "", jsonArray.get(i).getAsJsonObject().get("avatarUrl").getAsString(), jsonArray.get(i).getAsJsonObject().get("nickname").getAsString(), type, jsonArray.get(i).getAsJsonObject().get("count").getAsString());
                                        mArrayList.add(i1);

                                    }


                                }
                                Observable<JsonArray> observable = Observable.defer(new Callable<ObservableSource<? extends JsonArray>>() {
                                    @Override
                                    public ObservableSource<? extends JsonArray> call() throws Exception {
                                        return Observable.just(jsonArray);
                                    }
                                });
                                observable.subscribe(List.observer);

                        } else if (prexiew.getCode() == 40000) {
                            Toast.makeText(context, prexiew.getMsg() + "", Toast.LENGTH_SHORT).show();
                        }


                    }
                });

    }



    public static void initrecycler(Context context, RecyclerView recycler13) {
        //适配器
        mAdapter = new ListLeaderAdapter(context, mArrayList);
        //设置适配器adapter
        recycler13.setAdapter(mAdapter);

        /*LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mListView.setLayoutManager(mLinearLayoutManager);*/

        LinearLayoutManager layoutManager = new LinearLayoutManager(context) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recycler13.setLayoutManager(layoutManager);
        recycler13.setItemAnimator(new DefaultItemAnimator());

        mAdapter.setOnItemClickListener(new ListLeaderAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {


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
        recycler13.setItemAnimator(defaultItemAnimator);


    }

    public static void refule(){
        mAdapter.notifyDataSetChanged();
    }

    public static void Add(RecyclerView mRecyclerView, Listleader entity) {
        mAdapter.addData(mArrayList.size(), entity);
        mRecyclerView.smoothScrollToPosition(mArrayList.size());
    }

}
