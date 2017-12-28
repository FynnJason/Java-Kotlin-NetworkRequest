package app.fynnjason.java_kotlin_networkrequest.java;

/**
 * Created by FynnJason.
 * Des：数据模型基类
 */

public class BaseEnity<T> {
    private boolean error;
    private T results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }
}
