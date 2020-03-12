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
import com.example.hz52.app.Entity.Onlinepeople;
import com.example.hz52.app.R;

import java.util.List;

public class SelectPeopleAdapter extends RecyclerView.Adapter {
    private static final String TAG = SelectPeopleAdapter.class.getSimpleName();

    private Context mContext;
    private List<Onlinepeople> mEntityList;
    private SelectPeopleAdapter.OnItemClickListener mOnItemClickListener;

    public SelectPeopleAdapter(Context context, List<Onlinepeople> entityList) {
        this.mContext = context;
        this.mEntityList = entityList;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.room_select_people, parent, false);
        return new SelectPeopleAdapter.DemoViewHolder(view);
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }


    public void setOnItemClickListener(SelectPeopleAdapter.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Onlinepeople entity = mEntityList.get(position);

        ((SelectPeopleAdapter.DemoViewHolder) holder).id.setText(entity.getId());
        ((SelectPeopleAdapter.DemoViewHolder) holder).name.setText(entity.getName());

        if(entity.getType().equals("0")){
            ((SelectPeopleAdapter.DemoViewHolder) holder).type.setText("解除");
        }else if(entity.getType().equals("1")){
            ((SelectPeopleAdapter.DemoViewHolder) holder).type.setText("取消");
        }
        Glide.with(mContext).load(entity.getUserima()).into(((SelectPeopleAdapter.DemoViewHolder)holder).userima);
        Glide.with(mContext).load(R.drawable.l3).into(((SelectPeopleAdapter.DemoViewHolder)holder).grade);
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

        private TextView id;
        private ImageView userima;
        private TextView name;
        private ImageView grade;
        private TextView type;

        DemoViewHolder(View itemView) {
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.textView114);
            name = (TextView) itemView.findViewById(R.id.textView66);
            grade = (ImageView) itemView.findViewById(R.id.imageView66);
            userima = (ImageView) itemView.findViewById(R.id.imageView18);
            type = (TextView) itemView.findViewById(R.id.textView115);

        }
    }


    public void addData(int position, Onlinepeople entity) {
        mEntityList.add(position, entity);
        notifyItemInserted(position);
    }

    public void removeData(int position) {
        mEntityList.remove(position);
        notifyItemRemoved(position);
    }
}