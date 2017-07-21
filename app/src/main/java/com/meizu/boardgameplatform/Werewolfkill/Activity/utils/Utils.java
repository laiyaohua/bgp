package com.meizu.boardgameplatform.Werewolfkill.Activity.utils;

import android.content.Context;

import com.meizu.boardgameplatform.Werewolfkill.Activity.model.HeroInfo;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/30 0030.
 */
public class Utils {
    public final static String[] roleName = new String[]{"狼人","村民","预言家","女巫","猎人","村民","村民"};
    public final static int [] rolePositive = new int[]{-1,1,1,1,1,1,1,1};
    public final static int MAX_ROLE_TYPE = 5;
    public final  static int MIN_PLAYER_NUM = 8;

    public static int mPlayerNum = MIN_PLAYER_NUM;
    public static ArrayList<HeroInfo> mHeroInfo = new ArrayList<HeroInfo>();  //保存每个玩家选择的身份信息
    public static final int TIME_DELAY = 5 * 1000;  //5秒延迟
    public static  int TIME_COUNT = 15 * 1000;    //15秒倒计时

    /**
     * 对象不为空
     */
    public static boolean notNull(Object obj) {
        if (null != obj && !"".equals(obj)) {
            return true;
        }
        return false;


    }

    public static boolean isNull(Object obj) {
        if (null == obj || "".equals(obj)) {
            return true;
        }
        return false;
    }

    public static int dp2px(Context context, int dp) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public static int px2dp(Context context, int px) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }
}
