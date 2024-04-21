package com.perfomax.dataviewer.workers

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.perfomax.dataviewer.receivers.AlarmSchedulerImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private const val WORKER_TAG = "FEED.WORKER"
private const val ONGOING_NOTIFICATION_ID = 5

class FeedUpdateWorker(context: Context, workerParams: WorkerParameters)
    : CoroutineWorker(context, workerParams) {

    // Здесь также создаем экземпляр шедулера для перезапуска в конце работы воркера
    private val alarmScheduler = AlarmSchedulerImpl(context)
    override suspend fun doWork(): Result {
//        return withContext(Dispatchers.IO) {
//            try {
//                Log.e("MyLog", "do work")
////                inputData.getString(INPUT_DATA_KEY)?.let { jokeText ->
////                    repository.saveToFavorites(jokeText)
////                }
//                Result.success()
//            } catch (ex: Exception) {
//                Log.e("MyLog", "Worker exception: ${ex.localizedMessage}")
//                Result.failure()
//            }
//        }

//        val pendingIntent: PendingIntent = Intent(
//            context,
//            Example4WorkerReceiverActivity::class.java
//        ).let { notificationIntent ->
//            PendingIntent.getActivity(
//                context, 0, notificationIntent,
//                PendingIntent.FLAG_IMMUTABLE
//            )
//        }

        alarmScheduler.schedulerFeedUpdate(5000L)
        return Result.success()
    }

    companion object {
        private const val INPUT_DATA_KEY = "feedForSave"

//        fun enqueueWith(workManager: WorkManager, jokeText: String) {
//        fun enqueueWith(workManager: WorkManager) {
//            val jokeValueData: Data = Data.Builder()
////                .putString(INPUT_DATA_KEY, jokeText)
//                .build()
//            val testData = "test"
//            Log.d("MyLog", "++++++++++++")
//            workManager.enqueue(getOneTimeRequest(inputData = jokeValueData))
//        }
//
//        private fun getOneTimeRequest(inputData: Data): OneTimeWorkRequest {
//            Log.d("MyLog", "getOneTimeRequest()")
//            return OneTimeWorkRequestBuilder<FeedUpdateWorker>()
//                .setInputData(inputData)
//                .addTag(WORKER_TAG)
//                .build()
//        }






        private val workerRequest : OneTimeWorkRequest
            get() {
                return OneTimeWorkRequestBuilder<FeedUpdateWorker>().build()
            }

        fun enqueue(workManager: WorkManager) {
            workManager.enqueue(workerRequest)
        }
    }

}