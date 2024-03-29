package hu.epam.worktime.mvvmworkdroid.modules.dal

import hu.epam.worktime.mvvmworkdroid.modules.services.models.WorkingStatistics
import org.threeten.bp.LocalDate

/**
 * Created by hunyadym on 2017. 01. 03..
 */

interface WorkItemDao {
    fun find(): WorkingStatistics
    fun saveWorkingStatistics(workingStatistics: WorkingStatistics)
    fun deleteAll()
}
