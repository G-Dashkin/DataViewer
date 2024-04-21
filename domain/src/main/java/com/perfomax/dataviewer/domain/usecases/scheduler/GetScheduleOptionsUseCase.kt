package com.perfomax.dataviewer.domain.usecases.scheduler

//class GetScheduleOptionsUseCase {
//}

//private val defaultSchedule = Schedule.FIFTEEN_SECONDS
//private val defaultSchedule = 15
//
//class GetScheduleOptionsUseCase(private val repository: PreferencesRepository) {
//    suspend fun execute(): ScheduleOptions {
//        val time = repository.getScheduleTime()
//        val category = repository.getSelectedCategory()
//
//        val schedule = time?.let { Schedule.from(it) } ?: defaultSchedule
//
//        return ScheduleOptions(schedule = schedule, topic = category)
//    }
//}