package com.nicolas.minerelay

import android.app.Application
import com.nicolas.minerelay.data.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MineRelayApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MineRelayApplication)
            modules(appModule)
        }
    }
}