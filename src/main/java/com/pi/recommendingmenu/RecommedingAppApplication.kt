package com.pi.recommendingmenu

import android.app.Application
import com.pi.recommendingmenu.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class RecommedingAppApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@RecommedingAppApplication)
            modules(appModule)
        }
    }
}