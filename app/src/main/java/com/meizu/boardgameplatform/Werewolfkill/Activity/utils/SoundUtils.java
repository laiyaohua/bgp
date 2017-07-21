package com.meizu.boardgameplatform.Werewolfkill.Activity.utils;

import android.media.SoundPool;

import java.util.HashMap;

/**
 * Created by Administrator on 2017/5/7 0007.
 */
public class SoundUtils {
    static volatile SoundPool instance;
    public static HashMap<Integer, Integer> musicId = new HashMap<Integer, Integer>();
    public static SoundPool mSoundPool = new SoundPool(12, 0, 5);
    public static void playSound(SoundPool soundPool, int id) {
        soundPool.play(id, 1, 1, 0, 0, 1);
    }
    public static SoundPool getInstance(){
        if (instance == null) {
            synchronized (SoundPool.class) {
                if (instance == null) {
                    instance = new SoundPool(12, 0, 5);
                }
            }
        }
        return instance;
    };

}
