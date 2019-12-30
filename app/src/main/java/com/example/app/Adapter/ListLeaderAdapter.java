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
import com.example.app.Entity.Listleader;
import com.example.app.Entity.Listleader;
import com.example.app.R;

import java.util.List;

public class ListLeaderAdapter extends RecyclerView.Adapter {
    private static final String TAG = ListLeaderAdapter.class.getSimpleName();

    private Context mContext;
    private List<Listleader> mEntityList;
    private ListLeaderAdapter.OnItemClickListener mOnItemClickListener;

    public ListLeaderAdapter(Context context, List<Listleader> entityList) {
        this.mContext = context;
        this.mEntityList = entityList;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_leader, parent, false);
        return new ListLeaderAdapter.DemoViewHolder(view);
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }


    public void setOnItemClickListener(ListLeaderAdapter.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Listleader entity = mEntityList.get(position);

        ((ListLeaderAdapter.DemoViewHolder) holder).lerder.setText(entity.getLerder());
        ((ListLeaderAdapter.DemoViewHolder) holder).name.setText(entity.getName());
        ((ListLeaderAdapter.DemoViewHolder) holder).type.setText(entity.getType());
        ((ListLeaderAdapter.DemoViewHolder) holder).sum.setText(entity.getSum());
        Glide.with(mContext).load(entity.getUserima()).into(((ListLeaderAdapter.DemoViewHolder)holder).userima);
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

        private TextView lerder;
        private ImageView userima;
        private TextView name;
        private TextView type;
        private TextView sum;

        public DemoViewHolder(View itemView) {
            super(itemView);
            lerder = (TextView) itemView.findViewById(R.id.textView67);
            name = (TextView) itemView.findViewById(R.id.textView66);
            sum = (TextView) itemView.findViewById(R.id.textView113);
            type = (TextView) itemView.findViewById(R.id.textView114);
            userima = (ImageView) itemView.findViewById(R.id.imageView18);

        }
    }


    public void addData(int position, Listleader entity) {
        mEntityList.add(position, entity);
        notifyItemInserted(position);
    }

    public void removeData(int position) {
        mEntityList.remove(position);
        notifyItemRemoved(position);
    }
}
