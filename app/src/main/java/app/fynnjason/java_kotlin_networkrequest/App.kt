package app.fynnjason.java_kotlin_networkrequest

import android.app.Application
import android.content.Context

/**
 * Created by FynnJason.
 * Des：App
 */
class App : Application() {

    companion object {
        var Instance: App? = null
    }

    override fun onCreate() {
        super.onCreate()
        Instance = this
    }
}