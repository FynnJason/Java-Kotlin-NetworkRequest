package app.fynnjason.java_kotlin_networkrequest.java.api;

import java.util.List;

import app.fynnjason.java_kotlin_networkrequest.java.base.BaseEnity;
import app.fynnjason.java_kotlin_networkrequest.java.bean.GankBean;
import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by FynnJason.
 * Des：Api
 */

public interface ApiService {
    @GET("all/20/1")
    Observable<BaseEnity<List<GankBean>>> allGank();
}
