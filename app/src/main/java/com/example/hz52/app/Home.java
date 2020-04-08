package com.example.hz52.app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hz52.app.Entity.Homes;
import com.example.hz52.app.Entity.MyApp;
import com.example.hz52.app.Model.MessModel;
import com.example.hz52.app.cofig.Constant;
import com.example.hz52.app.cofig.Preview;
import com.example.hz52.app.cofig.TanTanCallback;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.mcxtzhang.commonadapter.rv.CommonAdapter;
import com.mcxtzhang.commonadapter.rv.OnItemClickListener;
import com.mcxtzhang.commonadapter.rv.ViewHolder;
import com.mcxtzhang.layoutmanager.swipecard.CardConfig;
import com.mcxtzhang.layoutmanager.swipecard.OverLayCardLayoutManager;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.agora.rtc.Constants;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class Home extends Fragment {


    @BindView(R.id.rv)
    RecyclerView mRv;
    @BindView(R.id.view2)
    View view2;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.imageView149)
    ImageView imageView149;
    @BindView(R.id.relative11)
    RelativeLayout relative11;
    private Unbinder unbinder;
    private Context context;
    private String slogin;
    private String userid;
    private CommonAdapter<Homes> mAdapter;
    private List<Homes> mDatas = new ArrayList<>();
    ;
    public static Observer<Integer> observer;
    private int in = 0;
    private int count = 10;
    private int type =1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_room, container, false);
        unbinder = ButterKnife.bind(this, view);
        context = getContext();
        //title.setText("首页");

        Window window = Objects.requireNonNull(getActivity()).getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(Color.TRANSPARENT);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);

        View decor = window.getDecorView();
        int ui = decor.getSystemUiVisibility();
        ui |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR; //设置状态栏中字体的颜色为黑色
        //ui &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR; //设置状态栏中字体颜色为白色
        decor.setSystemUiVisibility(ui);

        ViewGroup.LayoutParams para1;
        para1 = view2.getLayoutParams();
        para1.height = startup_page.height;
        view2.setLayoutParams(para1);

        /*ViewGroup.LayoutParams para2;
        para2 = mRv.getLayoutParams();
        para2.height = startup_page.cardheight;
        mRv.setLayoutParams(para2);*/

        SharedPreferences sp = Objects.requireNonNull(getContext()).getSharedPreferences("User", Context.MODE_PRIVATE);
        userid = sp.getString("userid", "");
        slogin = sp.getString("login", "");

        init();
        return view;

    }

    private void init() {
        initData();
        mRv.setLayoutManager(new OverLayCardLayoutManager());
        mRv.setAdapter(mAdapter = new CommonAdapter<Homes>(context, mDatas, R.layout.home_room_item) {
            static final String TAG = "zxt/Adapter";

            @NotNull
            @Override
            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                //Log.d(TAG, "onCreateViewHolder() called with: parent = [" + parent + "], viewType = [" + viewType + "]");
                return super.onCreateViewHolder(parent, viewType);
            }

            @Override
            public void onBindViewHolder(ViewHolder holder, int position) {
                //Log.d(TAG, "onBindViewHolder() called with: holder = [" + holder + "], position = [" + position + "]");
                super.onBindViewHolder(holder, position);
            }

            @Override
            public void convert(ViewHolder viewHolder, Homes homes) {
                //Log.d(TAG, "convert() called with: viewHolder = [" + viewHolder + "], swipeCardBean = [" + homes.getRoomname() + "]");
                Glide.with(context).load(R.drawable.mg).into((ImageView) (viewHolder.getView(R.id.imageViewi3)));
                Glide.with(context).load(homes.getUserima()).into((ImageView) (viewHolder.getView(R.id.imageViewi5)));
                viewHolder.setText(R.id.textViewi11, homes.getRoomname());

                if (homes.getRoomtype().equals("2")) {
                    viewHolder.setText(R.id.textViewi12, "黄金房间");
                }


            }

            @Override
            public void setListener(final int position, final ViewHolder viewHolder) {
                if (this.isEnabled(this.getItemViewType(position))) {
                    viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            if (slogin.equals("true")) {
                                if(isConnectIsNormal()){
                                    Intent intent = new Intent(getContext(), chatroom.class);
                                    //Constants.CLIENT_ROLE_AUDIENCE  听众
                                    //Constants.CLIENT_ROLE_BROADCASTER 主播
                                    intent.putExtra(Constant.ACTION_KEY_CROLE, Constants.CLIENT_ROLE_AUDIENCE);
                                    intent.putExtra(Constant.ACTION_KEY_ROOM_MODE, Constant.ChatRoomGamingStandard);
                                    intent.putExtra(Constant.ACTION_KEY_ROOM_ID, mDatas.get(position).getId());
                                    intent.putExtra(Constant.ACTION_KEY_TITLE_NAME, mDatas.get(position).getRoomname());
                                    startActivity(intent);
                                    Objects.requireNonNull(getActivity()).overridePendingTransition(R.anim.anim_bottom_in, R.anim.anim_bottom_out);
                                }
                            } else {
                                Intent intent1 = new Intent(getContext(), login.class);
                                intent1.putExtra("type", 0);
                                startActivity(intent1);
                                Objects.requireNonNull(getActivity()).overridePendingTransition(R.anim.anim_bottom_in, R.anim.anim_bottom_out);
                            }
                        }
                    });
                }
            }
        });

        CardConfig.initConfig(context);
        CardConfig.MAX_SHOW_COUNT = 4;//层数

        final TanTanCallback callback = new TanTanCallback(mRv, mAdapter, mDatas);
        callback.setHorizontalDeviation(0);

        //测试竖直滑动是否已经不会被移除屏幕
        //callback.setHorizontalDeviation(Integer.MAX_VALUE);

        final ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);

        itemTouchHelper.attachToRecyclerView(mRv);
    }

    /** 判断网络是否连接 */
    public boolean isConnectIsNormal() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info != null && info.isAvailable()) {
            String name = info.getTypeName();//网络类型
            return true;
        } else {
            Toast.makeText(getContext(),  " 无网络连接", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void initData() {
        for (int i = in; i < count; i++) {
            Homes i1 = new Homes("测试房间" + i, "226374470", "al.粉丝团", "热门", "CV", "德国", "1234", "https://momeak.oss-cn-shenzhen.aliyuncs.com/h4.jpg", "【芭比】uud小可爱", "2", "", "==《而福利社区点歌台》==", "我做了这么多改变，只为了我心中不变");
            mDatas.add(i1);
        }
        in = in + 10;
        count = count + 10;
    }


    @Override
    public void onResume() {
        super.onResume();
        //聊天
        observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {


            }

            @Override
            public void onNext(Integer integer) {
                if(mDatas.size()>0){
                    System.out.println("数量" + type);
                    if (mDatas.size()==type ) {
                        initData();
                    }
                    type++;
                    System.out.println("数量" + mDatas.size());
                }

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    static Home newInstance() {
        Bundle args = new Bundle();
        Home fragment = new Home();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }



    @OnClick({R.id.imageView149})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView149:
                Intent intent2 = new Intent(getContext(), search.class);
                startActivity(intent2);
                break;
        }
    }


    private void okgos() {
        MyApp application = ((MyApp) Objects.requireNonNull(getContext()).getApplicationContext());
        OkGo.<String>post(application.getUrl() + "/app/page/getHome")
                .params("count", 2)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        Gson gson = new Gson();
                        Preview prexiew = gson.fromJson(response.body(), Preview.class);

                        if (prexiew.getCode() == 0) {

                            JsonArray broadcasts = prexiew.getData().getAsJsonArray("home");

                            if (broadcasts.size() > 0) {
                                for (int i = 0; i < broadcasts.size(); i++) {
                                    String hot = broadcasts.get(i).getAsJsonObject().get("roomInfo").getAsString();
                                    String ownerId = broadcasts.get(i).getAsJsonObject().get("ownerId").getAsString();
                                    String uniqueId = broadcasts.get(i).getAsJsonObject().get("uniqueId").getAsString();
                                    String coverUrl = broadcasts.get(i).getAsJsonObject().get("coverUrl").getAsString();
                                    String roomName = broadcasts.get(i).getAsJsonObject().get("roomName").getAsString();
                                    String welcomeText = broadcasts.get(i).getAsJsonObject().get("welcomeText").getAsString();
                                    JsonArray tag = broadcasts.get(i).getAsJsonObject().get("tag").getAsJsonArray();
                                    String ownerName = broadcasts.get(i).getAsJsonObject().get("ownerName").getAsString();
                                }
                            }

                        } else {
                            Toast.makeText(getContext(), prexiew.getMsg() + "", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }


}
