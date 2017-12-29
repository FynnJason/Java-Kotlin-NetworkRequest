package app.fynnjason.java_kotlin_networkrequest.java.base;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by FynnJason.
 * Des：
 */

public abstract class BaseObserver<T> implements Observer<BaseEnity<T>> {

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(BaseEnity<T> tBaseEnity) {
        T t = tBaseEnity.getResults();
        onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        onFail(e);
    }

    @Override
    public void onComplete() {

    }

    public abstract void onSuccess(T t);

    public abstract void onFail(Throwable e);


}
