package com.example.hz52.app.cofig;

import android.content.Context;
import android.graphics.Color;
import android.text.TextPaint;
import android.util.Log;

import com.example.hz52.app.Entity.Svga;
import com.opensource.svgaplayer.SVGACallback;
import com.opensource.svgaplayer.SVGADrawable;
import com.opensource.svgaplayer.SVGADynamicEntity;
import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.SVGAParser;
import com.opensource.svgaplayer.SVGAVideoEntity;

import java.util.ArrayList;

public class SvgaUtils {
    private static ArrayList<Svga> stringList = new ArrayList<>();
    private static SVGAParser parser;

    /**
     * 初始化数据
     */
    public static void initAnimator(Context context,SVGAImageView svgaImage) {
        parser = new SVGAParser(context);
        stringList = new ArrayList<>();
        //监听大动画的控件周期
        svgaImage.setCallback(new SVGACallback() {
            @Override
            public void onPause() {
                Log.e("setCallback", "onPause");
            }

            @Override
            public void onFinished() {
                //当动画结束，如果数组容器大于0，则移除容器第一位的数据，轮询播放动画。
                if (stringList != null && stringList.size() > 0) {
                    stringList.remove(0);
                    //如果移除之后的容器大于0，则开始展示新一个的大动画
                    if (stringList != null && stringList.size() > 0) {
                        try {
                            parseSVGA(svgaImage);//解析加载动画
                        } catch (Exception ignored) {

                        }
                    } else {
                        stopSVGA(svgaImage);
                    }
                } else {
                    stopSVGA(svgaImage);
                }
            }

            @Override
            public void onRepeat() {
                Log.e("setCallback", "onRepeat=" + stringList.size());
                stopSVGA(svgaImage);
            }

            @Override
            public void onStep(int i, double v) {

            }
        });
    }

    /**
     * 显示动画
     */
    public static void startAnimator(Svga svga,SVGAImageView svgaImage) {
        stringList.add(stringList.size(), svga);
        System.out.println("增加"+stringList.size());
        //如果礼物容器列表的数量是1，则解析动画，如果数量不是1，则此处不解析动画，在上一个礼物解析完成之后加载再动画
        if (stringList.size() == 1) {
            parseSVGA(svgaImage);
        }
    }

    /**
     * 停止动画
     */
    private static void stopSVGA(SVGAImageView svgaImage) {
        if (svgaImage.isAnimating() && stringList.size() == 0) {
            svgaImage.stopAnimation();
        }
    }

    /**
     * 解析加载动画
     */
    private static void parseSVGA(SVGAImageView svgaImage) {
        if (stringList.size() > 0) {
            try {
                parser.decodeFromAssets(stringList.get(0).getName(), new SVGAParser.ParseCompletion() {
                    @Override
                    public void onComplete(SVGAVideoEntity svgaVideoEntity) {
                        //解析动画成功，到这里才真正的显示动画
                        SVGADynamicEntity dynamicEntity = new SVGADynamicEntity();
                        if (!stringList.get(0).getIma().equals("")) {
                            dynamicEntity.setDynamicImage(stringList.get(0).getIma(), stringList.get(0).getImaforkey()); // Here is the KEY implementation.
                        }else if(!stringList.get(0).getTxt().equals("")){
                            TextPaint textPaint = new TextPaint();
                            textPaint.setColor(Color.WHITE);
                            textPaint.setTextSize(28);
                            dynamicEntity.setDynamicText(stringList.get(0).getTxt(), textPaint, stringList.get(0).getTxtforkey());
                        }
                        SVGADrawable drawable = new SVGADrawable(svgaVideoEntity);
                        svgaImage.setImageDrawable(drawable);
                        svgaImage.startAnimation();
                    }

                    @Override
                    public void onError() {
                        //如果动画数组列表大于0,移除第一位的动画,继续循环解析
                        if (stringList.size() > 0) {
                            stringList.remove(0);
                            parseSVGA(svgaImage);
                        } else {
                            stopSVGA(svgaImage);
                        }
                    }
                });
            } catch (Exception ignored) {
            }
        } else {
            stopSVGA(svgaImage);
        }
    }
}
