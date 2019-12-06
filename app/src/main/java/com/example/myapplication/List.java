package com.example.myapplication;

import android.app.ListActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.cofig.IService;
import com.example.myapplication.Adapter.ListViewAdapter;
import com.example.myapplication.entity.Faxan;
import com.example.myapplication.utils.Translation;
import com.lzy.okgo.OkGo;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.MaterialHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.yanzhenjie.recyclerview.OnItemMenuClickListener;
import com.yanzhenjie.recyclerview.SwipeMenu;
import com.yanzhenjie.recyclerview.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.SwipeMenuItem;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;
import com.yanzhenjie.recyclerview.touch.OnItemMoveListener;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

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
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.largeLabel)
    LinearLayout largeLabel;

    private java.util.List<Faxan> mArrayList;
    private int layoutParamsHeight = 0;
    private double b;
    private Disposable mDisposable;
    private ListViewAdapter mAdapter;//适配器
    private LinearLayoutManager mLinearLayoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list, container, false);
        unbinder = ButterKnife.bind(this, view);

        refreshLayout.setRefreshHeader(new MaterialHeader(getContext()).setScrollableWhenRefreshing(true));

        refreshLayout.setRefreshFooter(new ClassicsFooter(getContext()));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshlayout) {
                //refreshlayout.autoRefresh();
                refreshlayout.finishRefresh();
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshlayout) {
                refreshlayout.autoLoadMore();
                //创建Retrofit对象
                Observable.create(new ObservableOnSubscribe<Faxan>() {
                    @Override
                    public void subscribe(ObservableEmitter<Faxan> emitter) throws Exception {
                        for (int i = mArrayList.size(); i < mArrayList.size()+10; i++) {
                            Faxan i1 = new Faxan("芭比Uki宝贝祝大叔生日快乐"+i, "[主持]芭比uu3号小可", "热门", "288", "635", "https://momeak.oss-cn-shenzhen.aliyuncs.com/dear1.png");
                            emitter.onNext(i1);
                        }

                    }
                }).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Faxan>() {
                            @Override
                            public void accept(Faxan entity) throws Exception {
                                //回调后在UI界面上展示出来
                                mAdapter.addData(mArrayList.size(), entity);
                                refreshlayout.finishLoadMore();
                            }
                        });

            }
        });

        //初始化数据
        init();

        SwipeRecyclerView listMode = view.findViewById(R.id.listMode);
        //创建适配器，将数据传递给适配器
        mAdapter = new ListViewAdapter(getContext(), mArrayList);

        listMode.setItemViewSwipeEnabled(false);// 开启滑动删除。默认关闭。
        listMode.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        // 创建菜单：
        SwipeMenuCreator mSwipeMenuCreator = new SwipeMenuCreator() {
            @Override
            public void onCreateMenu(SwipeMenu leftMenu, SwipeMenu rightMenu, int viewType) {

                // 在Item右侧添加一个菜单。
                SwipeMenuItem deleteItem = new SwipeMenuItem(getContext());
                deleteItem.setText("删除")
                        .setBackgroundColor(getResources().getColor(R.color.red_normal))
                        .setTextColor(Color.WHITE) // 文字颜色。
                        .setTextSize(15) // 文字大小。
                        .setWidth(140)
                        .setHeight(ViewGroup.LayoutParams.MATCH_PARENT);

                rightMenu.addMenuItem(deleteItem);

                // 注意：哪边不想要菜单，那么不要添加即可。
            }
        };
        // 设置监听器。
        listMode.setSwipeMenuCreator(mSwipeMenuCreator);

        OnItemMenuClickListener mMenuItemClickListener = new OnItemMenuClickListener() {
            @Override
            public void onItemClick(SwipeMenuBridge menuBridge, int adapterPosition) {
                menuBridge.closeMenu();
                mAdapter.removeData(adapterPosition);
            }
        };

        // 菜单点击监听。
        listMode.setOnItemMenuClickListener(mMenuItemClickListener);


        //设置适配器adapter
        listMode.setAdapter(mAdapter);

        mLinearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        listMode.setLayoutManager(mLinearLayoutManager);
        listMode.setItemAnimator(new DefaultItemAnimator());

        mAdapter.setOnItemClickListener(new ListViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getContext(), position + " click", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {

                Toast.makeText(getContext(), position + " Long click", Toast.LENGTH_SHORT).show();
            }
        });


        /**
         * 既然是动画，就会有时间，我们把动画执行时间变大一点来看一看效果
         */
        DefaultItemAnimator defaultItemAnimator = new DefaultItemAnimator();
        defaultItemAnimator.setAddDuration(200);
        defaultItemAnimator.setRemoveDuration(200);
        listMode.setItemAnimator(defaultItemAnimator);
        return view;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //这里接收到广播和数据，进行处理就是了

    }

    private void init() {
        mArrayList = new ArrayList<Faxan>();
        for (int i = 0; i < 10; i++) {
            Faxan i1 = new Faxan("芭比Uki宝贝祝大叔生日快乐" + i, "[主持]芭比uu3号小可", "热门", "288", "635", "https://momeak.oss-cn-shenzhen.aliyuncs.com/dear1.png");
            mArrayList.add(i1);
        }
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

        //创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fy.iciba.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        //创建网络请求接口实例
        IService request = retrofit.create(IService.class);
        //对网络请求进行封装，采用Observable<>方式
        Observable<Translation> observable = request.getCall();
        //发送网络请求（异步），这里是最大的不同之处
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//回到主线程处理请求结果
                .subscribe(new Observer<Translation>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        //初始化工作
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(Translation value) {
                        value.show();
                        //对返回结果JSTranslation进行处理
                    }

                    @Override
                    public void onError(Throwable e) {
                        //请求失败
                        mDisposable.dispose();
                    }

                    @Override
                    public void onComplete() {
                        //请求成功
                    }
                });

    }
}
