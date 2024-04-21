package com.perfomax.dataviewer.domain.repository

interface AlarmScheduler {
    fun schedulerFeedUpdate(intervalTime: Long)
}