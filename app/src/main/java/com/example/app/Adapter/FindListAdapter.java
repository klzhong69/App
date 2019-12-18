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
import com.example.app.Entity.Findlist;
import com.example.app.Entity.Findlist;
import com.example.app.R;

import java.util.List;

public class FindListAdapter extends RecyclerView.Adapter {
    private static final String TAG = FindListAdapter.class.getSimpleName();

    private Context mContext;
    private List<Findlist> mEntityList;
    private FindListAdapter.OnItemClickListener mOnItemClickListener;

    public FindListAdapter(Context context, List<Findlist> entityList) {
        this.mContext = context;
        this.mEntityList = entityList;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.find_list, parent, false);
        return new FindListAdapter.DemoViewHolder(view);
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }


    public void setOnItemClickListener(FindListAdapter.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Findlist entity = mEntityList.get(position);

        ((FindListAdapter.DemoViewHolder) holder).name.setText(entity.getName());
        ((FindListAdapter.DemoViewHolder) holder).txt.setText(entity.getTxt());
        ((FindListAdapter.DemoViewHolder) holder).sum.setText(entity.getSum());
        Glide.with(mContext).load(entity.getImagesrc()).into(((FindListAdapter.DemoViewHolder)holder).imagesrc);
        if(entity.getIcon().equals("0")){
            Glide.with(mContext).load(R.drawable.remen).into(((FindListAdapter.DemoViewHolder)holder).icon);
        }else{
            Glide.with(mContext).load("").into(((FindListAdapter.DemoViewHolder)holder).icon);
        }

        ((FindListAdapter.DemoViewHolder) holder).lab1.setText(entity.getLab1());
        ((FindListAdapter.DemoViewHolder) holder).lab2.setText(entity.getLab2());
        ((FindListAdapter.DemoViewHolder) holder).lab3.setText(entity.getLab3());
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
        private TextView sum;
        private ImageView imagesrc;
        private ImageView icon;
        private TextView lab1;
        private TextView lab2;
        private TextView lab3;


        public DemoViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.textView66);
            txt = (TextView) itemView.findViewById(R.id.textView114);
            sum = (TextView) itemView.findViewById(R.id.textView113);
            imagesrc = (ImageView) itemView.findViewById(R.id.imageView18);
            icon = (ImageView) itemView.findViewById(R.id.imageView95);
            lab1 = (TextView) itemView.findViewById(R.id.textView117);
            lab2 = (TextView) itemView.findViewById(R.id.textView118);
            lab3 = (TextView) itemView.findViewById(R.id.textView119);

        }
    }


    public void addData(int position, Findlist entity) {
        mEntityList.add(position, entity);
        notifyItemInserted(position);
    }

    public void removeData(int position) {
        mEntityList.remove(position);
        notifyItemRemoved(position);
    }
}
