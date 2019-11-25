package com.example.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ListViewAdapter extends BaseAdapter {
    private static final String TAG = ListViewAdapter.class.getSimpleName();

    private List<String> mList;
    private LayoutInflater inflater;
    private ViewHolder viewHolder;

    public ListViewAdapter(Context context, List<String> list) {
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
            viewHolder.mTextView=(TextView) convertView.findViewById(R.id.textView8);
            convertView.setTag(viewHolder);

            Log.d(TAG,"convertView == null");
        }else {
            Log.d(TAG,"convertView != null");
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.mTextView.setText(mList.get(position));
        return convertView;
    }


    static class ViewHolder {
        private TextView mTextView;
    }
}
