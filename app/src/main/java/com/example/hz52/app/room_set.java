package com.example.hz52.app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hz52.app.Entity.MyApp;
import com.example.hz52.app.cofig.Preview;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class room_set extends AppCompatActivity {

    @BindView(R.id.fold)
    ImageView fold;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.subtitle)
    TextView subtitle;
    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.editText2)
    EditText editText2;
    @BindView(R.id.editText3)
    EditText editText3;
    @BindView(R.id.textView133)
    TextView textView133;
    @BindView(R.id.textView134)
    TextView textView134;
    @BindView(R.id.textView135)
    TextView textView135;
    @BindView(R.id.textView136)
    TextView textView136;
    @BindView(R.id.but)
    QMUIRoundButton but;
    @BindView(R.id.but2)
    QMUIRoundButton but2;
    @BindView(R.id.but3)
    QMUIRoundButton but3;
    @BindView(R.id.but4)
    QMUIRoundButton but4;
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
    @BindView(R.id.imageView123)
    ImageView imageView123;
    @BindView(R.id.textView140)
    TextView textView140;
    @BindView(R.id.imageView124)
    ImageView imageView124;
    @BindView(R.id.imageView125)
    ImageView imageView125;
    @BindView(R.id.imageView126)
    ImageView imageView126;

    private int mode = 1;
    private String backgroundId;
    private int mCurrentDialogStyle = com.qmuiteam.qmui.R.style.QMUI_Dialog;
    private boolean bool = false;
    private String roomName;
    private String announce;
    private String password;
    private String userid;
    private String roomid;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_set);
        ButterKnife.bind(this);
        title.setText("房间设置");
        subtitle.setText("保存");
        but.setBackgroundColor(Color.parseColor("#DFEF6598"));
        but2.setBackgroundColor(Color.parseColor("#ABABAB"));
        but3.setBackgroundColor(Color.parseColor("#ABABAB"));
        but4.setBackgroundColor(Color.parseColor("#ABABAB"));
        SharedPreferences sp = getSharedPreferences("User", Context.MODE_PRIVATE);
         userid = sp.getString("userid", "");
         token = sp.getString("token", "");
        SharedPreferences sp1 = getSharedPreferences("Room", Context.MODE_PRIVATE);
        roomid = sp1.getString("roomid", "");
        if(!roomid.equals("")){
            okgo();
        }
    }

    //获取房间信息
    private void okgo() {
        MyApp application = ((MyApp) this.getApplicationContext());

        OkGo.<String>post(application.getUrl() + "/app/room/getInfo?token=" + token)
                .params("userId", userid)
                .params("roomId", roomid)
                .execute(new StringCallback() {

                    @Override
                    public void onSuccess(Response<String> response) {

                        Gson gson = new Gson();
                        Preview prexiew = gson.fromJson(response.body(), Preview.class);

                        if (prexiew.getCode() == 0) {
                            roomName = prexiew.getData().get("roomName").getAsString();
                            announce = prexiew.getData().get("announce").getAsString();
                            mode = prexiew.getData().get("mode").getAsInt();
                            backgroundId = prexiew.getData().get("backgroundId").getAsString();
                            password = prexiew.getData().get("password").getAsString();

                            switch (mode) {
                                case 1:
                                    but.setBackgroundColor(Color.parseColor("#DFEF6598"));
                                    but2.setBackgroundColor(Color.parseColor("#ABABAB"));
                                    but3.setBackgroundColor(Color.parseColor("#ABABAB"));
                                    but4.setBackgroundColor(Color.parseColor("#ABABAB"));
                                    break;
                                case 2:
                                    but2.setBackgroundColor(Color.parseColor("#DFEF6598"));
                                    but.setBackgroundColor(Color.parseColor("#ABABAB"));
                                    but3.setBackgroundColor(Color.parseColor("#ABABAB"));
                                    but4.setBackgroundColor(Color.parseColor("#ABABAB"));
                                    break;
                                case 3:
                                    but3.setBackgroundColor(Color.parseColor("#DFEF6598"));
                                    but2.setBackgroundColor(Color.parseColor("#ABABAB"));
                                    but.setBackgroundColor(Color.parseColor("#ABABAB"));
                                    but4.setBackgroundColor(Color.parseColor("#ABABAB"));
                                    break;
                                case 4:
                                    but4.setBackgroundColor(Color.parseColor("#DFEF6598"));
                                    but2.setBackgroundColor(Color.parseColor("#ABABAB"));
                                    but3.setBackgroundColor(Color.parseColor("#ABABAB"));
                                    but.setBackgroundColor(Color.parseColor("#ABABAB"));
                                    break;
                            }
                            editText.setText(roomName);
                            editText3.setText(announce);
                            editText2.setText(password);
                        } else if (prexiew.getCode() == 40000) {
                            Toast.makeText(room_set.this, prexiew.getMsg() + "", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }

    private void showMessagePositiveDialog() {
        new QMUIDialog.MessageDialogBuilder(this)
                .setTitle("标题")
                .setMessage("修改未保存，确定要退出吗？")
                .addAction("取消", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                    }
                })
                .addAction(0, "确定", QMUIDialogAction.ACTION_PROP_POSITIVE, new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                        finish();
                        overridePendingTransition(R.animator.anim_left_in, R.animator.anim_right_out);
                    }
                })
                .create(mCurrentDialogStyle).show();
    }

    //设置房间信息
    private void okgos() {
        MyApp application = ((MyApp) this.getApplicationContext());
        OkGo.<String>post(application.getUrl() + "/app/room/updateInfo?token=" + token)
                .params("userId", userid)
                .params("roomId", userid)
                .params("roomName", editText.getText().toString())
                .params("announce", editText3.getText().toString())
                .params("password", editText2.getText().toString())
                .params("mode", mode)
                .execute(new StringCallback() {

                    @Override
                    public void onSuccess(Response<String> response) {

                        Gson gson = new Gson();
                        Preview prexiew = gson.fromJson(response.body(), Preview.class);

                        if (prexiew.getCode() == 0) {
                            Toast.makeText(room_set.this, prexiew.getMsg() + "", Toast.LENGTH_SHORT).show();
                            SharedPreferences sp = getSharedPreferences("Room", Context.MODE_PRIVATE);
                            sp.edit().putString("roomid", userid).apply();
                            finish();
                            overridePendingTransition(R.animator.anim_left_in, R.animator.anim_right_out);
                        } else if (prexiew.getCode() == 40000) {
                            Toast.makeText(room_set.this, prexiew.getMsg() + "", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }

    @OnClick({R.id.fold, R.id.subtitle, R.id.but, R.id.but2, R.id.but3, R.id.but4, R.id.imageView119, R.id.textView137, R.id.imageView120, R.id.imageView121, R.id.textView138, R.id.imageView124, R.id.imageView122, R.id.textView139, R.id.imageView125, R.id.imageView123, R.id.textView140, R.id.imageView126})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fold:
                if (editText.getText().toString().equals(roomName) || editText3.getText().toString().equals(announce) || editText2.getText().toString().equals(password)) {
                    showMessagePositiveDialog();
                } else {
                    this.finish();
                    overridePendingTransition(R.animator.anim_left_in, R.animator.anim_right_out);
                }
                break;
            case R.id.subtitle:
                if (!editText.getText().toString().equals("") && !editText3.getText().toString().equals("") && !editText2.getText().toString().equals("")) {
                    okgos();
                }else{
                    Toast.makeText(room_set.this,  "请完善信息", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.but:
                mode = 1;
                but.setBackgroundColor(Color.parseColor("#DFEF6598"));
                but2.setBackgroundColor(Color.parseColor("#ABABAB"));
                but3.setBackgroundColor(Color.parseColor("#ABABAB"));
                but4.setBackgroundColor(Color.parseColor("#ABABAB"));
                break;
            case R.id.but2:
                mode = 2;
                but2.setBackgroundColor(Color.parseColor("#DFEF6598"));
                but.setBackgroundColor(Color.parseColor("#ABABAB"));
                but3.setBackgroundColor(Color.parseColor("#ABABAB"));
                but4.setBackgroundColor(Color.parseColor("#ABABAB"));
                break;
            case R.id.but3:
                mode = 3;
                but3.setBackgroundColor(Color.parseColor("#DFEF6598"));
                but2.setBackgroundColor(Color.parseColor("#ABABAB"));
                but.setBackgroundColor(Color.parseColor("#ABABAB"));
                but4.setBackgroundColor(Color.parseColor("#ABABAB"));
                break;
            case R.id.but4:
                mode = 4;
                but4.setBackgroundColor(Color.parseColor("#DFEF6598"));
                but2.setBackgroundColor(Color.parseColor("#ABABAB"));
                but3.setBackgroundColor(Color.parseColor("#ABABAB"));
                but.setBackgroundColor(Color.parseColor("#ABABAB"));
                break;
            case R.id.imageView119:
            case R.id.textView137:
            case R.id.imageView120:
                Intent intent1 = new Intent(room_set.this, room_theme.class);
                intent1.putExtra("backgroundId", backgroundId);
                startActivity(intent1);
                break;
            case R.id.imageView121:
            case R.id.textView138:
            case R.id.imageView124:
                Intent intent2 = new Intent(room_set.this, room_select_people.class);
                intent2.putExtra("type", 0);
                startActivity(intent2);
                break;
            case R.id.imageView122:
            case R.id.textView139:
            case R.id.imageView125:
                Intent intent3 = new Intent(room_set.this, room_select_people.class);
                intent3.putExtra("type", 1);
                startActivity(intent3);
                break;
            case R.id.imageView123:
            case R.id.textView140:
            case R.id.imageView126:

                break;
        }
    }


    @Override
    public void onBackPressed() {
        if (editText.getText().toString().equals(roomName) || editText3.getText().toString().equals(announce) || editText2.getText().toString().equals(password)) {
            showMessagePositiveDialog();
        } else {
            this.finish();
            overridePendingTransition(R.animator.anim_left_in, R.animator.anim_right_out);
        }
    }
}
