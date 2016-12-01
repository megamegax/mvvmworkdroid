package hu.hanprog.worktime.service.work.model

import org.threeten.bp.LocalDate
import org.threeten.bp.LocalTime


data class WorkTime(
        val date: LocalDate = LocalDate.MIN,
        val arrive: LocalTime = LocalTime.MIN,
        val leave: LocalTime = LocalTime.MIN,
        val dinner: Pair<LocalTime, LocalTime> = Pair(LocalTime.now(), LocalTime.now()),
        val offtime: List<Pair<LocalTime, LocalTime>> = listOf(Pair(LocalTime.now(), LocalTime.now())),
        var nettoWork: LocalTime = LocalTime.MIN)