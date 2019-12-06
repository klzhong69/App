package com.example.myapplication.cofig;

import com.example.myapplication.utils.Translation;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * 网络请求接口实例
 */
public interface IService {


    //天气网址：https://www.weather.com.cn/data/sk/101010100.html
    @GET("data/sk/{location}")
    Observable<Translation> getCall();


    // @GET注解的作用:采用Get方法发送网络请求

    // getCall() = 接收网络请求数据的方法
    // 其中返回类型为Call<*>，*是接收数据的类（即上面定义的Translation类）
    // 如果想直接获得Responsebody中的内容，可以定义网络请求返回值为Call<ResponseBody>

    @POST("translate?doctype=json&jsonversion=&type=&keyfrom=&model=&mid=&imei=&vendor=&screen=&ssid=&network=&abtest=")
    @FormUrlEncoded
    Call<Translation> postCall(@Body() String targetSentence);

    @POST("data/sk/{location}")
    Observable<Translation> getNewsWithRxJava(@Query("key") String key, @Query("type") String type);
}
