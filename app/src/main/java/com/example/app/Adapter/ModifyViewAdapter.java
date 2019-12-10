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
import com.example.app.Entity.Modify;
import com.example.app.R;

import java.util.List;

public class ModifyViewAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<Modify> mEntityList;
    private GridViewAdapter.OnItemClickListener mOnItemClickListener;
    public ModifyViewAdapter(Context context, List<Modify> entityList){
        this.mContext = context;
        this.mEntityList = entityList;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.modify, parent, false);
        return new ModifyViewAdapter.DemoViewHolder(view);
    }

    public void setOnItemClickListener(GridViewAdapter.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Modify entity = mEntityList.get(position);


        Glide.with(mContext).load(entity.getImagesrc()).into(((ModifyViewAdapter.DemoViewHolder)holder).mIma);

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

        private ImageView mIma;

        public DemoViewHolder(View itemView) {
            super(itemView);
            mIma = (ImageView) itemView.findViewById(R.id.imageView41);
        }
    }


    public void addData(int position,Modify entity) {
        mEntityList.add(position,entity);
        notifyItemInserted(position);
    }

    public void removeData(int position) {
        mEntityList.remove(position);
        notifyItemRemoved(position);
    }


}
