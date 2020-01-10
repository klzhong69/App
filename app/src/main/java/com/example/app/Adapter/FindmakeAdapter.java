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
import com.example.app.Entity.Findmake;
import com.example.app.Entity.Findmake;
import com.example.app.R;

import java.util.List;

public class FindmakeAdapter extends RecyclerView.Adapter {
    private static final String TAG = FindmakeAdapter.class.getSimpleName();

    private Context mContext;
    private List<Findmake> mEntityList;
    private FindmakeAdapter.OnItemClickListener mOnItemClickListener;

    public FindmakeAdapter(Context context, List<Findmake> entityList) {
        this.mContext = context;
        this.mEntityList = entityList;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.find_make, parent, false);
        return new FindmakeAdapter.DemoViewHolder(view);
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }


    public void setOnItemClickListener(FindmakeAdapter.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Findmake entity = mEntityList.get(position);

        ((FindmakeAdapter.DemoViewHolder) holder).txt.setText(entity.getTxt());
        ((FindmakeAdapter.DemoViewHolder) holder).name.setText(entity.getName());

        Glide.with(mContext).load(entity.getUserima()).into(((FindmakeAdapter.DemoViewHolder)holder).userima);
        ((FindmakeAdapter.DemoViewHolder) holder).gender.setVisibility(View.GONE);
        ((FindmakeAdapter.DemoViewHolder) holder).grade.setVisibility(View.GONE);
        /*if(entity.getGender().equals("")){
            ((FindmakeAdapter.DemoViewHolder) holder).gender.setVisibility(View.GONE);
        }else{
            if(entity.getGender().equals("0")){
                Glide.with(mContext).load(R.drawable.nan).into(((FindmakeAdapter.DemoViewHolder)holder).gender);
            }else{
                Glide.with(mContext).load(R.drawable.nv).into(((FindmakeAdapter.DemoViewHolder)holder).gender);
            }
        }
       if(entity.getGrade().equals("")){
           ((FindmakeAdapter.DemoViewHolder) holder).grade.setVisibility(View.GONE);
       }else{
           Glide.with(mContext).load(R.drawable.l3).into(((FindmakeAdapter.DemoViewHolder)holder).grade);
       }*/

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

        private ImageView gender;
        private ImageView userima;
        private TextView name;
        private ImageView grade;
        private TextView txt;


        public DemoViewHolder(View itemView) {
            super(itemView);
            txt = (TextView) itemView.findViewById(R.id.textView114);
            name = (TextView) itemView.findViewById(R.id.textView66);
            gender = (ImageView) itemView.findViewById(R.id.imageView31);
            userima = (ImageView) itemView.findViewById(R.id.imageView18);
            grade = (ImageView) itemView.findViewById(R.id.imageView66);

        }
    }


    public void addData(int position, Findmake entity) {
        mEntityList.add(position, entity);
        notifyItemInserted(position);
    }

    public void removeData(int position) {
        mEntityList.remove(position);
        notifyItemRemoved(position);
    }
}
