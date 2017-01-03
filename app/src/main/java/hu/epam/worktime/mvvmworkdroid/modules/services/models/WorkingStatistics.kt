package hu.epam.worktime.mvvmworkdroid.modules.services.models

import org.threeten.bp.LocalTime

data class WorkingStatistics(val dailyWorkTime: LocalTime,
                             val montlyWorkTime: LocalTime,
                             val daysToWork: Int,
                             val workTimeLeft: String,
                             val avgWorkTime: LocalTime,
                             val workTimes: List<WorkTime>,
                             var id: Int?)