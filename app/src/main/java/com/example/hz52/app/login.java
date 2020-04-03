package com.example.hz52.app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.hz52.app.Entity.MyApp;
import com.example.hz52.app.Sqlentity.User;
import com.example.hz52.app.cofig.Initialization;
import com.example.hz52.app.cofig.Preview;
import com.example.hz52.app.dao.mUserDao;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class login extends AppCompatActivity {

    @BindView(R.id.textView155)
    TextView textView155;
    @BindView(R.id.imageView19)
    QMUIRadiusImageView imageView19;
    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.imageView18)
    QMUIRadiusImageView imageView18;
    @BindView(R.id.editText4)
    EditText editText4;
    @BindView(R.id.but)
    QMUIRoundButton but;
    @BindView(R.id.imageView116)
    ImageView imageView116;
    @BindView(R.id.imageView117)
    ImageView imageView117;
    @BindView(R.id.textView156)
    TextView textView156;
    @BindView(R.id.textView157)
    TextView textView157;
    @BindView(R.id.textView158)
    TextView textView158;
    @BindView(R.id.textView159)
    TextView textView159;
    @BindView(R.id.fold)
    ImageView fold;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.subtitle)
    TextView subtitle;
    private int type;
    private int state;
    private QMUITipDialog tipDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        Initialization.setupDatabaseUser(this);
        title.setText("登陆");
        subtitle.setText("");
        editText.setText("");
        editText4.setText("");

        Intent intent = getIntent();
        state = intent.getIntExtra("type", 0);


    }

    /** 判断网络是否连接 */
    public boolean isConnectIsNormal() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info != null && info.isAvailable()) {
            String name = info.getTypeName();
            return true;
        } else {
            Toast.makeText(login.this,  " 无网络连接", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
    @OnClick({R.id.fold,R.id.but, R.id.textView156, R.id.textView157, R.id.textView159})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fold:
                this.finish();
                overridePendingTransition(R.animator.anim_bottom_in, R.animator.anim_bottom_out);
                break;
            case R.id.but:
                if(isConnectIsNormal()) {
                    tipDialog = new QMUITipDialog.Builder(this)
                            .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                            .setTipWord("正在加载")
                            .create();
                    tipDialog.show();
                    okgo();
                }
                break;
            case R.id.textView156:
                Intent intent2 = new Intent(login.this, forget_password.class);
                startActivity(intent2);
                overridePendingTransition(R.animator.anim_right_in, R.animator.anim_left_out);
                break;
            case R.id.textView157:
                Intent intent3 = new Intent(login.this, registered.class);
                startActivity(intent3);
                overridePendingTransition(R.animator.anim_right_in, R.animator.anim_left_out);
                break;
            case R.id.textView159:
                Intent intent4 = new Intent(login.this, agreement.class);
                intent4.putExtra("about", 0);
                startActivity(intent4);
                overridePendingTransition(R.animator.anim_right_in, R.animator.anim_left_out);
                break;
        }
    }

    private void okgo() {
        MyApp application = ((MyApp) this.getApplicationContext());
        type = 1;
        OkGo.<String>post(application.getUrl()+"/app/user/login")
                .params("account",editText.getText().toString())
                .params("password",editText4.getText().toString())
                .params("type",type)
                .execute(new StringCallback() {

                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<String> response) {

                        Gson gson = new Gson();
                        Preview prexiew = gson.fromJson(response.body(), Preview.class);

                        if(prexiew.getCode()==0){
                            Toast.makeText(login.this, prexiew.getMsg()+"", Toast.LENGTH_SHORT).show();

                            String id = prexiew.getData().get("uniqueId").getAsString();
                            String name = prexiew.getData().get("nickname").getAsString();
                            String userima = prexiew.getData().get("avatarUrl").getAsString();
                            String token = prexiew.getData().get("token").getAsString();
                            String phone = prexiew.getData().get("phone").getAsString();
                            String birthday = prexiew.getData().get("birthday").getAsString();
                            String gender = prexiew.getData().get("gender").getAsString();

                            if(state>0){
                                User user = new User();
                                user.setName(name);
                                user.setUsersrc(userima);
                                user.setState(0);
                                user.setUserId(Long.valueOf(id));
                                mUserDao.insert(user);
                            }

                            SharedPreferences sp = getSharedPreferences("User", Context.MODE_PRIVATE);
                            sp.edit().putString("userid", id).apply();
                            sp.edit().putString("token", token).apply();
                            sp.edit().putString("phone", phone).apply();
                            sp.edit().putString("nickname", name).apply();
                            sp.edit().putString("gender", gender).apply();
                            sp.edit().putString("birthday", birthday).apply();
                            sp.edit().putString("avatarUrl", userima).apply();
                            sp.edit().putString("login", "true").apply();

                            ActivityCompat.finishAfterTransition(login.this);

                            tipDialog.dismiss();
                        }else{
                            tipDialog.dismiss();
                            Toast.makeText(login.this, prexiew.getMsg()+"", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }


    @Override
    public void onBackPressed() {
        if(state == 1){
            System.exit(0);
        }else{
            this.finish();
            overridePendingTransition(R.animator.anim_bottom_in, R.animator.anim_bottom_out);
        }

    }
}
