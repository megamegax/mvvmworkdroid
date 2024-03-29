package hu.epam.worktime.mvvmworkdroid.modules.services.worker

import hu.epam.worktime.mvvmworkdroid.modules.services.models.WorkDay
import hu.epam.worktime.mvvmworkdroid.modules.services.models.WorkTime
import hu.epam.worktime.mvvmworkdroid.modules.services.models.WorkingStatistics
import org.threeten.bp.DayOfWeek
import org.threeten.bp.Duration
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalTime

/**
 * Created by Mihaly_Hunyady on 2016. 11. 11..
 */
class CalculatorService(var workDays: List<WorkDay> = emptyList()) {
    constructor() : this(emptyList())

    fun calculateStuffs(workTimes: List<WorkTime>): WorkingStatistics? {
        workTimes.forEach {
            it.nettoWork = calculateWorkDay(it)
        }

        val dailyWorkTime: LocalTime = workTimes.find { it.date == LocalDate.now() }?.nettoWork ?: LocalTime.MIN

        val monthlyWorkTime: LocalTime = calculateMontlyWorkTime(workTimes)
        val daysToWork: Int = calculateDaysToWork()
        val workTimeLeft: String = calculateWorkTimeLeft(daysToWork)
        val avgWorkTime: LocalTime = calculateAverageWorktime(workTimes)
        return WorkingStatistics(dailyWorkTime, monthlyWorkTime, daysToWork, workTimeLeft, avgWorkTime, workTimes,0)
    }

    private fun calculateWorkTimeLeft(daysToWork: Int): String {
        return "${daysToWork * 8}:00:00"
    }


    //FIXME ki kell javítani, rossz
    private fun calculateDaysToWork(): Int {

        val month = workDays.find { it.year == LocalDate.now().year && it.month == LocalDate.now().monthValue }
        var i = 0
        var date = LocalDate.of(LocalDate.now().year, LocalDate.now().month, 1)
        listOf(1..LocalDate.now().dayOfMonth).forEach {
            if (date.dayOfWeek.value <= 5 && date.dayOfWeek.value >= 1) {
                i += 1
            }
            date = date.plusDays(1)

        }
        return month!!.workingDays - i
    }

    private fun calculateAverageWorktime(workTimes: List<WorkTime>): LocalTime {
        var avg = 0
        val thisMonth = workTimes.filter { isThisMonth(it) }
        thisMonth.forEach {
            avg += it.nettoWork.hour * 60 * 60 + it.nettoWork.minute * 60 + it.nettoWork.second
        }

        val size = if (thisMonth.size > 0) thisMonth.size else 1
        return LocalTime.of(0, 0, 0).plusSeconds((avg / size).toLong())
    }

    private fun calculateMontlyWorkTime(workTimes: List<WorkTime>): LocalTime {
        var month: LocalTime = LocalTime.of(0, 0, 0)
        workTimes.filter { isThisMonth(it) }.forEach {
            month = month.plusHours(it.nettoWork.hour.toLong())
            month = month.plusMinutes(it.nettoWork.minute.toLong())
            month = month.plusSeconds(it.nettoWork.second.toLong())
        }
        return month
    }

    private fun calculateWorkDay(workTime: WorkTime?): LocalTime {
        if (workTime == null) {
            return LocalTime.of(0, 0, 0)
        }
        var tempTime = Duration.between(workTime.arrive, if (workTime.leave.equals(LocalTime.of(0, 0, 0))) LocalTime.now() else workTime.leave)
        var dinnerTime = Duration.between(workTime.dinner.first, workTime.dinner.second)
        if (dinnerTime.toMinutes() < 30) {
            dinnerTime = Duration.ofMinutes(30)
        }

        tempTime = tempTime.minus(dinnerTime)
        for (out in workTime.offtime) {
            tempTime = tempTime.minus(Duration.between(out.first, out.second))
        }
        return LocalTime.of(0, 0, 0).plusSeconds(Math.abs(tempTime.seconds))
    }

    private fun isToday(it: WorkTime) = it.date.isEqual(LocalDate.now())

    private fun isThisMonth(it: WorkTime) = it.date.isAfter(LocalDate.now().withDayOfMonth(1)) || it.date.isEqual(LocalDate.now().withDayOfMonth(1))

    private fun isThisWeek(it: WorkTime) = it.date.isAfter(LocalDate.now().with(DayOfWeek.MONDAY)) || it.date.isEqual(LocalDate.now().with(DayOfWeek.MONDAY))

}