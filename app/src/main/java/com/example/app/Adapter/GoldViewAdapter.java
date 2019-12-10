package com.example.app.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.Entity.Mygold;
import com.example.app.R;

import java.util.List;

public class GoldViewAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<Mygold> mEntityList;
    private GoldViewAdapter.OnItemClickListener mOnItemClickListener;

    public GoldViewAdapter(Context context, List<Mygold> entityList) {
        this.mContext = context;
        this.mEntityList = entityList;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.my_gold, parent, false);
        return new GoldViewAdapter.DemoViewHolder(view);
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }


    public void setOnItemClickListener(GoldViewAdapter.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Mygold entity = mEntityList.get(position);

        ((GoldViewAdapter.DemoViewHolder) holder).fold.setText(entity.getFolds());
        ((GoldViewAdapter.DemoViewHolder) holder).amount.setText(entity.getAmount());

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

        private TextView fold;
        private TextView amount;

        public DemoViewHolder(View itemView) {
            super(itemView);
            fold = (TextView) itemView.findViewById(R.id.textView64);
            amount = (TextView) itemView.findViewById(R.id.textView65);
        }
    }


    public void addData(int position, Mygold entity) {
        mEntityList.add(position, entity);
        notifyItemInserted(position);
    }

    public void removeData(int position) {
        mEntityList.remove(position);
        notifyItemRemoved(position);
    }
}