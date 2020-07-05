package com.khaleds.coolblue.di.component
import com.khaleds.coolblue.data.remote.endpoints.ApiServices
import android.content.Context
import com.khaleds.coolblue.di.module.ContextModule
import com.khaleds.coolblue.di.module.RetrofitModule
import com.khaleds.coolblue.di.scope.ApplicationScope

import dagger.Component
import retrofit2.Retrofit
@ApplicationScope
@Component(modules = [ContextModule::class, RetrofitModule::class])
interface ApplicationComponent {
    fun exposeRetrofit(): Retrofit
    fun exposeContext(): Context
    fun exposeApiServices(): ApiServices
}