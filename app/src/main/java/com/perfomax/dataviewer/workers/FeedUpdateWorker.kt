package com.perfomax.dataviewer.workers

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.perfomax.dataviewer.data.storage.api.SettingsStorage
import com.perfomax.dataviewer.domain.usecases.settings.GetUpdatePeriodUseCase
import com.perfomax.dataviewer.receivers.AlarmSchedulerImpl
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

private const val WORKER_TAG = "FEED.WORKER"
private const val ONGOING_NOTIFICATION_ID = 5

class FeedUpdateWorker (
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    private val alarmScheduler = AlarmSchedulerImpl(context)

    override suspend fun doWork(): Result {
        setupNextUpdate(5000L)
        return Result.success()
    }

    private fun setupNextUpdate(scheduledTimer: Long) {
        alarmScheduler.schedulerFeedUpdate(scheduledTimer)
    }

    companion object {

        private val workerRequest : OneTimeWorkRequest
            get() {
                return OneTimeWorkRequestBuilder<FeedUpdateWorker>().build()
            }

        fun enqueue(workManager: WorkManager) {
            workManager.enqueue(workerRequest)
        }
    }

}