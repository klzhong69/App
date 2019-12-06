package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.entity.BaseEntity;

import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<BaseEntity> mEntityList;
    private OnItemClickListener mOnItemClickListener;
    public RecyclerViewAdapter (Context context, List<BaseEntity> entityList){
        this.mContext = context;
        this.mEntityList = entityList;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.folorlist, parent, false);
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
        BaseEntity entity = mEntityList.get(position);
        if(entity.getName().equals("")){
            ((DemoViewHolder)holder).mName.setVisibility(View.GONE);
        }else{
            ((DemoViewHolder)holder).mName.setText(entity.getName());
        }
        if(entity.getIma().equals("")){
            ((DemoViewHolder)holder).mIma.setVisibility(View.GONE);
        }else{
            Glide.with(mContext).load(entity.getIma()).into(((DemoViewHolder)holder).mIma);
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
        private ImageView mIma;

        public DemoViewHolder(View itemView) {
            super(itemView);
            mText = (TextView) itemView.findViewById(R.id.textView35);
            mName = (TextView) itemView.findViewById(R.id.textView34);
            mIma = (ImageView) itemView.findViewById(R.id.imageView42);
        }
    }


    public void addData(int position,BaseEntity entity) {
        mEntityList.add(position,entity);
        notifyItemInserted(position);
    }

    public void removeData(int position) {
        mEntityList.remove(position);
        notifyItemRemoved(position);
    }




}

