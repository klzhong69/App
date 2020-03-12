package com.example.hz52.app;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hz52.app.Adapter.FindListAdapter;
import com.example.hz52.app.Entity.Findlist;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class Find extends Fragment {


    @BindView(R.id.textView115)
    TextView textView115;
    @BindView(R.id.textView116)
    TextView textView116;
    @BindView(R.id.relative9)
    RelativeLayout relative9;
    @BindView(R.id.recycler14)
    RecyclerView recycler14;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private Unbinder unbinder;
    private ArrayList<Findlist> mArrayList;
    private QMUITipDialog tipDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.find_hot, container, false);
        unbinder = ButterKnife.bind(this, view);

        refreshLayout.setRefreshFooter(new ClassicsFooter(getContext()));
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadMore(2000);//传入false表示加载失败
            }
        });
        tipDialog = new QMUITipDialog.Builder(getContext())
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                .setTipWord("正在加载")
                .create();
        tipDialog.show();
        Window window = Objects.requireNonNull(getActivity()).getWindow();
        //21表示5.0
        //取消设置透明状态栏,使 ContentView 内容不再覆盖状态栏
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //设置状态栏颜色
        window.setStatusBarColor(Color.WHITE);

        View decor = window.getDecorView();
        int ui = decor.getSystemUiVisibility();
        ui |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR; //设置状态栏中字体的颜色为黑色
        //ui &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR; //设置状态栏中字体颜色为白色
        decor.setSystemUiVisibility(ui);



        initData();
        init();
        return view;

    }
    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
    private void init() {
        //适配器
        FindListAdapter mAdapter = new FindListAdapter(getContext(), mArrayList);
        //设置适配器adapter
        recycler14.setAdapter(mAdapter);

        /*LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mListView.setLayoutManager(mLinearLayoutManager);*/

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recycler14.setLayoutManager(layoutManager);
        recycler14.setItemAnimator(new DefaultItemAnimator());

        mAdapter.setOnItemClickListener(new FindListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getContext(), chatroom.class);
                startActivity(intent);

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
        recycler14.setItemAnimator(defaultItemAnimator);
    }

    private void initData() {
        mArrayList = new ArrayList<Findlist>();
        for (int i = 0; i < 8; i++) {
            int sum;
            if (i > 5) {
                sum = i - 5;
            } else {
                sum = i + 1;
            }
            Findlist i1 = new Findlist("PHakamile Sikali", "Muria Moura", "2345", "https://momeak.oss-cn-shenzhen.aliyuncs.com/h" + sum + ".jpg", "", "热门", "CV", "德国");
            mArrayList.add(i1);
        }
        tipDialog.dismiss();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    public static Find newInstance() {
        Bundle args = new Bundle();
        Find fragment = new Find();
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

    }

    @OnClick(R.id.textView116)
    public void onViewClicked() {
        Intent intent2 = new Intent(getContext(), find_make.class);
        startActivity(intent2);
        getActivity().overridePendingTransition(R.animator.anim_right_in, R.animator.anim_left_out);
    }
}
