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
import com.example.app.Entity.Findgift;
import com.example.app.Entity.Findgift;
import com.example.app.R;

import java.util.List;

public class FindgiftAdapter extends RecyclerView.Adapter {
    private static final String TAG = FindgiftAdapter.class.getSimpleName();

    private Context mContext;
    private List<Findgift> mEntityList;
    private FindgiftAdapter.OnItemClickListener mOnItemClickListener;

    public FindgiftAdapter(Context context, List<Findgift> entityList) {
        this.mContext = context;
        this.mEntityList = entityList;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.find_gift, parent, false);
        return new DemoViewHolder(view);
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }


    public void setOnItemClickListener(FindgiftAdapter.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Findgift entity = mEntityList.get(position);

        ((FindgiftAdapter.DemoViewHolder) holder).name1.setText(entity.getName1());
        ((FindgiftAdapter.DemoViewHolder) holder).name2.setText(entity.getName2());
        ((FindgiftAdapter.DemoViewHolder) holder).num.setText(entity.getNum());
        ((FindgiftAdapter.DemoViewHolder) holder).roomid.setText(entity.getRoomid());
        Glide.with(mContext).load(entity.getUserima1()).into(((FindgiftAdapter.DemoViewHolder)holder).userima1);
        Glide.with(mContext).load(entity.getUserima2()).into(((FindgiftAdapter.DemoViewHolder)holder).userima2);
        Glide.with(mContext).load(entity.getGrade1()).into(((FindgiftAdapter.DemoViewHolder)holder).grade1);
        Glide.with(mContext).load(entity.getGrade2()).into(((FindgiftAdapter.DemoViewHolder)holder).grade2);
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

        private ImageView userima1;
        private ImageView userima2;
        private TextView name1;
        private TextView name2;
        private ImageView grade1;
        private ImageView grade2;
        private TextView num;
        private TextView roomid;



        public DemoViewHolder(View itemView) {
            super(itemView);
            name1 = (TextView) itemView.findViewById(R.id.textView17);
            name2 = (TextView) itemView.findViewById(R.id.textView18);
            num = (TextView) itemView.findViewById(R.id.textView15);
            roomid = (TextView) itemView.findViewById(R.id.textView114);
            userima1 = (ImageView) itemView.findViewById(R.id.imageView18);
            userima2 = (ImageView) itemView.findViewById(R.id.imageView19);
            grade1 = (ImageView) itemView.findViewById(R.id.imageView31);
            grade2 = (ImageView) itemView.findViewById(R.id.imageView32);

        }
    }


    public void addData(int position, Findgift entity) {
        mEntityList.add(position, entity);
        notifyItemInserted(position);
    }

    public void removeData(int position) {
        mEntityList.remove(position);
        notifyItemRemoved(position);
    }
}

