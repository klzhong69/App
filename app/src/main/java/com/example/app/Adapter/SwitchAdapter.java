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
import com.example.app.Sqlentity.User;

import java.util.List;

public class SwitchAdapter extends RecyclerView.Adapter {
        private static final String TAG = SwitchAdapter.class.getSimpleName();

        private Context mContext;
        private List<User> mEntityList;
        private SwitchAdapter.OnItemClickListener mOnItemClickListener;

    public SwitchAdapter(Context context, List<User> entityList) {
            this.mContext = context;
            this.mEntityList = entityList;
        }


        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.my_switch, parent, false);
            return new SwitchAdapter.DemoViewHolder(view);
        }

        public interface OnItemClickListener {
            void onItemClick(View view, int position);

            void onItemLongClick(View view, int position);
        }


        public void setOnItemClickListener(SwitchAdapter.OnItemClickListener onItemClickListener) {
            mOnItemClickListener = onItemClickListener;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            User entity = mEntityList.get(position);

            ((SwitchAdapter.DemoViewHolder) holder).name.setText(entity.getName());
            ((SwitchAdapter.DemoViewHolder) holder).id.setText(entity.getUserId().toString());
            Glide.with(mContext).load(entity.getUsersrc()).into(((SwitchAdapter.DemoViewHolder)holder).userima);
            if(entity.getState()==1){
                Glide.with(mContext).load(R.drawable.qmui_icon_checkmark).into(((DemoViewHolder)holder).type);
            }else{
                Glide.with(mContext).load("").into(((DemoViewHolder)holder).type);
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


            private ImageView userima;
            private TextView name;
            private TextView id;
            private ImageView type;

            public DemoViewHolder(View itemView) {
                super(itemView);
                name = (TextView) itemView.findViewById(R.id.textView8);
                id = (TextView) itemView.findViewById(R.id.textView154);
                userima = (ImageView) itemView.findViewById(R.id.imageView20);
                type = (ImageView) itemView.findViewById(R.id.imageView43);

            }
        }


        public void addData(int position, User entity) {
            mEntityList.add(position, entity);
            notifyItemInserted(position);
        }

        public void removeData(int position) {
            mEntityList.remove(position);
            notifyItemRemoved(position);
        }
}
