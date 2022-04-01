package com.trendinggitrepos.presentation

import android.app.Application
import androidx.work.Configuration
import androidx.work.WorkManager
import com.trendinggitrepos.workmanager.DaggerWorkerFactory
import com.trendinggitrepos.presentation.di.AppComponent
import com.trendinggitrepos.presentation.di.AppModule
import com.trendinggitrepos.presentation.di.DaggerAppComponent

import javax.inject.Inject

class App : Application(), Configuration.Provider {

    lateinit var appComponent: AppComponent

    @Inject
    lateinit var workerFactory: DaggerWorkerFactory


    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(applicationContext)).build()
        appComponent.inject(this)

        WorkManager.initialize(this, workManagerConfiguration)
    }

    override fun getWorkManagerConfiguration(): Configuration {
    return Configuration.Builder()
        .setWorkerFactory(workerFactory)
    .setMinimumLoggingLevel(android.util.Log.DEBUG)
    .build()
}


}