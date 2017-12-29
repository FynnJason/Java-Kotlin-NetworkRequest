package app.fynnjason.java_kotlin_networkrequest.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import app.fynnjason.java_kotlin_networkrequest.App;

/**
 * Created by FynnJason
 * Des：other tools
 */

public class Utils {
    /**
     * 判断网络是否可用
     */
    public static boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) App.Companion.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isAvailable();
    }
}