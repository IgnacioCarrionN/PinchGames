package dev.carrion.pinchgames

import android.app.Application
import dev.carrion.pinchgames.di.appModule
import org.koin.android.ext.android.startKoin
import org.koin.android.logger.AndroidLogger

class BaseApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule), logger = AndroidLogger())
    }
}