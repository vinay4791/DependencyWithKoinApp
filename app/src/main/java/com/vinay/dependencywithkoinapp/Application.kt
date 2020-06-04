package com.vinay.dependencywithkoinapp

import android.app.Application
import com.vinay.dependencywithkoinapp.dependency.apiModule
import com.vinay.dependencywithkoinapp.dependency.repositoryModule
import com.vinay.dependencywithkoinapp.dependency.retrofitModule
import com.vinay.dependencywithkoinapp.dependency.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@Application)
            modules(listOf(repositoryModule, viewModelModule, retrofitModule, apiModule))
        }
    }
}