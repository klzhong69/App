package com.example.hz52.app.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hz52.app.Entity.Theme;
import com.example.hz52.app.R;

import java.util.HashMap;
import java.util.List;

public class ThemeAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<Theme> mEntityList;
    private ThemeAdapter.OnItemClickListener mOnItemClickListener;
    public static HashMap<Integer, Boolean> states = new HashMap<Integer, Boolean>();  //在这里要做判断保证只有一个RadioButton被选中
    private Boolean bool=true;

    public ThemeAdapter(Context context, List<Theme> entityList) {
        this.mContext = context;
        this.mEntityList = entityList;
        for (int i = 0; i < mEntityList.size(); i++) {
            states.put(i, false);
        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.room_theme, parent, false);
        return new ThemeAdapter.DemoViewHolder(view);
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }


    public void setOnItemClickListener(ThemeAdapter.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Theme entity = mEntityList.get(position);

        ((DemoViewHolder) holder).mName.setText(entity.getName());
        ((DemoViewHolder) holder).mType.setText(entity.getType());
        Glide.with(mContext).load(R.drawable.roomback).into(((DemoViewHolder) holder).mTheme);

        if(entity.getState()==0 && bool){
            //然后设置点击的那个按钮设置状态为选中
            states.put(position, true);    //这样所有的条目中只有一个被选中！
            bool=false;
        }
        ((DemoViewHolder) holder).sum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i = 0; i < mEntityList.size(); i++) {
                    if(i!=position){
                        states.put(i, false);
                    }else{
                        states.put(position, true);
                    }

                }
                notifyDataSetChanged();
            }
        });
        if (states.get(position) == null || states.get(position) == false) {  //true说明没有被选中
            ((DemoViewHolder) holder).sum.setChecked(false);
        } else {
            ((DemoViewHolder) holder).sum.setChecked(true);
        }


        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView, pos);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemLongClick(holder.itemView, pos);
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mEntityList.size();
    }

    private class DemoViewHolder extends RecyclerView.ViewHolder {

        private ImageView mTheme;
        private TextView mName;
        private TextView mType;
        private RadioButton sum;


        public DemoViewHolder(View itemView) {
            super(itemView);
            mTheme = (ImageView) itemView.findViewById(R.id.imageView151);
            mName = (TextView) itemView.findViewById(R.id.textView128);
            mType = (TextView) itemView.findViewById(R.id.textView186);
            sum = (RadioButton) itemView.findViewById(R.id.radioButton);
        }
    }


    public void addData(int position, Theme entity) {
        mEntityList.add(position, entity);
        notifyItemInserted(position);
    }

    public void removeData(int position) {
        mEntityList.remove(position);
        notifyItemRemoved(position);
    }


}
