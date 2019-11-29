package com.example.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.opensource.svgaplayer.SVGADrawable;
import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.SVGAParser;
import com.opensource.svgaplayer.SVGAVideoEntity;

import org.jetbrains.annotations.NotNull;

import java.net.MalformedURLException;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class Message extends Fragment {

    Unbinder unbinder;
    @BindView(R.id.button5)
    Button button5;
    SVGAParser parser;
    @BindView(R.id.imageView46)
    ImageView imageView46;
    @BindView(R.id.svgaima)
    SVGAImageView animationView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.message, container, false);
        unbinder = ButterKnife.bind(this, view);
        animationView.setBackgroundColor(Color.GRAY);
        parser = new SVGAParser(container.getContext());
        loadAnimation();
        return view;
    }

    private void loadAnimation() {
        try { // new URL needs try catch.
            parser.parse(new URL("https://data.meitehudong.com/52hertz/svga/kingset.svga"), new SVGAParser.ParseCompletion() {
                @Override
                public void onComplete(@NotNull SVGAVideoEntity videoItem) {
                    animationView.setVisibility(View.VISIBLE);
                    SVGADrawable drawable = new SVGADrawable(videoItem);
                    animationView.setImageDrawable(drawable);
                    animationView.startAnimation();
                }

                @Override
                public void onError() {

                }
            });
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public static Message newInstance() {
        Bundle args = new Bundle();
        Message fragment = new Message();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.button5)
    public void onViewClicked() {
        Intent intent = new Intent(getContext(), chat.class);
        startActivity(intent);
    }
}
