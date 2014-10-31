package com.tush.game.android.tictac.util;

import android.util.Log;

import com.tush.game.android.tictac.BuildConfig;

public class LogUtil {

    public static void LogDebug(Object msg) {
        if (BuildConfig.DEBUG&&msg!=null) {
            Log.d("TicTac",msg.toString());
        }
    }

    public static void LogError(Object msg) {
        if (BuildConfig.DEBUG&&msg!=null) {
            Log.e("TicTac",msg.toString());
        }
    }
}