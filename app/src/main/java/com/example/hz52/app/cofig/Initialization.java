package com.example.hz52.app.cofig;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.hz52.app.gen.DaoMaster;
import com.example.hz52.app.gen.DaoSession;

public class Initialization {

    private static DaoSession daoSessionuser;
    private static DaoSession daoSessionaccount;
    private static DaoSession daoSessioncaht;
    private static DaoSession daoSessionconver;
    private static DaoSession daoSessionmusic;
    private static DaoSession daoSessiongift;

    /**
     * 配置数据库
     */
    public static void setupDatabaseUser(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "User.db", null);
        //获取可写数据库
        SQLiteDatabase db = helper.getWritableDatabase();
        //获取数据库对象
        DaoMaster daoMaster = new DaoMaster(db);
        //获取Dao对象管理者
        daoSessionuser = daoMaster.newSession();
    }

    public static DaoSession getDaoInstantUser() {
        return daoSessionuser;
    }


    /**
     * 配置数据库
     */
    public static void setupDatabaseAccount(Context context) {
        DaoMaster.DevOpenHelper helper1 = new DaoMaster.DevOpenHelper(context, "Account.db", null);
        //获取可写数据库
        SQLiteDatabase db1 = helper1.getWritableDatabase();
        //获取数据库对象
        DaoMaster daoMaster1 = new DaoMaster(db1);
        //获取Dao对象管理者
        daoSessionaccount = daoMaster1.newSession();
    }

    public static DaoSession getDaoInstantAccount() {
        return daoSessionaccount;
    }


    /**
     * 配置数据库
     */
    public static void setupDatabaseChat(Context context) {
        DaoMaster.DevOpenHelper helper2 = new DaoMaster.DevOpenHelper(context, "Chat.db", null);
        //获取可写数据库
        SQLiteDatabase db2 = helper2.getWritableDatabase();
        //获取数据库对象
        DaoMaster daoMaster2 = new DaoMaster(db2);
        //获取Dao对象管理者
        daoSessioncaht = daoMaster2.newSession();
    }

    public static DaoSession getDaoInstantChat() {
        return daoSessioncaht;
    }


    /**
     * 配置数据库
     */
    public static void setupDatabaseConver(Context context) {
        DaoMaster.DevOpenHelper helper3 = new DaoMaster.DevOpenHelper(context, "Conver.db", null);
        //获取可写数据库
        SQLiteDatabase db3 = helper3.getWritableDatabase();
        //获取数据库对象
        DaoMaster daoMaster3 = new DaoMaster(db3);
        //获取Dao对象管理者
        daoSessionconver = daoMaster3.newSession();
    }

    public static DaoSession getDaoInstantConver() {
        return daoSessionconver;
    }


    /**
     * 配置数据库
     */
    public static void setupDatabaseMusic(Context context) {
        DaoMaster.DevOpenHelper helper4 = new DaoMaster.DevOpenHelper(context, "Music.db", null);
        //获取可写数据库
        SQLiteDatabase db4 = helper4.getWritableDatabase();
        //获取数据库对象
        DaoMaster daoMaster4 = new DaoMaster(db4);
        //获取Dao对象管理者
        daoSessionmusic = daoMaster4.newSession();
    }

    public static DaoSession getDaoInstantMusic() {
        return daoSessionmusic;
    }

    /**
     * 配置数据库
     */
    public static void setupDatabaseGift(Context context) {
        DaoMaster.DevOpenHelper helper5 = new DaoMaster.DevOpenHelper(context, "Gift.db", null);
        //获取可写数据库
        SQLiteDatabase db5 = helper5.getWritableDatabase();
        //获取数据库对象
        DaoMaster daoMaster5 = new DaoMaster(db5);
        //获取Dao对象管理者
        daoSessiongift = daoMaster5.newSession();
    }

    public static DaoSession getDaoInstantGift() {
        return daoSessiongift;
    }
}
