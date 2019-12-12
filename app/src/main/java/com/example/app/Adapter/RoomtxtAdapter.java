package com.example.app.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.app.Entity.Roomtxt;
import com.example.app.R;

import java.util.List;

public class RoomtxtAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<Roomtxt> mEntityList;
    private OnItemClickListener mOnItemClickListener;
    public RoomtxtAdapter (Context context, List<Roomtxt> entityList){
        this.mContext = context;
        this.mEntityList = entityList;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.room_txt, parent, false);
        return new DemoViewHolder(view);
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Roomtxt entity = mEntityList.get(position);
        if(entity.getName().equals("")){
            ((DemoViewHolder)holder).mName.setVisibility(View.GONE);
        }else{
            ((DemoViewHolder)holder).mName.setText(entity.getName());
        }
        if(entity.getGrade().equals("")){
            ((DemoViewHolder)holder).mGrade.setVisibility(View.GONE);
        }else{
            Glide.with(mContext).load(R.drawable.l3).into(((DemoViewHolder)holder).mGrade);
        }
        ((DemoViewHolder)holder).mText.setText(entity.getText());

        if (mOnItemClickListener != null)
        {
            holder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView, pos);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener()
            {
                @Override
                public boolean onLongClick(View v)
                {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemLongClick(holder.itemView, pos);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mEntityList.size();
    }

    private class DemoViewHolder extends RecyclerView.ViewHolder{

        private TextView mText;
        private TextView mName;
        private ImageView mGrade;

        public DemoViewHolder(View itemView) {
            super(itemView);
            mText = (TextView) itemView.findViewById(R.id.textView35);
            mName = (TextView) itemView.findViewById(R.id.textView34);
            mGrade = (ImageView) itemView.findViewById(R.id.imageView42);
        }
    }


    public void addData(int position, Roomtxt entity) {
        mEntityList.add(position,entity);
        notifyItemInserted(position);
    }

    public void removeData(int position) {
        mEntityList.remove(position);
        notifyItemRemoved(position);
    }




}

