package app.fynnjason.java_kotlin_networkrequest.java.base;

import android.support.v7.app.AppCompatActivity;

import app.fynnjason.java_kotlin_networkrequest.App;
import app.fynnjason.java_kotlin_networkrequest.utils.ToastUtils;
import app.fynnjason.java_kotlin_networkrequest.utils.Utils;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by FynnJason.
 * Des：activity基类
 */

public class BaseActivity extends AppCompatActivity {

    public <T> ObservableTransformer<T, T> compose() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> observable) {
                return observable
                        .subscribeOn(Schedulers.io())
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(Disposable disposable) throws Exception {
                                if (!Utils.isNetworkAvailable(App.Companion.getInstance())) {
                                    ToastUtils.show("网络连接断开！");
                                }
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Disposables.disposed();
    }
}
