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
import com.example.hz52.app.Entity.Friends;
import com.example.hz52.app.R;

import java.util.List;

public class FriendsAdapter extends RecyclerView.Adapter {
    private static final String TAG = FriendsAdapter.class.getSimpleName();

    private Context mContext;
    private List<Friends> mEntityList;
    private FriendsAdapter.OnItemClickListener mOnItemClickListener;

    public FriendsAdapter(Context context, List<Friends> entityList) {
        this.mContext = context;
        this.mEntityList = entityList;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.mess_friends, parent, false);
        return new FriendsAdapter.DemoViewHolder(view);
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }


    public void setOnItemClickListener(FriendsAdapter.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Friends entity = mEntityList.get(position);

        ((FriendsAdapter.DemoViewHolder) holder).name.setText(entity.getName());
        ((FriendsAdapter.DemoViewHolder) holder).txt.setText(entity.getTxt());
        Glide.with(mContext).load(entity.getImagesrc()).into(((FriendsAdapter.DemoViewHolder)holder).imagesrc);
        if(entity.getIcon().equals("2")){
            Glide.with(mContext).load(R.drawable.nans).into(((FriendsAdapter.DemoViewHolder)holder).icon);
        }else{
            Glide.with(mContext).load(R.drawable.nvs).into(((FriendsAdapter.DemoViewHolder)holder).icon);
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

        private TextView name;
        private TextView txt;
        private ImageView imagesrc;
        private ImageView icon;

        public DemoViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.textView66);
            txt = (TextView) itemView.findViewById(R.id.textView67);
            imagesrc = (ImageView) itemView.findViewById(R.id.imageView18);
            icon = (ImageView) itemView.findViewById(R.id.imageView67);

        }
    }


    public void addData(int position, Friends entity) {
        mEntityList.add(position, entity);
        notifyItemInserted(position);
    }

    public void removeData(int position) {
        mEntityList.remove(position);
        notifyItemRemoved(position);
    }
}
