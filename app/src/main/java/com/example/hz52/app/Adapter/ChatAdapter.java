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
import com.cxd.chatview.moudle.ChatView;
import com.example.hz52.app.Entity.Chats;
import com.example.hz52.app.R;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter {
    private static final String TAG = ChatAdapter.class.getSimpleName();

    private Context mContext;
    private List<Chats> mEntityList;
    private ChatAdapter.OnItemClickListener mOnItemClickListener;

    public ChatAdapter(Context context, List<Chats> entityList) {
        this.mContext = context;
        this.mEntityList = entityList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.chat_txt, parent, false);
        return new ChatAdapter.DemoViewHolder(view);
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }


    public void setOnItemClickListener(ChatAdapter.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Chats entity = mEntityList.get(position);

        if(entity.getType()==0){
            ((ChatAdapter.DemoViewHolder) holder).head1.setVisibility(View.GONE);
            ((ChatAdapter.DemoViewHolder) holder).head2.setVisibility(View.GONE);
            ((ChatAdapter.DemoViewHolder) holder).head3.setVisibility(View.VISIBLE);
            ((ChatAdapter.DemoViewHolder) holder).textViewtime.setText(entity.getTime());

        }else if(entity.getType()==1){
            ((ChatAdapter.DemoViewHolder) holder).head1.setVisibility(View.VISIBLE);
            ((ChatAdapter.DemoViewHolder) holder).head2.setVisibility(View.GONE);
            ((ChatAdapter.DemoViewHolder) holder).head3.setVisibility(View.GONE);
            Glide.with(mContext).load(entity.getUserima()).into(((ChatAdapter.DemoViewHolder) holder).imageView1);
            ((ChatAdapter.DemoViewHolder) holder).text1.setText(entity.getTxt());

        }else if(entity.getType()==2){
            ((ChatAdapter.DemoViewHolder) holder).head1.setVisibility(View.GONE);
            ((ChatAdapter.DemoViewHolder) holder).head2.setVisibility(View.VISIBLE);
            ((ChatAdapter.DemoViewHolder) holder).head3.setVisibility(View.GONE);
            Glide.with(mContext).load(entity.getUserima()).into(((ChatAdapter.DemoViewHolder) holder).imageView2);
            ((ChatAdapter.DemoViewHolder) holder).text2.setText(entity.getTxt());

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

        private RelativeLayout head1;
        private RelativeLayout head2;
        private RelativeLayout head3;
        private ImageView imageView1;
        private ChatView chatView1;
        private TextView text1;
        private ImageView imageView2;
        private ChatView chatView2;
        private TextView text2;

        private TextView textViewtime;


        public DemoViewHolder(View itemView) {
            super(itemView);
            head1 = (RelativeLayout) itemView.findViewById(R.id.head1);
            head2 = (RelativeLayout) itemView.findViewById(R.id.head2);
            head3 = (RelativeLayout) itemView.findViewById(R.id.head3);
            imageView1 = (ImageView) itemView.findViewById(R.id.imageView1);
            text1 = (TextView) itemView.findViewById(R.id.text1);
            imageView2 = (ImageView) itemView.findViewById(R.id.imageView2);
            text2= (TextView) itemView.findViewById(R.id.text2);

            textViewtime= (TextView) itemView.findViewById(R.id.textViewtime);

        }
    }


    public void addData(int position, Chats entity) {
        mEntityList.add(position, entity);
        notifyItemInserted(position);
    }

    public void removeData(int position) {
        mEntityList.remove(position);
        notifyItemRemoved(position);
    }
}
