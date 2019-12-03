package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class Messages extends Fragment {

    Unbinder unbinder;
    @BindView(R.id.button5)
    Button button5;
    @BindView(R.id.button10)
    Button button10;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.message, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public static Messages newInstance() {
        Bundle args = new Bundle();
        Messages fragment = new Messages();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @OnClick({R.id.button5, R.id.button10})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button5:
                Intent intent = new Intent(getContext(), chat.class);
                startActivity(intent);
                break;
            case R.id.button10:
                Intent intent1 = new Intent(getContext(), chatroom.class);
                startActivity(intent1);
                break;
        }
    }
}
