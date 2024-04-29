package com.perfomax.dataviewer.receivers

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import com.perfomax.dataviewer.domain.repository.AlarmScheduler

private const val ALARM_REQUEST_CODE = 1002

@SuppressLint("UnspecifiedImmutableFlag")
class AlarmSchedulerImpl(private val context: Context): AlarmScheduler {

    private val alarmManager: AlarmManager by lazy {
        context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    }

    private val  alarmIntent: PendingIntent

        get() = Intent(context, FeedUpdateScheduleReceiver::class.java).let { intent ->
            var pendingIntent: PendingIntent? = null
            pendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                PendingIntent.getBroadcast(
                    context,
                    ALARM_REQUEST_CODE,
                    intent,
                    PendingIntent.FLAG_MUTABLE
                )
            } else {
                PendingIntent.getBroadcast(
                    context,
                    ALARM_REQUEST_CODE,
                    intent,
                    PendingIntent.FLAG_ONE_SHOT
                )
            }
            pendingIntent
        }
    override fun schedulerFeedUpdate(intervalTime: Long) {
        val scheduleTimer = System.currentTimeMillis() + intervalTime
        alarmManager.set(
            AlarmManager.RTC_WAKEUP,
            scheduleTimer,
            alarmIntent
        )
    }

    fun cancelFeedUpdate() {
        if (Build.VERSION.SDK_INT >= 34) {
            alarmManager.cancelAll()
        } else {
            alarmManager.cancel(alarmIntent)
        }
    }
}