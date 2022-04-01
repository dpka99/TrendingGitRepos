package com.trendinggitrepos.presentation.di

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val context: Context) {

    @Provides
    fun providesAppContext(): Context {
        return context.applicationContext
    }

}