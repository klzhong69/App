package com.example.hz52.app.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hz52.app.Entity.Holdpeople;
import com.example.hz52.app.R;

import java.util.HashMap;
import java.util.List;

public class HoldpeopleAdapter extends RecyclerView.Adapter {
    private static final String TAG = HoldpeopleAdapter.class.getSimpleName();

    private Context mContext;
    public static List<Holdpeople> mEntityList;
    private HoldpeopleAdapter.OnItemClickListener mOnItemClickListener;

    public static HashMap<Integer, Boolean> states = new HashMap<Integer, Boolean>();  //在这里要做判断保证只有一个RadioButton被选中

    public HoldpeopleAdapter(Context context, List<Holdpeople> entityList) {
        this.mContext = context;
        this.mEntityList = entityList;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.room_hold_people, parent, false);
        return new HoldpeopleAdapter.DemoViewHolder(view);
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }


    public void setOnItemClickListener(HoldpeopleAdapter.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Holdpeople entity = mEntityList.get(position);

        ((HoldpeopleAdapter.DemoViewHolder) holder).id.setText(entity.getId());
        ((HoldpeopleAdapter.DemoViewHolder) holder).name.setText(entity.getName());

        if(entity.getType().equals("0")){
            ((HoldpeopleAdapter.DemoViewHolder)holder).sum.setOnClickListener(new View.OnClickListener() {
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
            if (states.get(position) != null) {
                if(states.get(position)){
                    System.out.println("这里");
                    ((HoldpeopleAdapter.DemoViewHolder)holder).sum.setChecked(true);
                }else{
                    System.out.println("这里2");
                    ((HoldpeopleAdapter.DemoViewHolder)holder).sum.setChecked(false);
                }


            }
            ((HoldpeopleAdapter.DemoViewHolder) holder).sum.setVisibility(View.VISIBLE);
        }else{
            ((HoldpeopleAdapter.DemoViewHolder) holder).sum.setVisibility(View.GONE);

        }

        Glide.with(mContext).load(entity.getUserima()).into(((HoldpeopleAdapter.DemoViewHolder)holder).userima);
        Glide.with(mContext).load(R.drawable.l3).into(((DemoViewHolder)holder).grade);
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

        private TextView id;
        private ImageView userima;
        private TextView name;
        private ImageView grade;
        private RadioButton sum;

        public DemoViewHolder(View itemView) {
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.textView114);
            name = (TextView) itemView.findViewById(R.id.textView66);
            grade = (ImageView) itemView.findViewById(R.id.imageView66);
            sum = (RadioButton) itemView.findViewById(R.id.radioButton);
            userima = (ImageView) itemView.findViewById(R.id.imageView18);

        }
    }


    public void addData(int position, Holdpeople entity) {
        mEntityList.add(position, entity);
        notifyItemInserted(position);
    }

    public void removeData(int position) {
        mEntityList.remove(position);
        notifyItemRemoved(position);
    }

}