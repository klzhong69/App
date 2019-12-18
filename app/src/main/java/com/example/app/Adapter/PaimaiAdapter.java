package com.example.app.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.app.Entity.Paimai;
import com.example.app.R;

import java.util.List;

public class PaimaiAdapter extends RecyclerView.Adapter {
    private static final String TAG = PaimaiAdapter.class.getSimpleName();

    private Context mContext;
    private List<Paimai> mEntityList;
    private PaimaiAdapter.OnItemClickListener mOnItemClickListener;

    public PaimaiAdapter(Context context, List<Paimai> entityList) {
        this.mContext = context;
        this.mEntityList = entityList;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.room_paimai_people, parent, false);
        return new PaimaiAdapter.DemoViewHolder(view);
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }


    public void setOnItemClickListener(PaimaiAdapter.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Paimai entity = mEntityList.get(position);

        ((PaimaiAdapter.DemoViewHolder) holder).id.setText(entity.getId());
        ((PaimaiAdapter.DemoViewHolder) holder).name.setText(entity.getName());

        if(entity.getType().equals("0")){
            ((PaimaiAdapter.DemoViewHolder) holder).type.setVisibility(View.GONE);
        }else{
            ((PaimaiAdapter.DemoViewHolder) holder).type.setVisibility(View.VISIBLE);


        }
        Glide.with(mContext).load(entity.getUserima()).into(((PaimaiAdapter.DemoViewHolder)holder).userima);
        Glide.with(mContext).load(R.drawable.l3).into(((PaimaiAdapter.DemoViewHolder)holder).grade);
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
        private Button type;

        DemoViewHolder(View itemView) {
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.textView114);
            name = (TextView) itemView.findViewById(R.id.textView66);
            grade = (ImageView) itemView.findViewById(R.id.imageView66);
            userima = (ImageView) itemView.findViewById(R.id.imageView18);
            type = (Button) itemView.findViewById(R.id.but);

        }
    }


    public void addData(int position, Paimai entity) {
        mEntityList.add(position, entity);
        notifyItemInserted(position);
    }

    public void removeData(int position) {
        mEntityList.remove(position);
        notifyItemRemoved(position);
    }
}
