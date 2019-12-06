package com.example.myapplication.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.entity.User;
import com.example.myapplication.R;

import java.util.List;

public class UserListAdapter extends BaseAdapter {

    private static final String TAG = UserListAdapter.class.getSimpleName();

    private List<User> mList;
    private LayoutInflater inflater;
    private UserListAdapter.ViewHolder viewHolder;

    public UserListAdapter(Context context, List<User> list) {
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
            viewHolder = new UserListAdapter.ViewHolder();
            convertView = inflater.inflate(R.layout.userlist, null);
            viewHolder.name=(TextView) convertView.findViewById(R.id.textView8);
            viewHolder.imagesrc=(ImageView) convertView.findViewById(R.id.imageView20);
            viewHolder.state=(ImageView) convertView.findViewById(R.id.imageView43);
            convertView.setTag(viewHolder);

            Log.d(TAG,"convertView == null");
        }else {
            Log.d(TAG,"convertView != null");
            viewHolder = (UserListAdapter.ViewHolder) convertView.getTag();
        }

        viewHolder.name.setText(mList.get(position).getMemberNickname());
        Glide.with(convertView).load(mList.get(position).getMemberIcon()).into(viewHolder.imagesrc);
        if(mList.get(position).getState()){
            Glide.with(convertView).load(R.drawable.qmui_icon_checkmark).into(viewHolder.state);
        }else{
            Glide.with(convertView).load(R.color.qmui_config_color_white).into(viewHolder.state);
        }
        return convertView;
    }


    static class ViewHolder {
        private TextView name;
        private ImageView imagesrc;
        private ImageView state;
    }
}
