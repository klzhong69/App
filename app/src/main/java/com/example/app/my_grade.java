package com.example.app;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.qmuiteam.qmui.widget.QMUIProgressBar;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class my_grade extends AppCompatActivity {

    @BindView(R.id.imageView40)
    ImageView imageView40;
    @BindView(R.id.textView6)
    TextView textView6;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.imageView17)
    QMUIRadiusImageView imageView17;
    @BindView(R.id.imageView59)
    ImageView imageView59;
    @BindView(R.id.textView51)
    TextView textView51;
    @BindView(R.id.imageView60)
    ImageView imageView60;
    @BindView(R.id.textView52)
    TextView textView52;
    @BindView(R.id.circleProgressBar)
    QMUIProgressBar circleProgressBar;
    @BindView(R.id.textView55)
    TextView textView55;
    @BindView(R.id.textView56)
    TextView textView56;
    @BindView(R.id.imageView57)
    QMUIRadiusImageView imageView57;
    @BindView(R.id.imageView61)
    ImageView imageView61;
    @BindView(R.id.textView58)
    TextView textView58;
    @BindView(R.id.imageView62)
    ImageView imageView62;
    @BindView(R.id.textView59)
    TextView textView59;
    @BindView(R.id.circleProgressBar2)
    QMUIProgressBar circleProgressBar2;
    @BindView(R.id.textView61)
    TextView textView61;
    @BindView(R.id.textView62)
    TextView textView62;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_grade);
        ButterKnife.bind(this);

        circleProgressBar.setMaxValue(100);
        circleProgressBar2.setMaxValue(100);

        circleProgressBar.setProgress(90);
        circleProgressBar2.setProgress(90);

        circleProgressBar.setQMUIProgressBarTextGenerator(new QMUIProgressBar.QMUIProgressBarTextGenerator() {
            @Override
            public String generateText(QMUIProgressBar progressBar, int value, int maxValue) {
                return 100 * value / maxValue + "%";
            }
        });

        circleProgressBar2.setQMUIProgressBarTextGenerator(new QMUIProgressBar.QMUIProgressBarTextGenerator() {
            @Override
            public String generateText(QMUIProgressBar progressBar, int value, int maxValue) {
                return 100 * value / maxValue + "%";
            }
        });
    }
}
