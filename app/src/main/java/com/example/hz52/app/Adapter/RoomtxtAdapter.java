package com.example.hz52.app.Adapter;

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
import com.example.hz52.app.Entity.Roomtxt;
import com.example.hz52.app.R;

import java.util.List;

public class RoomtxtAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<Roomtxt> mEntityList;
    private OnItemClickListener mOnItemClickListener;
    public RoomtxtAdapter (Context context, List<Roomtxt> entityList){
        this.mContext = context;
        this.mEntityList = entityList;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.room_txt, parent, false);
        return new DemoViewHolder(view);
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Roomtxt entity = mEntityList.get(position);

        if(entity.getType().equals("0")){
            ((DemoViewHolder)holder).relativeLayout1.setVisibility(View.GONE);
            ((DemoViewHolder)holder).relativeLayout2.setVisibility(View.VISIBLE);
            if(entity.getTxt().equals("")){
                ((DemoViewHolder)holder).mTxt.setVisibility(View.GONE);
            }else{
                ((DemoViewHolder)holder).mTxt.setText(entity.getTxt());
            }
        }else if(entity.getType().equals("1")){
            ((DemoViewHolder)holder).relativeLayout2.setVisibility(View.GONE);
            ((DemoViewHolder)holder).relativeLayout1.setVisibility(View.VISIBLE);
            if(entity.getGrade().equals("")){
                ((DemoViewHolder)holder).mGrade.setVisibility(View.GONE);
            }else{
                Glide.with(mContext).load(R.drawable.l3).into(((DemoViewHolder)holder).mGrade);
            }
            ((DemoViewHolder)holder).mName.setText(entity.getName());
            ((DemoViewHolder)holder).mText.setText(entity.getText());
        }

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
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mEntityList.size();
    }

    private class DemoViewHolder extends RecyclerView.ViewHolder{

        private RelativeLayout relativeLayout1;
        private RelativeLayout relativeLayout2;

        private TextView mText;
        private TextView mName;
        private TextView mTxt;
        private ImageView mGrade;

        public DemoViewHolder(View itemView) {
            super(itemView);
            relativeLayout1 = (RelativeLayout) itemView.findViewById(R.id.relacy1);
            relativeLayout2 = (RelativeLayout) itemView.findViewById(R.id.relacy2);

            mText = (TextView) itemView.findViewById(R.id.textView35);
            mName = (TextView) itemView.findViewById(R.id.textView34);
            mGrade = (ImageView) itemView.findViewById(R.id.imageView42);
            mTxt = (TextView) itemView.findViewById(R.id.textView37);
        }
    }


    public void addData(int position, Roomtxt entity) {
        mEntityList.add(position,entity);
        notifyItemInserted(position);
    }

    public void removeData(int position) {
        mEntityList.remove(position);
        notifyItemRemoved(position);
    }




}

