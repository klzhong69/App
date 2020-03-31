package com.example.hz52.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class my_about extends AppCompatActivity {

    @BindView(R.id.fold)
    ImageView fold;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.subtitle)
    TextView subtitle;
    @BindView(R.id.textView155)
    TextView textView155;
    @BindView(R.id.imageView119)
    ImageView imageView119;
    @BindView(R.id.textView137)
    TextView textView137;
    @BindView(R.id.imageView120)
    ImageView imageView120;
    @BindView(R.id.imageView121)
    ImageView imageView121;
    @BindView(R.id.textView138)
    TextView textView138;
    @BindView(R.id.imageView122)
    ImageView imageView122;
    @BindView(R.id.textView139)
    TextView textView139;
    @BindView(R.id.imageView124)
    ImageView imageView124;
    @BindView(R.id.imageView125)
    ImageView imageView125;
    @BindView(R.id.textView165)
    TextView textView165;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_about);
        ButterKnife.bind(this);
        title.setText("关于我们");
        subtitle.setText("");
    }

    @OnClick({R.id.fold, R.id.imageView119, R.id.textView137, R.id.imageView120, R.id.imageView121, R.id.textView138, R.id.imageView122, R.id.textView139, R.id.imageView124, R.id.imageView125, R.id.textView165})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fold:
                this.finish();overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                break;
            case R.id.imageView119:
            case R.id.textView137:
            case R.id.imageView120:
            case R.id.textView165:
                Toast.makeText(my_about.this, "当前已是最新版本", Toast.LENGTH_SHORT).show();
                break;
            case R.id.imageView121:
            case R.id.textView138:
            case R.id.imageView124:
                Intent intent2 = new Intent(my_about.this, agreement.class);
                intent2.putExtra("about",1);
                startActivity(intent2);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                break;
            case R.id.imageView122:
            case R.id.textView139:
            case R.id.imageView125:
                Intent intent3 = new Intent(my_about.this, agreement.class);
                intent3.putExtra("about",2);
                startActivity(intent3);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                break;

        }
    }

    @Override
    public void onBackPressed() {
        this.finish();overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
}
