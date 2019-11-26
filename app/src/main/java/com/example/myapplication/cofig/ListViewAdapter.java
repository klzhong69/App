package com.example.myapplication.cofig;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;

import java.util.List;

public class ListViewAdapter extends BaseAdapter {
    private static final String TAG = ListViewAdapter.class.getSimpleName();

    private List<Faxan> mList;
    private LayoutInflater inflater;
    private ViewHolder viewHolder;

    public ListViewAdapter(Context context, List<Faxan> list) {
        mList = list;
        this.inflater  = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList == null ? null : mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.list, null);
            viewHolder.name=(TextView) convertView.findViewById(R.id.textView8);
            viewHolder.txt=(TextView) convertView.findViewById(R.id.textView15);
            viewHolder.type=(TextView) convertView.findViewById(R.id.textView19);
            viewHolder.popul=(TextView) convertView.findViewById(R.id.textView17);
            viewHolder.collection=(TextView) convertView.findViewById(R.id.textView18);
            viewHolder.imagesrc=(ImageView) convertView.findViewById(R.id.imageView20);
            convertView.setTag(viewHolder);

            Log.d(TAG,"convertView == null");
        }else {
            Log.d(TAG,"convertView != null");
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.name.setText(mList.get(position).getName());
        viewHolder.txt.setText(mList.get(position).getTxt());
        viewHolder.type.setText(mList.get(position).getType());
        viewHolder.popul.setText(mList.get(position).getPopul());
        viewHolder.collection.setText(mList.get(position).getCollection());
        Glide.with(convertView).load(mList.get(position).getImagesrc()).into(viewHolder.imagesrc);
        return convertView;
    }


    static class ViewHolder {
        private TextView name;
        private TextView txt;
        private TextView type;
        private TextView popul;
        private TextView collection;
        private ImageView imagesrc;
    }
}
