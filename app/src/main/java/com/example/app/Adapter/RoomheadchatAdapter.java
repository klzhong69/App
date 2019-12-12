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
import com.example.app.Entity.Roomheadchat;
import com.example.app.R;

import java.util.List;

public class RoomheadchatAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<Roomheadchat> mEntityList;
    private RoomheadchatAdapter.OnItemClickListener mOnItemClickListener;

    public RoomheadchatAdapter(Context context, List<Roomheadchat> entityList) {
        this.mContext = context;
        this.mEntityList = entityList;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.room_head_chat, parent, false);
        return new RoomheadchatAdapter.DemoViewHolder(view);
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }


    public void setOnItemClickListener(RoomheadchatAdapter.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Roomheadchat entity = mEntityList.get(position);

        Glide.with(mContext).load(entity.getIma()).into(((RoomheadchatAdapter.DemoViewHolder) holder).mIma);
        ((DemoViewHolder)holder).mNum.setText(entity.getNum());
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


        private ImageView mIma;
        private TextView mNum;

        public DemoViewHolder(View itemView) {
            super(itemView);

            mIma = (ImageView) itemView.findViewById(R.id.imageView2);
            mNum = (TextView) itemView.findViewById(R.id.textView127);
        }
    }


    public void addData(int position, Roomheadchat entity) {
        mEntityList.add(position, entity);
        notifyItemInserted(position);
    }

    public void removeData(int position) {
        mEntityList.remove(position);
        notifyItemRemoved(position);
    }
}
