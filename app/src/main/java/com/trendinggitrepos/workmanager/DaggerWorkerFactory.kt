package com.trendinggitrepos.workmanager

import android.content.Context
import android.util.Log
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.trendinggitrepos.domain.repository.GithubReposRepository
import javax.inject.Inject


class DaggerWorkerFactory @Inject constructor(
   private val syncDataWorkerFactory : SyncDataWorker.Factory
) : WorkerFactory() {

    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {

      //  Log.e("MyTAG","daggerworkerfactory - called ")
        return when (workerClassName) {

            SyncDataWorker::class.java.name ->
                syncDataWorkerFactory.create(appContext, workerParameters)
            else -> null
        }

    }
}