package com.perfomax.dataviewer.domain.usecases.scheduler

import com.perfomax.dataviewer.domain.repository.AlarmScheduler
import com.perfomax.dataviewer.domain.usecases.UseCaseWithParams
import javax.inject.Inject

class SetScheduleUseCase @Inject constructor(
    private val alarmScheduler: AlarmScheduler
): UseCaseWithParams<Unit, Long> {
        override suspend fun execute(intervalTime: Long) {
            alarmScheduler.schedulerFeedUpdate(intervalTime = intervalTime)
    }
}