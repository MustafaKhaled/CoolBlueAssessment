package com.khaleds.coolblue.di.module

import android.content.Context
import com.khaleds.coolblue.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import java.util.concurrent.TimeUnit

@Module
class OkHttpModule {
    @ApplicationScope
    @Provides
    fun getOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor?
    ): OkHttpClient {
        return OkHttpClient()
            .newBuilder()
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor!!)
            .build()
    }


    @ApplicationScope
    @Provides
    fun providesFile(context: Context): File {
        val file = File(context.cacheDir, "HttpCache")
        file.mkdir()
        return file
    }
    @ApplicationScope
    @Provides
    fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }
}