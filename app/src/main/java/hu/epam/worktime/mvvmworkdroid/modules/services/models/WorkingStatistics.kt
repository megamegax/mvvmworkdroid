package hu.hanprog.worktime.service.model

import hu.hanprog.worktime.service.work.model.WorkTime
import org.threeten.bp.LocalTime

data class WorkingStatistics(val dailyWorkTime: LocalTime,
                             val montlyWorkTime: LocalTime,
                             val daysToWork: Int,
                             val workTimeLeft: String,
                             val avgWorkTime: LocalTime,
                             val workTimes: List<WorkTime>)