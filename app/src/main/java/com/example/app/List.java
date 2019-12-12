package com.example.app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.Adapter.ListLeaderAdapter;
import com.example.app.Entity.Listleader;
import com.example.app.Model.HomePageModel;
import com.example.app.Model.ListModel;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;
import com.scwang.smart.refresh.header.MaterialHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class List extends Fragment {
    Unbinder unbinder;

    private static final String REFRESH_HEADER_PULLDOWN = "下拉可以刷新";
    private static final String REFRESH_HEADER_REFRESHING = "正在刷新...";
    private static final String REFRESH_HEADER_LOADING = "正在加载...";
    private static final String REFRESH_HEADER_RELEASE = "释放立即刷新";
    private static final String REFRESH_HEADER_FINISH = "刷新完成";
    private static final String REFRESH_HEADER_FAILED = "刷新失败";
    private static final String REFRESH_HEADER_SECONDARY = "释放进入二楼";
    private static final String REFRESH_HEADER_LASTTIME = "上次更新 M-d HH:mm";
    @BindView(R.id.imageView86)
    ImageView imageView86;
    @BindView(R.id.imageView28)
    QMUIRadiusImageView imageView28;
    @BindView(R.id.imageView87)
    QMUIRadiusImageView imageView87;
    @BindView(R.id.imageView88)
    QMUIRadiusImageView imageView88;
    @BindView(R.id.imageView89)
    ImageView imageView89;
    @BindView(R.id.imageView90)
    ImageView imageView90;
    @BindView(R.id.imageView91)
    ImageView imageView91;
    @BindView(R.id.textView102)
    TextView textView102;
    @BindView(R.id.imageView92)
    ImageView imageView92;
    @BindView(R.id.textView103)
    TextView textView103;
    @BindView(R.id.textView104)
    TextView textView104;
    @BindView(R.id.imageView93)
    ImageView imageView93;
    @BindView(R.id.textView105)
    TextView textView105;
    @BindView(R.id.textView106)
    TextView textView106;
    @BindView(R.id.imageView94)
    ImageView imageView94;
    @BindView(R.id.textView107)
    TextView textView107;
    @BindView(R.id.textView108)
    TextView textView108;
    @BindView(R.id.textView109)
    TextView textView109;
    @BindView(R.id.imageView18)
    QMUIRadiusImageView imageView18;
    @BindView(R.id.imageView19)
    QMUIRadiusImageView imageView19;
    @BindView(R.id.largeLabel2)
    RelativeLayout largeLabel2;
    @BindView(R.id.textView110)
    TextView textView110;
    @BindView(R.id.textView111)
    TextView textView111;
    @BindView(R.id.textView112)
    TextView textView112;
    @BindView(R.id.relative8)
    RelativeLayout relative8;
    @BindView(R.id.textView67)
    TextView textView67;
    @BindView(R.id.imageView21)
    QMUIRadiusImageView imageView21;
    @BindView(R.id.textView66)
    TextView textView66;
    @BindView(R.id.textView68)
    TextView textView68;
    @BindView(R.id.imageView66)
    ImageView imageView66;
    @BindView(R.id.textView113)
    TextView textView113;
    @BindView(R.id.textView114)
    TextView textView114;
    @BindView(R.id.recycler13)
    RecyclerView recycler13;
    @BindView(R.id.relative10)
    RelativeLayout relative10;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private ArrayList<Listleader> mArrayList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_leaderboard, container, false);
        unbinder = ButterKnife.bind(this, view);

        Context context = getContext();
        ListModel.initData();
        ListModel.initrecycler(context,recycler13);

        refreshLayout.setRefreshHeader(new MaterialHeader(getContext()).setScrollableWhenRefreshing(true));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshlayout) {
                //refreshlayout.autoRefresh();
                refreshlayout.finishRefresh(2000);

             /*   //创建Retrofit对象
                Observable.create(new ObservableOnSubscribe<Listleader>() {
                    @Override
                    public void subscribe(ObservableEmitter<Listleader> emitter) throws Exception {


                    }
                }).observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Listleader>() {
                            @Override
                            public void accept(Listleader entity) throws Exception {
                                //回调后在UI界面上展示出来
                                mAdapter.addData(mArrayList.size(), entity);
                                refreshlayout.finishLoadMore();
                            }
                        });*/
            }
        });



        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //这里接收到广播和数据，进行处理就是了

    }


    public static List newInstance() {
        Bundle args = new Bundle();
        List fragment = new List();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void request() {

       /* //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fy.iciba.com/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();

        // 步骤5:创建 网络请求接口 的实例
        IService request = retrofit.create(IService.class);

        //对 发送请求 进行封装
        Call<Translation> call = request.getCall();

        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<Translation>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<Translation> call, Response<Translation> response) {
                // 步骤7：处理返回的数据结果
                response.body().show();
                System.out.println(response.body());
            }

            //请求失败时回调
            @Override
            public void onFailure(Call<Translation> call, Throwable throwable) {
                System.out.println("连接失败");
                System.out.println(throwable.getMessage());
            }
        });
    }*/

        /*OkGo.<LzyResponse<ServerModel>>get(Urls.URL_JSONOBJECT)//
                .headers("aaa", "111")//
                .params("bbb", "222")//
                .converter(new JsonConvert<LzyResponse<ServerModel>>() {})//
                .adapt(new ObservableBody<LzyResponse<ServerModel>>())//
                .subscribeOn(Schedulers.io())//
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        showLoading();
                    }
                })//
                .map(new Function<LzyResponse<ServerModel>, ServerModel>() {
                    @Override
                    public ServerModel apply(@NonNull LzyResponse<ServerModel> response) throws Exception {
                        return response.data;
                    }
                })//
                .observeOn(AndroidSchedulers.mainThread())//
                .subscribe(new Observer<ServerModel>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(@NonNull ServerModel serverModel) {
                        handleResponse(serverModel);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();            //请求失败
                        showToast("请求失败");
                        handleError(null);
                    }

                    @Override
                    public void onComplete() {
                        dismissLoading();
                    }
                });*/

    }
}
