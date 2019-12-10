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
import com.example.app.Entity.Faxan;
import com.example.app.R;

import java.util.List;

public class ListViewAdapter extends RecyclerView.Adapter {
    private static final String TAG = ListViewAdapter.class.getSimpleName();

    private Context mContext;
    private List<Faxan> mEntityList;
    private ListViewAdapter.OnItemClickListener mOnItemClickListener;
    public ListViewAdapter (Context context, List<Faxan> entityList){
        this.mContext = context;
        this.mEntityList = entityList;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.faxlist, parent, false);
        return new ListViewAdapter.DemoViewHolder(view);
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }


    public void setOnItemClickListener(ListViewAdapter.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Faxan entity = mEntityList.get(position);

        ((ListViewAdapter.DemoViewHolder)holder).name.setText(entity.getName());
        ((ListViewAdapter.DemoViewHolder)holder).txt.setText(entity.getTxt());
        ((ListViewAdapter.DemoViewHolder)holder).type.setText(entity.getType());
        ((ListViewAdapter.DemoViewHolder)holder).popul.setText(entity.getPopul());
        ((ListViewAdapter.DemoViewHolder)holder).collection.setText(entity.getCollection());
        Glide.with(mContext).load(entity.getImagesrc()).into(((ListViewAdapter.DemoViewHolder)holder).imagesrc);
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

        private TextView name;
        private TextView txt;
        private TextView type;
        private TextView popul;
        private TextView collection;
        private ImageView imagesrc;

        public DemoViewHolder(View itemView) {
            super(itemView);
            name=(TextView) itemView.findViewById(R.id.textView8);
            txt=(TextView) itemView.findViewById(R.id.textView15);
            type=(TextView) itemView.findViewById(R.id.textView19);
            popul=(TextView) itemView.findViewById(R.id.textView17);
            collection=(TextView) itemView.findViewById(R.id.textView18);
            imagesrc=(ImageView) itemView.findViewById(R.id.imageView20);
        }
    }


    public void addData(int position,Faxan entity) {
        mEntityList.add(position,entity);
        notifyItemInserted(position);
    }

    public void removeData(int position) {
        mEntityList.remove(position);
        notifyItemRemoved(position);
    }
}
