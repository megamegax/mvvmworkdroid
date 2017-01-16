package hu.epam.worktime.mvvmworkdroid.modules.main.stats.model

import java.util.Collections

import hu.epam.worktime.mvvmworkdroid.modules.dal.WorkItemDao
import hu.epam.worktime.mvvmworkdroid.modules.services.models.WorkingStatistics

/**
 * Created by Mihaly_Hunyady on 2016. 12. 02..
 */

class MainStatsModel(private val workItemDao: WorkItemDao) {

    fun loadWorkDays(): WorkingStatistics {
        val workingStatistics = workItemDao.find()
        Collections.sort(workingStatistics.workTimes)
        return workingStatistics
    }

}
