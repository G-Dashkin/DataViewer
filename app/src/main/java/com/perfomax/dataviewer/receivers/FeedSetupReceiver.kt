package com.perfomax.dataviewer.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

private const val RECEIVER_FEED_EXTRAS = "receiver_feed_extras"
private const val RECEIVER_FEED_TIME_EXTRAS = "receiver_feed_time_extras"

class FeedSetupReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        context ?: return

        val alarmScheduler = AlarmSchedulerImpl(context)

        val isEnable = intent?.getBooleanExtra(RECEIVER_FEED_EXTRAS, false) ?: return
        val updateTime = intent.getLongExtra(RECEIVER_FEED_TIME_EXTRAS, 5000L)

        if (isEnable) {
            alarmScheduler.schedulerFeedUpdate(updateTime)
        } else {
            alarmScheduler.cancelFeedUpdate()
        }
    }
}