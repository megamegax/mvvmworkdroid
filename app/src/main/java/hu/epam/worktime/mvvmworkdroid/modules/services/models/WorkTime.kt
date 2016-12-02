package hu.epam.worktime.mvvmworkdroid.modules.services.models

import org.threeten.bp.LocalDate
import org.threeten.bp.LocalTime


data class WorkTime(
        val date: LocalDate = LocalDate.MIN,
        val arrive: LocalTime = LocalTime.MIN,
        val leave: LocalTime = LocalTime.MIN,
        val dinner: Pair<LocalTime, LocalTime> = Pair(LocalTime.now(), LocalTime.now()),
        val offtime: List<Pair<LocalTime, LocalTime>> = listOf(Pair(LocalTime.now(), LocalTime.now())),
        var nettoWork: LocalTime = LocalTime.MIN) {
    constructor() : this(LocalDate.MIN, LocalTime.MIN, LocalTime.MIN, Pair(LocalTime.now(), LocalTime.now()), listOf(Pair(LocalTime.now(), LocalTime.now())), LocalTime.MIN)
}