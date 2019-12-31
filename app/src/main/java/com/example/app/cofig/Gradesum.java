package com.example.app.cofig;

import java.util.HashMap;
import java.util.Map;

public class Gradesum {

    private static int f =0;
    private static int s =0;
    private static int a =0;

    private static int fs =0;
    private static int ss =0;
    private static int as =0;

    public static Map grades(int sum){

        f=0;
        Map<String,Integer> map = new HashMap<>();
        for(int i=1;i<=100;i++){

             f = f+(int)(30*(i+Math.pow(i,2)));
            if(sum<f){
                a=i;
                s = f;
                break;
            }
        }
        map.put("n",a);
        map.put("f",s);

        return map;
    }


    public static Map diamonds(int sum){

        fs=0;
        Map<String,Integer> map = new HashMap<>();
        for(int i=1;i<=100;i++){

            fs = fs+(int)(30*(i+Math.pow(i,2)));
            if(sum<fs){
                as=i;
                ss = fs;
                break;
            }
        }
        map.put("n",as);
        map.put("f",ss);

        return map;
    }
}
