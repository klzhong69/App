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
import com.example.app.Entity.Package2;
import com.example.app.R;

import java.util.List;

public class Package2Adapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<Package2> mEntityList;
    private Package2Adapter.OnItemClickListener mOnItemClickListener;
    public Package2Adapter(Context context, List<Package2> entityList){
        this.mContext = context;
        this.mEntityList = entityList;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.my_package2, parent, false);
        return new Package2Adapter.DemoViewHolder(view);
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }


    public void setOnItemClickListener(Package2Adapter.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Package2 entity = mEntityList.get(position);

        ((Package2Adapter.DemoViewHolder)holder).mNmae.setText(entity.getName());
        ((Package2Adapter.DemoViewHolder)holder).mTime.setText(entity.getTime());
        Glide.with(mContext).load(entity.getIma()).into(((Package2Adapter.DemoViewHolder)holder).mIma);

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
        private TextView mNmae;
        private TextView mTime;

        public DemoViewHolder(View itemView) {
            super(itemView);
            mIma = (ImageView) itemView.findViewById(R.id.imageView18);
            mNmae = (TextView) itemView.findViewById(R.id.textView18);
            mTime = (TextView) itemView.findViewById(R.id.textView73);
        }
    }


    public void addData(int position,Package2 entity) {
        mEntityList.add(position,entity);
        notifyItemInserted(position);
    }

    public void removeData(int position) {
        mEntityList.remove(position);
        notifyItemRemoved(position);
    }


}
