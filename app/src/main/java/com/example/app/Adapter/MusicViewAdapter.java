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
import com.example.app.Entity.Mymusic;
import com.example.app.Entity.Mymusic;
import com.example.app.R;

import java.util.List;

public class MusicViewAdapter extends RecyclerView.Adapter {
    private static final String TAG = MusicViewAdapter.class.getSimpleName();

    private Context mContext;
    private List<Mymusic> mEntityList;
    private MusicViewAdapter.OnItemClickListener mOnItemClickListener;

    public MusicViewAdapter(Context context, List<Mymusic> entityList) {
        this.mContext = context;
        this.mEntityList = entityList;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.my_music, parent, false);
        return new MusicViewAdapter.DemoViewHolder(view);
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }


    public void setOnItemClickListener(MusicViewAdapter.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Mymusic entity = mEntityList.get(position);

        ((MusicViewAdapter.DemoViewHolder) holder).name.setText(entity.getName());
        ((MusicViewAdapter.DemoViewHolder) holder).time.setText(entity.getTime());
       if(entity.getType().equals("0")){
            Glide.with(mContext).load(R.drawable.dk_ic_play_disable).into(((DemoViewHolder)holder).type);
        }else{
            Glide.with(mContext).load(R.drawable.dk_ic_pause_disable).into(((DemoViewHolder)holder).type);
        }
        Glide.with(mContext).load(entity.getIma()).into(((MusicViewAdapter.DemoViewHolder) holder).imagesrc);
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
        private TextView time;
        private ImageView type;
        private ImageView imagesrc;

        public DemoViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.textView66);
            time = (TextView) itemView.findViewById(R.id.textView67);
            type = (ImageView) itemView.findViewById(R.id.imageView67);
            imagesrc = (ImageView) itemView.findViewById(R.id.imageView18);
        }
    }


    public void addData(int position, Mymusic entity) {
        mEntityList.add(position, entity);
        notifyItemInserted(position);
    }

    public void removeData(int position) {
        mEntityList.remove(position);
        notifyItemRemoved(position);
    }
}