package com.example.app.cofig;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonActivity {

    /**
     * 解析JSON对象
     * {
     *     "name":"李四",
     *     "age":99,
     *     "hobby":"爱好是练习截拳道"
     * }
     * @param result
     */
    public void analyzeJSON1(String result) {


        try{
            JSONObject jsonObject = new JSONObject(result);
            /**
             * 为什么要使用jsonObject.optString， 不使用jsonObject.getString
             * 因为jsonObject.optString获取null不会报错
             */
            String name = jsonObject.optString("name", null);
            int age = jsonObject.optInt("age", 0);
            String hobby = jsonObject.optString("hobby", null);



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析JSON对象-带Key
     * {
     *     "student":{
     *         "name":"李四",
     *         "age":99,
     *         "hobby":"爱好是练习截拳道"
     *     }
     * }
     * @param result
     */
    public void analyzeJSON2(String result) {

        try{
            // 整个最大的JSON对象
            JSONObject jsonObjectALL = new JSONObject(result);
            /**
             * 为什么要使用jsonObject.optString， 不使用jsonObject.getString
             * 因为jsonObject.optString获取null不会报错
             */
            String student = jsonObjectALL.optString("student", null);

            if (!TextUtils.isEmpty(student)) {
                JSONObject jsonObject = new JSONObject(student);
                String name = jsonObject.optString("name", null);
                int age = jsonObject.optInt("age", 0);
                String hobby = jsonObject.optString("hobby", null);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析JSON对象-嵌套对象
     * {
     *     "student":{
     *         "name":"李四",
     *         "age":99,
     *         "hobby":"爱好是练习截拳道",
     *         "dog":{
     *             "name":"阿黄",
     *             "age":77,
     *             "sex":"母"
     *         }
     *     }
     * }
     * @param result
     */
    public void analyzeJSON3( String result) {

        try{
            // 整个最大的JSON对象
            JSONObject jsonObjectALL = new JSONObject(result);
            /**
             * 为什么要使用jsonObject.optString， 不使用jsonObject.getString
             * 因为jsonObject.optString获取null不会报错
             */
            String student = jsonObjectALL.optString("student", null);

            if (!TextUtils.isEmpty(student)) {
                JSONObject jsonObject = new JSONObject(student);
                String name = jsonObject.optString("name", null);
                int age = jsonObject.optInt("age", 0);
                String hobby = jsonObject.optString("hobby", null);

                // 以下是dog JSON 对象相关的解析

                String dogStr = jsonObject.optString("dog", null);
                // 定义dog的JSON对象
                JSONObject dogJSONObject = new JSONObject(dogStr);
                String dogName = dogJSONObject.optString("name", null);
                int dogAge = dogJSONObject.optInt("age", 0);
                String dogSex = dogJSONObject.optString("sex", null);


            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析JSON数组
     * [
     *     {
     *         "name":"君君",
     *         "age":89,
     *         "sex":"男"
     *     },
     *     {
     *         "name":"小君",
     *         "age":99,
     *         "sex":"女"
     *     },
     *     {
     *         "name":"大君",
     *         "age":88,
     *         "sex":"男"
     *     }
     * ]
     * @param result
     */
    public void analyzeJSONArray1( String result) {

        try{
            // 整个最大的JSON数组
            JSONArray jsonArray = new JSONArray(result);

            for (int i = 0; i < jsonArray.length(); i++) {
                // JSON数组里面的具体-JSON对象
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String name = jsonObject.optString("name", null);
                int age = jsonObject.optInt("age", 0);
                String sex = jsonObject.optString("sex", null);


            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析JSON数组-带Key
     * {
     *     "person":[
     *         {
     *             "name":"君君",
     *             "age":89,
     *             "sex":"男"
     *         },
     *         {
     *             "name":"小君",
     *             "age":99,
     *             "sex":"女"
     *         },
     *         {
     *             "name":"大君",
     *             "age":88,
     *             "sex":"男"
     *         }
     *     ]
     * }
     * @param result
     */
    public void analyzeJSONArray2(String result) {

        try{
            /**
             * JSON数组在牛逼，一旦有了 key person 这样的标记，就必须先是个 JSON对象
             * 最外层的JSON对象，最大的哪个 { ... }
             */
            JSONObject jsonObjectALL = new JSONObject(result);

            // 通过标识(person)，获取JSON数组
            JSONArray jsonArray = jsonObjectALL.getJSONArray("person");

            for (int i = 0; i < jsonArray.length(); i++) {
                // JSON数组里面的具体-JSON对象
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String name = jsonObject.optString("name", null);
                int age = jsonObject.optInt("age", 0);
                String sex = jsonObject.optString("sex", null);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
