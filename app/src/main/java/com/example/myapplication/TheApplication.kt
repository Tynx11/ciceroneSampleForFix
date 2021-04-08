package com.example.myapplication

import android.app.Application
import com.example.myapplication.di.AppComponent
import com.example.myapplication.di.DaggerAppComponent


class TheApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initComponent()
    }

    private fun initComponent() {
        appComponent = DaggerAppComponent
            .builder()
            .build()
    }

    companion object {
        var appComponent: AppComponent? = null
    }
}