package com.example.app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app.MQ.MqttMessageService;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class mqtttest extends AppCompatActivity {

    @BindView(R.id.button)
    Button button;
    @BindView(R.id.button2)
    Button button2;
    @BindView(R.id.textView187)
    TextView textView187;
    @BindView(R.id.button3)
    Button button3;
    private int i = 0;
    private Boolean bool = true;
    public static Observer<String> observer;
    private Map<String, String> map = new HashMap<String,String>();
    private Map<String, String> maps = new HashMap<String,String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mqtttest);
        ButterKnife.bind(this);

        MqttMessageService.create(this);

        observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(String integer) {
                String[] intter = integer.split("~");
                if(intter[3].equals("true")){
                    map.put(intter[2],intter[0]+"~"+intter[1]);
                }else{
                    maps.put(intter[2],intter[0]+"~"+intter[1]);
                }

                textView187.setText("用户" + intter[0] + "消息" + intter[1]);
                System.out.println("用户" + intter[0] + "消息" + intter[1]);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }


        };


    }

    @OnClick({R.id.button, R.id.button2, R.id.button3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button:
                MqttMessageService.subscribeToTopic("room/1001");
                //System.out.println(map.size()+"/"+maps.size());
                break;
            case R.id.button2:
                i++;
                MqttMessageService.publishMessage("room/1001", "测试" + i);
                break;
            case R.id.button3:
                if (bool) {
                    bool=false;
                    MqttMessageService.unsubscribeToTopic("room/1001");
                    MqttMessageService.destroy();
                    map.clear();
                } else {
                    //初始化mqtt配置
                    MqttMessageService.create(this);
                    bool=true;
                }
                break;
        }
    }

    protected void onDestroy(){
        super.onDestroy();
        MqttMessageService.unsubscribeToTopic("room/1001");
        MqttMessageService.destroy();
        map.clear();
    }
}
