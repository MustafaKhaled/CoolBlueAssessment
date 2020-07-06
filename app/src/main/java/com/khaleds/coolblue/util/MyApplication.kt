package com.khaleds.coolblue.util

import android.app.Application
import android.content.Context
import com.khaleds.coolblue.di.component.ApplicationComponent
import com.khaleds.coolblue.di.component.DaggerApplicationComponent
import com.khaleds.coolblue.di.module.ContextModule
import com.khaleds.coolblue.di.module.OkHttpModule
import com.khaleds.coolblue.di.module.RetrofitModule

class MyApplication : Application() {
    companion object {
        lateinit var applicationComponent: ApplicationComponent
        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        applicationComponent =
            DaggerApplicationComponent.builder().contextModule(ContextModule(applicationContext))
                .okHttpModule(
                    OkHttpModule()
                ).retrofitModule(RetrofitModule()).build()
    }

}