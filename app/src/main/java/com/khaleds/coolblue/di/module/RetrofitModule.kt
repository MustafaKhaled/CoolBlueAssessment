package com.khaleds.coolblue.di.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.khaleds.coolblue.BuildConfig
import com.khaleds.coolblue.data.remote.endpoints.ApiServices
import com.khaleds.coolblue.di.module.OkHttpModule
import com.khaleds.coolblue.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module(includes = [OkHttpModule::class])
class RetrofitModule {
    @ApplicationScope
    @Provides
    fun retrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @ApplicationScope
    @Provides
    fun providesGson(): Gson {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }

    @ApplicationScope
    @Provides
    fun providesGsonConverterFactory(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }
    @ApplicationScope
    @Provides
    fun providesApiService(retrofit: Retrofit): ApiServices {
        return retrofit.create(ApiServices::class.java)
    }

}