package com.example.hz52.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class system_information extends AppCompatActivity {

    private static View convertView;
    private static ViewHolder viewHolder;
    private static LayoutInflater inflater;
    @BindView(R.id.fold)
    ImageView fold;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.subtitle)
    TextView subtitle;
    @BindView(R.id.scrollable)
    NestedScrollView scrollmess;
    @BindView(R.id.layout)
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_information);
        ButterKnife.bind(this);

        inflater = LayoutInflater.from(this);

        title.setText("系统消息");
        subtitle.setText("");

        viewHolder = new ViewHolder();
        scrollmess.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                scrollmess.post(new Runnable() {
                    public void run() {
                        scrollmess.fullScroll(View.FOCUS_DOWN);
                    }
                });
            }
        });

        initData();
    }

    @OnClick(R.id.fold)
    public void onViewClicked() {
        this.finish();overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);;
    }

    @Override
    public void onBackPressed() {
        this.finish();overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }


    public static class ViewHolder {
        private TextView txt;
        private ImageView imagesrc;
        private ImageView head;
        private TextView time;
    }


    public static View add(String txt, String head, String time, String imagesrc) {
        convertView = inflater.inflate(R.layout.mess_system, null);
        viewHolder.txt = (TextView) convertView.findViewById(R.id.textView185);
        viewHolder.time = (TextView) convertView.findViewById(R.id.textView184);
        viewHolder.head = (ImageView) convertView.findViewById(R.id.imageView2);
        viewHolder.imagesrc = (ImageView) convertView.findViewById(R.id.imageView145);
        convertView.setTag(viewHolder);
        viewHolder.txt.setText(txt);
        viewHolder.time.setText(time);
        Glide.with(convertView).load(head).into(viewHolder.head);
        Glide.with(convertView).load(imagesrc).into(viewHolder.imagesrc);
        return convertView;
    }


    public void initData() {
        layout.addView(add("系统消息系统消息系统消息系统消息系统消息系统消息系统消系统消息系统消息消息系统消息系统消息系统消息系统消息", "https://momeak.oss-cn-shenzhen.aliyuncs.com/h4.jpg", "2019-10-30 19:58", "https://momeak.oss-cn-shenzhen.aliyuncs.com/b1.png"));
        layout.addView(add("系统消息系统消息系统消息系统消息系统消息系统消息系统消系统消息系统消息消息系统消息系统消息系统消息系统消息", "https://momeak.oss-cn-shenzhen.aliyuncs.com/h4.jpg", "2019-10-30 19:59", "https://momeak.oss-cn-shenzhen.aliyuncs.com/b1.png"));
    }
}
