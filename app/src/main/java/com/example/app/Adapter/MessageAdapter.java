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
import com.example.app.Entity.Message;
import com.example.app.Entity.Message;
import com.example.app.R;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter {
    private static final String TAG = MessageAdapter.class.getSimpleName();

    private Context mContext;
    private List<Message> mEntityList;
    private MessageAdapter.OnItemClickListener mOnItemClickListener;

    public MessageAdapter(Context context, List<Message> entityList) {
        this.mContext = context;
        this.mEntityList = entityList;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.mess_messagelist, parent, false);
        return new MessageAdapter.DemoViewHolder(view);
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }


    public void setOnItemClickListener(MessageAdapter.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Message entity = mEntityList.get(position);

        ((MessageAdapter.DemoViewHolder) holder).name.setText(entity.getName());
        ((MessageAdapter.DemoViewHolder) holder).txt.setText(entity.getTxt());
        ((MessageAdapter.DemoViewHolder) holder).time.setText(entity.getTime());
        ((MessageAdapter.DemoViewHolder) holder).sum.setText(entity.getSum());
        if(entity.getSum()>0){
            ((MessageAdapter.DemoViewHolder) holder).sum.setText(entity.getSum());
        }else{
            ((MessageAdapter.DemoViewHolder) holder).sum.setVisibility(View.GONE);
        }
        Glide.with(mContext).load(entity.getImagesrc()).into(((MessageAdapter.DemoViewHolder)holder).imagesrc);
        if(entity.getIcon().equals("0")){
            Glide.with(mContext).load(R.drawable.zhiyou).into(((MessageAdapter.DemoViewHolder)holder).icon);
        }else{
            Glide.with(mContext).load("").into(((MessageAdapter.DemoViewHolder)holder).icon);
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
        private TextView time;
        private TextView sum;
        private ImageView imagesrc;
        private ImageView icon;

        public DemoViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.textView66);
            txt = (TextView) itemView.findViewById(R.id.textView67);
            time = (TextView) itemView.findViewById(R.id.textView68);
            sum = (TextView) itemView.findViewById(R.id.textView96);
            imagesrc = (ImageView) itemView.findViewById(R.id.imageView18);
            icon = (ImageView) itemView.findViewById(R.id.imageView67);

        }
    }


    public void addData(int position, Message entity) {
        mEntityList.add(position, entity);
        notifyItemInserted(position);
    }

    public void removeData(int position) {
        mEntityList.remove(position);
        notifyItemRemoved(position);
    }
}
