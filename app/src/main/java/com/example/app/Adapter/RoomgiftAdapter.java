package com.example.app.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.app.Entity.Roomgift;
import com.example.app.Entity.Roomgift;
import com.example.app.R;

import org.w3c.dom.Text;

import java.util.List;

public class RoomgiftAdapter extends RecyclerView.Adapter {
    private static final String TAG = RoomgiftAdapter.class.getSimpleName();

    private Context mContext;
    private List<Roomgift> mEntityList;
    private RoomgiftAdapter.OnItemClickListener mOnItemClickListener;

    public RoomgiftAdapter(Context context, List<Roomgift> entityList) {
        this.mContext = context;
        this.mEntityList = entityList;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.room_gift, parent, false);
        return new RoomgiftAdapter.DemoViewHolder(view);
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }


    public void setOnItemClickListener(RoomgiftAdapter.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Roomgift entity = mEntityList.get(position);

        ((DemoViewHolder) holder).giftname.setText(entity.getGiftname());
        ((DemoViewHolder) holder).num.setText(entity.getNum());
        //Glide.with(mContext).load(entity.getIma()).into(((RoomgiftAdapter.DemoViewHolder)holder).ima);
        Glide.with(mContext).load(R.drawable.liwu).into(((RoomgiftAdapter.DemoViewHolder)holder).ima);
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

        private ImageView ima;
        private TextView giftname;
        private TextView num;
        private RelativeLayout relativeLayout;

        DemoViewHolder(View itemView) {
            super(itemView);
            ima = (ImageView) itemView.findViewById(R.id.imageView18);
            giftname = (TextView) itemView.findViewById(R.id.textView146);
            num = (TextView) itemView.findViewById(R.id.textView147);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.rect_views);
        }
    }


    public void addData(int position, Roomgift entity) {
        mEntityList.add(position, entity);
        notifyItemInserted(position);
    }

    public void removeData(int position) {
        mEntityList.remove(position);
        notifyItemRemoved(position);
    }
}
