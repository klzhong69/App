package com.example.hz52.app.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hz52.app.Entity.Roomhead;
import com.example.hz52.app.R;
import com.skyfishjy.library.RippleBackground;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class RoomheadAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<Roomhead> mEntityList;
    private RoomheadAdapter.OnItemClickListener mOnItemClickListener;

    public RoomheadAdapter(Context context, List<Roomhead> entityList) {
        this.mContext = context;
        this.mEntityList = entityList;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.room_head, parent, false);
        return new RoomheadAdapter.DemoViewHolder(view);
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }


    public void setOnItemClickListener(RoomheadAdapter.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Roomhead entity = mEntityList.get(position);
        if (entity.getUsersrc().equals("")) {
            Glide.with(mContext).load(R.drawable.add2).into(((RoomheadAdapter.DemoViewHolder) holder).mUserSrc);
        } else {
            Glide.with(mContext).load(entity.getUsersrc()).into(((RoomheadAdapter.DemoViewHolder) holder).mUserSrc);
        }
        if (entity.getName().equals("")) {
            ((DemoViewHolder) holder).mName.setVisibility(View.GONE);
        } else {
            ((RoomheadAdapter.DemoViewHolder) holder).mName.setText(entity.getName());
        }
        if (entity.getIma().equals("")) {
            ((DemoViewHolder) holder).mIma.setVisibility(View.GONE);
        } else {
            ((DemoViewHolder) holder).mIma.setVisibility(View.VISIBLE);
        }

        if (entity.getMsima().equals("")) {
            ((DemoViewHolder) holder).msIma.setVisibility(View.GONE);
        } else {
            ((DemoViewHolder) holder).msIma.setVisibility(View.VISIBLE);
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

    public class DemoViewHolder extends RecyclerView.ViewHolder {

        private ImageView mUserSrc;
        private TextView mName;
        private ImageView mIma;
        private ImageView msIma;

        public DemoViewHolder(View itemView) {
            super(itemView);
            mUserSrc = (ImageView) itemView.findViewById(R.id.imageView2);
            mName = (TextView) itemView.findViewById(R.id.textView39);
            mIma = (ImageView) itemView.findViewById(R.id.imageView46);
            msIma = (ImageView) itemView.findViewById(R.id.imageView107);
        }
    }


    public void addData(int position, Roomhead entity) {
        mEntityList.add(position, entity);
        notifyItemInserted(position);
    }

    public void removeData(int position) {
        mEntityList.remove(position);
        notifyItemRemoved(position);
    }


}

