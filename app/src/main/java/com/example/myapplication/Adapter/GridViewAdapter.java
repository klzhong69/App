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
import com.example.myapplication.entity.Chatroom;

import java.util.List;


public class GridViewAdapter  extends RecyclerView.Adapter {
    private Context mContext;
    private List<Chatroom> mEntityList;
    private GridViewAdapter.OnItemClickListener mOnItemClickListener;
    public GridViewAdapter (Context context, List<Chatroom> entityList){
        this.mContext = context;
        this.mEntityList = entityList;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.chatroome1, parent, false);
        return new GridViewAdapter.DemoViewHolder(view);
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }


    public void setOnItemClickListener(GridViewAdapter.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Chatroom entity = mEntityList.get(position);
        if(entity.getUsersrc().equals("")){
            Glide.with(mContext).load(entity.getUsersrc()).into(((GridViewAdapter.DemoViewHolder)holder).mUserSrc);
        }else{
            Glide.with(mContext).load(entity.getUsersrc()).into(((GridViewAdapter.DemoViewHolder)holder).mUserSrc);
        }
        if(entity.getName().equals("")){
            ((DemoViewHolder)holder).mName.setVisibility(View.GONE);
        }else{
            ((GridViewAdapter.DemoViewHolder)holder).mName.setText(entity.getName());
        }
        if(entity.getIma().equals("")){
            ((DemoViewHolder)holder).mIma.setVisibility(View.GONE);
        }else{
            Glide.with(mContext).load(entity.getIma()).into(((GridViewAdapter.DemoViewHolder)holder).mIma);
        }

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
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mEntityList.size();
    }

    private class DemoViewHolder extends RecyclerView.ViewHolder{

        private ImageView mUserSrc;
        private TextView mName;
        private ImageView mIma;

        public DemoViewHolder(View itemView) {
            super(itemView);
            mUserSrc = (ImageView) itemView.findViewById(R.id.imageView2);
            mName = (TextView) itemView.findViewById(R.id.textView39);
            mIma = (ImageView) itemView.findViewById(R.id.imageView46);
        }
    }


    public void addData(int position,Chatroom entity) {
        mEntityList.add(position,entity);
        notifyItemInserted(position);
    }

    public void removeData(int position) {
        mEntityList.remove(position);
        notifyItemRemoved(position);
    }


}
