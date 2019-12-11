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
import com.example.app.Entity.Familyhome;
import com.example.app.R;

import java.util.List;

public class FamilyHomeAdapter extends RecyclerView.Adapter {
    private static final String TAG = FamilyHomeAdapter.class.getSimpleName();

    private Context mContext;
    private List<Familyhome> mEntityList;
    private FamilyHomeAdapter.OnItemClickListener mOnItemClickListener;

    public FamilyHomeAdapter(Context context, List<Familyhome> entityList) {
        this.mContext = context;
        this.mEntityList = entityList;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.family_home, parent, false);
        return new FamilyHomeAdapter.DemoViewHolder(view);
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }


    public void setOnItemClickListener(FamilyHomeAdapter.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Familyhome entity = mEntityList.get(position);

        ((FamilyHomeAdapter.DemoViewHolder) holder).name.setText(entity.getName());
        Glide.with(mContext).load(entity.getImagesrc()).into(((FamilyHomeAdapter.DemoViewHolder) holder).imagesrc);
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
        private ImageView imagesrc;

        public DemoViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.textView48);
            imagesrc = (ImageView) itemView.findViewById(R.id.imageView28);
        }
    }


    public void addData(int position, Familyhome entity) {
        mEntityList.add(position, entity);
        notifyItemInserted(position);
    }

    public void removeData(int position) {
        mEntityList.remove(position);
        notifyItemRemoved(position);
    }
}
