package com.trendinggitrepos.workmanager

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.trendinggitrepos.domain.repository.GithubReposRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import java.text.SimpleDateFormat
import java.util.*

class SyncDataWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted private val workerParams: WorkerParameters,
    private val githubReposRepository: GithubReposRepository
) : CoroutineWorker(context, workerParams) {

    private val TAG = "SyncDataWorker"

    override suspend fun doWork(): Result {

        return try {

            githubReposRepository.syncData()

            val time = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
            val currentDate = time.format(Date())

            Log.i(TAG, "Completed $currentDate")
            Result.success()
        } catch (throwable: Throwable) {
            Log.e(TAG, "Error syncing data == > ${throwable.message}")
            Result.failure()
        }
    }


    @AssistedFactory
    interface Factory {
        fun create(appContext : Context,parameters: WorkerParameters) : SyncDataWorker
    }
}
