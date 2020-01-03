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
import com.example.app.Entity.Mygold;
import com.example.app.R;
import com.example.app.my_gold;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;

public class GoldViewAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<Mygold> mEntityList;
    private GoldViewAdapter.OnItemClickListener mOnItemClickListener;
    public HashMap<Integer, Boolean> states = new HashMap<Integer, Boolean>();  //在这里要做判断保证只有一个RadioButton被选中

    public GoldViewAdapter(Context context, List<Mygold> entityList) {
        this.mContext = context;
        this.mEntityList = entityList;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.my_gold, parent, false);
        return new GoldViewAdapter.DemoViewHolder(view);
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }


    public void setOnItemClickListener(GoldViewAdapter.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Mygold entity = mEntityList.get(position);

        ((GoldViewAdapter.DemoViewHolder) holder).fold.setText(entity.getFolds());
        ((GoldViewAdapter.DemoViewHolder) holder).amount.setText(entity.getAmount());

            ((GoldViewAdapter.DemoViewHolder)holder).imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //把所有的按钮的状态设置为没选中
                    for (int i = 0; i < mEntityList.size(); i++) {
                        states.put(i, false);
                    }
                    //然后设置点击的那个按钮设置状态为选中
                    states.put(position, true);    //这样所有的条目中只有一个被选中！
                    notifyDataSetChanged();//刷新适配器
                }
            });
            if (states.get(position) == null || states.get(position) == false) {  //true说明没有被选中
                ((GoldViewAdapter.DemoViewHolder) holder).fold.setTextColor(mContext.getResources().getColor(R.color.qmui_config_color_black,mContext.getTheme()));
                ((GoldViewAdapter.DemoViewHolder) holder).amount.setTextColor(mContext.getResources().getColor(R.color.qmui_config_color_black,mContext.getTheme()));
                ((GoldViewAdapter.DemoViewHolder)holder).imageView.setImageResource(R.color.qmui_config_color_white);
            } else {
                ((GoldViewAdapter.DemoViewHolder) holder).fold.setTextColor(mContext.getResources().getColor(R.color.qmui_config_color_white,mContext.getTheme()));
                ((GoldViewAdapter.DemoViewHolder) holder).amount.setTextColor(mContext.getResources().getColor(R.color.qmui_config_color_white,mContext.getTheme()));
                ((GoldViewAdapter.DemoViewHolder)holder).imageView.setImageResource(R.color.tabbarcolor);

                Observable<Integer> observable = Observable.defer(new Callable<ObservableSource<? extends Integer>>() {
                    @Override
                    public ObservableSource<? extends Integer> call() throws Exception {
                        return Observable.just(position);
                    }
                });
                observable.subscribe(my_gold.observer);

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
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mEntityList.size();
    }

    private class DemoViewHolder extends RecyclerView.ViewHolder {

        private TextView fold;
        private TextView amount;
        private ImageView imageView;

        public DemoViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView41);
            fold = (TextView) itemView.findViewById(R.id.textView64);
            amount = (TextView) itemView.findViewById(R.id.textView65);
        }
    }


    public void addData(int position, Mygold entity) {
        mEntityList.add(position, entity);
        notifyItemInserted(position);
    }

    public void removeData(int position) {
        mEntityList.remove(position);
        notifyItemRemoved(position);
    }
}