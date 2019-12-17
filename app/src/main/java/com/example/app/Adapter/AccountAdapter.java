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
import com.example.app.R;
import com.example.app.Sqlentity.Account;

import java.util.List;

public class AccountAdapter extends RecyclerView.Adapter {
    private static final String TAG = AccountAdapter.class.getSimpleName();

    private Context mContext;
    private List<Account> mEntityList;
    private AccountAdapter.OnItemClickListener mOnItemClickListener;

    public AccountAdapter(Context context, List<Account> entityList) {
        this.mContext = context;
        this.mEntityList = entityList;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.account, parent, false);
        return new AccountAdapter.DemoViewHolder(view);
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }


    public void setOnItemClickListener(AccountAdapter.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Account entity = mEntityList.get(position);

        switch (entity.getType()){
            case 0:
                ((AccountAdapter.DemoViewHolder) holder).txt.setText("微信");
                Glide.with(mContext).load("").into(((AccountAdapter.DemoViewHolder)holder).ima);
                break;
            case 1:
                ((AccountAdapter.DemoViewHolder) holder).txt.setText("支付宝");
                Glide.with(mContext).load("").into(((AccountAdapter.DemoViewHolder)holder).ima);
                break;
            case 2:
                ((AccountAdapter.DemoViewHolder) holder).txt.setText("银行卡");
                Glide.with(mContext).load("").into(((AccountAdapter.DemoViewHolder)holder).ima);
                break;
        }

        ((AccountAdapter.DemoViewHolder) holder).name.setText(entity.getName());
        if(entity.getState()==1){
            ((AccountAdapter.DemoViewHolder) holder).state.setText("默认");
        }else{
            ((AccountAdapter.DemoViewHolder) holder).state.setText("");
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

        private TextView txt;
        private ImageView ima;
        private TextView name;
        private TextView state;

        public DemoViewHolder(View itemView) {
            super(itemView);
            txt = (TextView) itemView.findViewById(R.id.textView87);
            name = (TextView) itemView.findViewById(R.id.textView169);
            state = (TextView) itemView.findViewById(R.id.textView170);
            ima = (ImageView) itemView.findViewById(R.id.imageView76);

        }
    }


    public void addData(int position, Account entity) {
        mEntityList.add(position, entity);
        notifyItemInserted(position);
    }

    public void removeData(int position) {
        mEntityList.remove(position);
        notifyItemRemoved(position);
    }
}
