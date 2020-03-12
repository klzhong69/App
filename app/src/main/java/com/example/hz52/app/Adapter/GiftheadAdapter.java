package com.example.hz52.app.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hz52.app.Entity.Gifthead;
import com.example.hz52.app.R;

import java.util.List;

public class GiftheadAdapter extends RecyclerView.Adapter {
    private static final String TAG = GiftheadAdapter.class.getSimpleName();

    private Context mContext;
    private List<Gifthead> mEntityList;
    private GiftheadAdapter.OnItemClickListener mOnItemClickListener;

    public GiftheadAdapter(Context context, List<Gifthead> entityList) {
        this.mContext = context;
        this.mEntityList = entityList;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.room_head_gift, parent, false);
        return new GiftheadAdapter.DemoViewHolder(view);
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }


    public void setOnItemClickListener(GiftheadAdapter.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Gifthead entity = mEntityList.get(position);

        Glide.with(mContext).load(entity.getUserima()).into(((GiftheadAdapter.DemoViewHolder) holder).userima);
        Glide.with(mContext).load(entity.getType()).into(((GiftheadAdapter.DemoViewHolder) holder).type);
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
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mEntityList.size();
    }

    private class DemoViewHolder extends RecyclerView.ViewHolder {

        private ImageView userima;
        private ImageView type;

        DemoViewHolder(View itemView) {
            super(itemView);
            userima = (ImageView) itemView.findViewById(R.id.imageView2);
            type = (ImageView) itemView.findViewById(R.id.imageView107);

        }
    }


    public void addData(int position, Gifthead entity) {
        mEntityList.add(position, entity);
        notifyItemInserted(position);
    }

    public void removeData(int position) {
        mEntityList.remove(position);
        notifyItemRemoved(position);
    }
}