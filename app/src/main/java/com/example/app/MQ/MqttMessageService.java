package com.example.app.MQ;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.app.mqtttest;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.Map;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;

public class MqttMessageService extends Service {

    /**
     * 订阅的主题
     */
    private static String subTopic;
    private static MqttConnectOptions mqttConnectOptions;
    private static MqttAndroidClient mqttAndroidClient;
    public static final String TAG = "MqttMessageService";
    private static boolean bool = false;
    private static int sum;


    public static void create(Context context) {

        //初始化mqtt配置
        initMqtt(context);
        //连接mqtt
        connectMqtt();

    }


    /**
     * mqtt初始化
     */
    public static void initMqtt(Context context) {
        try {
            sum = 0;
            String username = "55556666";
            String password = "";
            //写上自己的url
            String uri = "tcp://mqtt2.weiyunhezi.com:1883";
            subTopic = "user/55556666";
            mqttAndroidClient = new MqttAndroidClient(
                    context,
                    uri, "test");

            mqttAndroidClient.setCallback(new MqttCallbackExtended() {
                @Override
                public void connectComplete(boolean reconnect, String serverURI) {
                    if (reconnect) {
                        Log.i(TAG, "MQTT重新连接成功！serverURI:" + serverURI);
                    } else {
                        Log.i(TAG, "MQTT连接成功！serverURI:" + serverURI);
                    }
                    subscribeAllTopics();
                }

                @Override
                public void connectionLost(Throwable cause) {
                    Log.i(TAG, "MQTT连接断开！" + cause.getCause());
                }

                @Override
                public void messageArrived(String topic, MqttMessage message) {
                    Observable<String> observable = Observable.defer(new Callable<ObservableSource<? extends String>>() {
                        @Override
                        public ObservableSource<? extends String> call() throws Exception {
                            return Observable.just(topic + "~" + message.toString() + "~" + sum+ "~" + bool);
                        }
                    });
                    observable.subscribe(mqtttest.observer);
                    sum++;

                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    try {
                        MqttMessage mqttMessage = token.getMessage();
                        Log.i(TAG, "消息发送成功:" + mqttMessage.toString());
                    } catch (MqttException e) {
                        e.printStackTrace();
                    }
                }

            });

            // 新建连接设置
            mqttConnectOptions = new MqttConnectOptions();
            mqttConnectOptions.setUserName(username);
            mqttConnectOptions.setPassword(password.toCharArray());
            //断开后，是否自动连接
            mqttConnectOptions.setAutomaticReconnect(true);
            //是否清空客户端的连接记录。若为true，则断开后，broker将自动清除该客户端连接信息
            mqttConnectOptions.setCleanSession(false);
            //设置超时时间，单位为秒
            mqttConnectOptions.setConnectionTimeout(15);
            //心跳时间，单位为秒。即多长时间确认一次Client端是否在线
            mqttConnectOptions.setKeepAliveInterval(30);
            //允许同时发送几条消息（未收到broker确认信息）
            mqttConnectOptions.setMaxInflight(30);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 连接mqtt
     */
    public static void connectMqtt() {

        try {
            mqttAndroidClient.connect(mqttConnectOptions, null, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {

                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Log.e(TAG, "MQTT连接失败！！！！" + exception.getCause());
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Log.e(TAG, "30s后，尝试重新连接");
                            try {
                                Thread.sleep(1000 * 30);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            connectMqtt();
                        }
                    }).start();
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }


    /**
     * 订阅所有主题
     */
    private static void subscribeAllTopics() {
        //订阅主消息主题和更新消息主题
        subscribeToTopic(subTopic, 1);

    }


    /**
     * 订阅一个主主题
     *
     * @param subTopic 主题名称
     */
    public static void subscribeToTopic(String subTopic, int qos) {
        try {
            mqttAndroidClient.subscribe(subTopic, qos, null, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Log.i(TAG, "MQTT订阅消息成功：" + subTopic);
                    bool=true;
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Log.d(TAG, "MQTT订阅消息失败！" + subTopic);
                }
            });
        } catch (MqttException ex) {
            Log.d(TAG, "subscribeToTopic: Exception whilst subscribing");
            ex.printStackTrace();
        }
    }

    /**
     * 发布主题
     *
     * @param topic 主题
     * @param msg   内容
     * @param qos   qos
     */
    public static void publishMessage(String topic, String msg, int qos) {
        if (mqttAndroidClient != null && mqttAndroidClient.isConnected()) {
            try {
                Log.d(TAG, "publishMessage: 发送" + msg);
                mqttAndroidClient.publish(topic, msg.getBytes(), qos, false);
            } catch (Exception e) {
                Log.e(TAG, "publishMessage: Error Publishing: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            Log.e(TAG, "publishMessage失败，MQTT未连接 ");
        }
    }



    /**
     * 退订一个主主题
     *
     * @param subTopic 主题名称
     */
    public static void unsubscribeToTopic(String subTopic) {
        try {
            mqttAndroidClient.unsubscribe(subTopic, null, new IMqttActionListener() {

                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Log.i(TAG, "MQTT退订消息成功：" + subTopic);
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Log.i(TAG, "MQTT退订消息失败：" + subTopic);
                }
            });
        } catch (MqttException ex) {
            Log.d(TAG, "subscribeToTopic: Exception whilst subscribing");
            ex.printStackTrace();
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return Service.START_NOT_STICKY;
    }


    public static void destroy() {
        Log.e(TAG, "关闭MQTT");
        sum = 0;
        //断开mqtt连接
        try {
            if (mqttAndroidClient != null && mqttAndroidClient.isConnected()) {
                mqttAndroidClient.disconnect();
                mqttAndroidClient.unregisterResources();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
