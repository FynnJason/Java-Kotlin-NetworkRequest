package app.fynnjason.java_kotlin_networkrequest.utils;

import android.widget.Toast;

import app.fynnjason.java_kotlin_networkrequest.App;


/**
 * Created by FynnJason
 * Des：Toast tools
 */

public class ToastUtils {
    private static Toast sToast = null;

    public static void show(String msg) {
        if (sToast != null)
            sToast.cancel();
        sToast = Toast.makeText(App.Companion.getInstance(),msg,Toast.LENGTH_SHORT);
        sToast.show();
    }
}
