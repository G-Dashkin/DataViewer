package com.perfomax.dataviewer.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

private const val RECEIVER_FEED_EXTRAS = "receiver_feed_extras"

class FeedSetupReceiver: BroadcastReceiver() {

    // Этот ресивер именно для запуска фоновой работы а не для обновления.
    // т.е. если передано занчение true, то он запускает шедулер
    // Инстанция данного шедулера ни где не вызывается, он работает за счет регистрации
    // в манифесте
    override fun onReceive(context: Context?, intent: Intent?) {
        context ?: return
        val isEnable = intent?.getBooleanExtra(RECEIVER_FEED_EXTRAS, false) ?: return
        Log.d("MyLog", "Value in FeedSetupReceiver: $isEnable")

        // Создаем экземпляр нашего шедулена (AlarmManager) для планирования события.
        val alarmScheduler = AlarmSchedulerImpl(context)

        if (isEnable) {
            // Запускаем AlarmManeger через 5 секунд и с периодичностью в 5 секунд
            // т.е. если мы типа включили фоновую работу, то шедулер будет запускаться кажде 5 секунд
            alarmScheduler.schedulerFeedUpdate(5000L)
        } else {
            // И здесь мы отменяем
            alarmScheduler.cancelFeedUpdate()
        }
    }
}