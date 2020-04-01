package com.example.hz52.app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Rect;
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

    CommonAdapter<Homes> mAdapter;
    List<Homes> mDatas;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_room, container, false);
        unbinder = ButterKnife.bind(this, view);
        context = getContext();
        //title.setText("首页");

        Window window = Objects.requireNonNull(getActivity()).getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }

        View decor = window.getDecorView();
        int ui = decor.getSystemUiVisibility();
        ui |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR; //设置状态栏中字体的颜色为黑色
        //ui &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR; //设置状态栏中字体颜色为白色
        decor.setSystemUiVisibility(ui);

        ViewGroup.LayoutParams para1;
        para1 = view2.getLayoutParams();
        para1.height = startup_page.height;
        view2.setLayoutParams(para1);

        ViewGroup.LayoutParams para2;
        para2 = mRv.getLayoutParams();
        para2.height = startup_page.cardheight;
        mRv.setLayoutParams(para2);

        SharedPreferences sp = Objects.requireNonNull(getContext()).getSharedPreferences("User", Context.MODE_PRIVATE);
        userid = sp.getString("userid", "");
        slogin = sp.getString("login", "");

        init();
        return view;

    }

    private void init() {
        mRv.setLayoutManager(new OverLayCardLayoutManager());
        mRv.setAdapter(mAdapter = new CommonAdapter<Homes>(getContext(), mDatas = initDatas(), R.layout.home_room_item) {
            static final String TAG = "zxt/Adapter";

            @NotNull
            @Override
            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                Log.d(TAG, "onCreateViewHolder() called with: parent = [" + parent + "], viewType = [" + viewType + "]");
                return super.onCreateViewHolder(parent, viewType);
            }

            @Override
            public void onBindViewHolder(ViewHolder holder, int position) {
                Log.d(TAG, "onBindViewHolder() called with: holder = [" + holder + "], position = [" + position + "]");
                super.onBindViewHolder(holder, position);
            }

            @Override
            public CommonAdapter setOnItemClickListener(OnItemClickListener onItemClickListener) {

                System.out.println("这里"+onItemClickListener);
                return super.setOnItemClickListener(onItemClickListener);
            }

            @Override
            public void convert(ViewHolder viewHolder, Homes homes) {
                Log.d(TAG, "convert() called with: viewHolder = [" + viewHolder + "], swipeCardBean = [" + homes + "]");
                viewHolder.setText(R.id.textViewi1,homes.getRoomname());
                viewHolder.setText(R.id.textViewi2,homes.getId());
                viewHolder.setText(R.id.textViewi3,homes.getFamilyname());
                viewHolder.setText(R.id.textViewi4,homes.getLabel1());
                viewHolder.setText(R.id.textViewi5,homes.getLabel2());
                viewHolder.setText(R.id.textViewi6,homes.getLabel3());
                viewHolder.setText(R.id.textViewi7,homes.getOnlinepeople());
                Glide.with(Objects.requireNonNull(getContext())).load(R.drawable.s1).into((ImageView)(viewHolder.getView(R.id.imageViewi3)));
                viewHolder.setText(R.id.textViewi9,homes.getTitle());
                viewHolder.setText(R.id.textViewi10,homes.getTxt());
                Glide.with(getContext()).load(homes.getUserima()).into((ImageView)(viewHolder.getView(R.id.imageViewi5)));
                viewHolder.setText(R.id.textViewi11,homes.getUsername());

                if (homes.getRoomtype().equals("2")) {
                    viewHolder.setText(R.id.textViewi12,"黄金房间");
                }
            }
        });

        CardConfig.initConfig(Objects.requireNonNull(getContext()));

        final TanTanCallback callback = new TanTanCallback(mRv, mAdapter, mDatas);

        //测试竖直滑动是否已经不会被移除屏幕
        //callback.setHorizontalDeviation(Integer.MAX_VALUE);

        final ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(mRv);
    }


    public static List<Homes> initDatas() {
        List<Homes> datas = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Homes i1 = new Homes("《点歌》生命歌手集" + i, "ID1315464", "al.粉丝团", "热门", "CV", "德国", "1234", "https://momeak.oss-cn-shenzhen.aliyuncs.com/h4.jpg", "【芭比】uud小可爱", "2", "", "==《而福利社区点歌台》==", "我做了这么多改变，只为了我心中不变");
            datas.add(i1);
        }
        return datas;
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


    @Override
    public void onPause() {
        super.onPause();
        /*if (slogin.equals("true")) {
            Intent intent = new Intent(getContext(), chatroom.class);
            //Constants.CLIENT_ROLE_AUDIENCE  听众
            //Constants.CLIENT_ROLE_BROADCASTER 主播
            intent.putExtra(Constant.ACTION_KEY_CROLE, Constants.CLIENT_ROLE_AUDIENCE);
            intent.putExtra(Constant.ACTION_KEY_ROOM_MODE, Constant.ChatRoomGamingStandard);
            intent.putExtra(Constant.ACTION_KEY_ROOM_ID, userid);
            intent.putExtra(Constant.ACTION_KEY_TITLE_NAME, "测试房间");
            startActivity(intent);
            Objects.requireNonNull(getActivity()).overridePendingTransition(R.anim.scale_in_center, R.anim.scale_out_center);
        } else {
            Intent intent1 = new Intent(getContext(), login.class);
            intent1.putExtra("type", 0);
            startActivity(intent1);
            Objects.requireNonNull(getActivity()).overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        }*/
    }

    @OnClick({R.id.imageView149})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView149:
                Intent intent2 = new Intent(getContext(), search.class);
                startActivity(intent2);
                Objects.requireNonNull(getActivity()).overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                break;
        }
    }


    private void okgos() {
        MyApp application = ((MyApp) getContext().getApplicationContext());
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
