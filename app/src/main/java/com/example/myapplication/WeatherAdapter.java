package com.example.myapplication;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import java.util.List;

public class WeatherAdapter extends ArrayAdapter<Faxan> {

    private int resourceId;

    //将上下文、ListView子项布局的id、数据 传递进来
    public WeatherAdapter(Context context, int textViewResourceId, List<Faxan> obj){
        super(context, textViewResourceId, obj);
        resourceId = textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Faxan faxan = getItem(position);//获取当前项的Weather实例
        //LayoutInflater的inflate()方法接收3个参数：需要实例化布局资源的id、ViewGroup类型视图组对象、false
        //false表示只让父布局中声明的layout属性生效，但不会为这个view添加父布局
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        //获取实例
        ImageView image = (ImageView) view.findViewById(R.id.imageView20);
        TextView name = (TextView) view.findViewById(R.id.textView8);
        TextView txt = (TextView) view.findViewById(R.id.textView15);
        TextView type = (TextView) view.findViewById(R.id.textView17);
        TextView popul = (TextView) view.findViewById(R.id.textView18);
        TextView collection = (TextView) view.findViewById(R.id.textView19);
        //设置图片和文字
        image.setImageURI(Uri.parse(faxan.getImagesrc()));
        name.setText(faxan.getName());
        txt.setText(faxan.getTxt());
        type.setText(faxan.getType());
        popul.setText(faxan.getPopul());
        collection.setText(faxan.getCollection());
        return view;
    }
}