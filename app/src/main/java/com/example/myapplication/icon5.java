package com.example.myapplication;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.cofig.MyApp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class icon5 extends Fragment {

    private int layoutParamsHeight =0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.head1, container, false);
        MyApp application = ((MyApp) getContext().getApplicationContext());
        Map<Integer,Integer> a = application.getScores();
        a.put(4,layoutParamsHeight);
        application.setScores(a);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}
