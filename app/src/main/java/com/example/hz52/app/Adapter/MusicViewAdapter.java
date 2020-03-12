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
import com.example.hz52.app.Entity.Mymusic;
import com.example.hz52.app.R;

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
           ((MusicViewAdapter.DemoViewHolder) holder).type.setVisibility(View.GONE);
           ((MusicViewAdapter.DemoViewHolder) holder).txt.setVisibility(View.VISIBLE);
           ((MusicViewAdapter.DemoViewHolder) holder).txt.setText(entity.getTxt());
        }else if(entity.getType().equals("1")){
           ((MusicViewAdapter.DemoViewHolder) holder).txt.setVisibility(View.GONE);
           ((DemoViewHolder) holder).type.setVisibility(View.VISIBLE);
            Glide.with(mContext).load(R.drawable.dk_ic_play_disable).into(((DemoViewHolder)holder).type);
        }else if(entity.getType().equals("2")){
           ((MusicViewAdapter.DemoViewHolder) holder).txt.setVisibility(View.GONE);
           ((MusicViewAdapter.DemoViewHolder) holder).type.setVisibility(View.VISIBLE);
           Glide.with(mContext).load(R.drawable.dk_ic_pause_disable).into(((DemoViewHolder)holder).type);
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
        private TextView time;
        private ImageView type;
        private TextView txt;

        public DemoViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.textView66);
            time = (TextView) itemView.findViewById(R.id.textView67);
            type = (ImageView) itemView.findViewById(R.id.imageView67);
            txt = (TextView) itemView.findViewById(R.id.textView68);
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


    public void refresh(int position){
        mEntityList.get(position).setType("1");
        notifyItemChanged(position);
    }

    public void refreshall(){
        notifyDataSetChanged();
    }
}