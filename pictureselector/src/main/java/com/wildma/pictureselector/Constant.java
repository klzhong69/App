package com.wildma.pictureselector;

import java.io.File;

/**
 * Author       wildma
 * Github       https://github.com/wildma
 * Date         2018/6/10
 * Desc	        ${常量}
 */
public class Constant {
    public static final String APP_NAME   = "PictureSelector";//app名称
    public static final String BASE_DIR   =  APP_NAME + File.separator;//PictureSelector/
    public static final String DIR_ROOT   = FileUtils.getRootPath() + File.separator + Constant.BASE_DIR;//文件夹根目录 /storage/emulated/0/PictureSelector/

    public static final int CANCEL = 0;//取消
    public static final int CAMERA = 1;//拍照
    public static final int ALBUM  = 2;//相册


    // 开黑聊天室
    public static final int ChatRoomGamingStandard = 0x01;
    // 娱乐聊天室
    public static final int ChatRoomEntertainmentStandard = 0x02;
    // K 歌房
    public static final int ChatRoomEntertainmentHighQuality = 0x03;
    // FM 超高音质
    public static final int ChatRoomGamingHighQuality = 0x04;

    // 开黑聊天室
    public static final String ChatRoomGamingStandardName = "ChatRoomGamingStandard";
    // 娱乐聊天室
    public static final String ChatRoomEntertainmentStandardName = "ChatRoomEntertainmentStandard";
    // K 歌房
    public static final String ChatRoomEntertainmentHighQualityName = "ChatRoomEntertainmentHighQuality";
    // FM 超高音质
    public static final String ChatRoomGamingHighQualityName = "ChatRoomGamingHighQuality";

    public static final String ACTION_KEY_CROLE = "C_Role";
    public static final String ACTION_KEY_ROOM_MODE = "ecHANELMODE";
    public static final String ACTION_KEY_ROOM_ID = "ecHANEL";
    public static final String ACTION_KEY_TITLE_NAME = "eTITLE";

    public static String[] SOUNDARRAY = new String[] {
            "大叔","正太","猪八戒","空灵",
            "浩克","萝莉","电台KTV","演唱会",
            "录音棚", "流行", "R&B", "嘻哈", "摇滚"
    };

}

