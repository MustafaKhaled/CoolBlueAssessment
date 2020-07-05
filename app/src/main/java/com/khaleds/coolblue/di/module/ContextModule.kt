package com.khaleds.coolblue.di.module

import android.content.Context
import com.khaleds.coolblue.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class ContextModule constructor(private val context: Context) {
    @ApplicationScope
    @Provides
    fun provideContext(): Context {
        return context
    }

}