package com.perfomax.dataviewer.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.work.WorkManager
import com.perfomax.dataviewer.workers.FeedUpdateWorker

class FeedUpdateScheduleReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let {
            try {
                val workManager: WorkManager = WorkManager.getInstance(context)
                FeedUpdateWorker.enqueue(workManager)
            } catch (ex: Exception) {
                Log.e(
                    "JokeUpdateScheduleReceiver",
                    "JokeUpdateScheduleReceiver onReceive exception: ${ex.localizedMessage}"
                )
            }
        }
    }
}