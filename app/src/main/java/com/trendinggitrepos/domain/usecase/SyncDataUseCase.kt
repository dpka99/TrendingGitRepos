package com.trendinggitrepos.domain.usecase

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.work.*
import com.trendinggitrepos.workmanager.SyncDataWorker
import java.util.concurrent.TimeUnit

class SyncDataUseCase(
    private val context: Context
) {

    val WORK_TAG = "syncData"

    fun execute() {

        Log.e("MyTag", "syncdata use case called")

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val workRequest =
            PeriodicWorkRequestBuilder<SyncDataWorker>(15, TimeUnit.MINUTES,
                5, TimeUnit.MINUTES)
                .setConstraints(constraints)
                .build()

        val workManager = WorkManager.getInstance(context)
        workManager.enqueueUniquePeriodicWork(
            WORK_TAG,
            ExistingPeriodicWorkPolicy.KEEP,
            workRequest
        )

    }

}