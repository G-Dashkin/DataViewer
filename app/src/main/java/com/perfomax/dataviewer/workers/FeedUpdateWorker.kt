package com.perfomax.dataviewer.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.perfomax.dataviewer.receivers.AlarmSchedulerImpl

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