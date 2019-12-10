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
import com.example.app.Entity.Package1;
import com.example.app.Entity.Package1;
import com.example.app.R;

import java.util.List;

public class Package1Adapter extends RecyclerView.Adapter {
private Context mContext;
private List<Package1> mEntityList;
private Package1Adapter.OnItemClickListener mOnItemClickListener;
public Package1Adapter(Context context, List<Package1> entityList){
        this.mContext = context;
        this.mEntityList = entityList;
        }


@NonNull
@Override
public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.my_package1, parent, false);
        return new Package1Adapter.DemoViewHolder(view);
        }

public interface OnItemClickListener {
    void onItemClick(View view, int position);
    void onItemLongClick(View view, int position);
}


    public void setOnItemClickListener(Package1Adapter.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Package1 entity = mEntityList.get(position);

        ((Package1Adapter.DemoViewHolder)holder).mName.setText(entity.getName());
        Glide.with(mContext).load(entity.getIma()).into(((Package1Adapter.DemoViewHolder)holder).mIma);

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
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mEntityList.size();
    }

private class DemoViewHolder extends RecyclerView.ViewHolder{

    private ImageView mIma;
    private TextView mName;

    public DemoViewHolder(View itemView) {
        super(itemView);
        mIma = (ImageView) itemView.findViewById(R.id.imageView18);
        mName = (TextView) itemView.findViewById(R.id.textView18);
    }
}


    public void addData(int position,Package1 entity) {
        mEntityList.add(position,entity);
        notifyItemInserted(position);
    }

    public void removeData(int position) {
        mEntityList.remove(position);
        notifyItemRemoved(position);
    }


}
