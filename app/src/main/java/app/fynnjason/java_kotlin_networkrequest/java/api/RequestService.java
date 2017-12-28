package app.fynnjason.java_kotlin_networkrequest.java.api;

import android.util.Log;
import android.widget.Toast;

import app.fynnjason.java_kotlin_networkrequest.java.base.BaseEnity;
import app.fynnjason.java_kotlin_networkrequest.java.bean.GankBean;
import io.reactivex.Observable;

/**
 * Created by FynnJason.
 * Des：request
 */

public class RequestService implements ApiService {
    private RequestService() {

    }

    private static class Holder {
        private static RequestService Instance = new RequestService();
    }

    public static RequestService getInstance() {
        return Holder.Instance;
    }


    @Override
    public Observable<BaseEnity<GankBean>> allGank() {
        return null;
    }
}
