package com.example.hz52.app.MQ;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.hz52.app.Entity.MyApp;
import com.example.hz52.app.MainActivity;
import com.example.hz52.app.Sqlentity.Chat;
import com.example.hz52.app.chat;
import com.example.hz52.app.Entity.Mess;
import com.example.hz52.app.dao.mChatDao;
import com.example.hz52.app.find_make;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.List;
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
            SharedPreferences sp = context.getSharedPreferences("User", Context.MODE_PRIVATE);
            String userid = sp.getString("userid", "");
            String token = sp.getString("token", "");
            String password = "";
            //写上自己的url
            String uri = "tcp://mqtt2.weiyunhezi.com:1883";
            subTopic = "user/" + userid;
            mqttAndroidClient = new MqttAndroidClient(
                    context,
                    uri, token);

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
                    SharedPreferences sp = context.getSharedPreferences("User", Context.MODE_PRIVATE);
                    String userid = sp.getString("userid","");
                    System.out.println("消息"+message.toString());
                    if (!message.toString().equals("")) {
                        MyApp application = ((MyApp) context.getApplicationContext());
                        Gson gson = new Gson();
                        Mess mess = gson.fromJson(message.toString(), Mess.class);
                        switch (mess.getType()){
                            case 1:
                                Chat chat1 = new Chat();
                                chat1.setConversation("office");
                                chat1.setUserId(Long.valueOf(userid));
                                chat1.setState(0);
                                chat1.setSendsrc(mess.getData().get("senderAvatarUrl").getAsString());
                                chat1.setTxt(mess.getData().get("content").getAsString());
                                chat1.setData(mess.getSendTime());
                                chat1.setSendname(mess.getData().get("senderName").getAsString());
                                chat1.setSendId(mess.getData().get("sendId").getAsLong());
                                mChatDao.insert(chat1);
                                List<Chat> offic = application.getOfficmess();
                                offic.add(chat1);
                                application.setOfficmess(offic);
                                break;
                            case 2:
                                break;
                            case 3:
                                Chat chat3 = new Chat();
                                chat3.setConversation("user/"+mess.getData().get("sendId").getAsLong());
                                chat3.setData(mess.getSendTime());
                                chat3.setUserId(Long.valueOf(userid));
                                chat3.setSendId(mess.getData().get("sendId").getAsLong());
                                chat3.setSendsrc(mess.getData().get("senderAvatarUrl").getAsString());
                                chat3.setTxt(mess.getData().get("content").getAsString());
                                chat3.setSendname(mess.getData().get("senderName").getAsString());
                                chat3.setState(0);
                                mChatDao.insert(chat3);
                                List<Chat> user = application.getUsermess();
                                user.add(chat3);
                                application.setUsermess(user);
                                Observable<Integer> observables = Observable.defer(new Callable<ObservableSource<? extends Integer>>() {
                                    @Override
                                    public ObservableSource<? extends Integer> call() throws Exception {
                                        return Observable.just(1);
                                    }
                                });
                                observables.subscribe(MainActivity.observers);
                                if(chat.isFront){
                                    if(chat.sendid == mess.getData().get("sendId").getAsLong()){
                                        Observable<Chat> observable = Observable.defer(new Callable<ObservableSource<? extends Chat>>() {
                                            @Override
                                            public ObservableSource<? extends Chat> call() throws Exception {
                                                return Observable.just(chat3);
                                            }
                                        });
                                        observable.subscribe(chat.observerchat);
                                    }
                                }
                                break;
                            case 4:
                                Observable<JsonObject> observable4 = Observable.defer(new Callable<ObservableSource<? extends JsonObject>>() {
                                    @Override
                                    public ObservableSource<? extends JsonObject> call() throws Exception {
                                        return Observable.just(mess.getData());
                                    }
                                });
                                observable4.subscribe(find_make.observer);
                                break;
                        }
                    }


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
            mqttConnectOptions.setUserName(userid);
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
        subscribeToTopic(subTopic);
        subscribeToTopic("office");
        subscribeToTopic("broadcast");
    }


    /**
     * 订阅一个主主题
     *
     * @param subTopic 主题名称
     */
    public static void subscribeToTopic(String subTopic) {
        try {
            mqttAndroidClient.subscribe(subTopic, 1, null, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Log.i(TAG, "MQTT订阅消息成功：" + subTopic);
                    bool = true;
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
     */
    public static void publishMessage(String topic, String msg) {
        if (mqttAndroidClient != null && mqttAndroidClient.isConnected()) {
            try {
                Log.d(TAG, "publishMessage: 发送" + msg);
                mqttAndroidClient.publish(topic, msg.getBytes(), 1, false);
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
