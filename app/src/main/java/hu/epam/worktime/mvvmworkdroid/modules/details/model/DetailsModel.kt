package hu.epam.worktime.mvvmworkdroid.modules.details.model

import org.threeten.bp.format.DateTimeFormatter

import hu.epam.worktime.mvvmworkdroid.modules.dal.WorkItemDao
import hu.epam.worktime.mvvmworkdroid.modules.services.models.WorkTime

/**
 * Created by Mihaly_Hunyady on 2017. 01. 06..
 */

class DetailsModel(dao: WorkItemDao) {
    private var selectedWorkTime: WorkTime? = null

    fun setSelectedWorkTime(selectedWorkTime: WorkTime) {
        this.selectedWorkTime = selectedWorkTime
    }

    val arriveTime: String
        get() = selectedWorkTime!!.arrive.format(DateTimeFormatter.ISO_LOCAL_TIME)

    val leaveTime: String
        get() = selectedWorkTime!!.leave.format(DateTimeFormatter.ISO_LOCAL_TIME)

    val currentDate: String
        get() = selectedWorkTime!!.date.format(DateTimeFormatter.ISO_LOCAL_DATE)

    val dinnerStartTime: String
        get() {
            val dinnerStart = selectedWorkTime!!.dinner.first.format(DateTimeFormatter.ISO_LOCAL_TIME)
            if (dinnerStart == "00:00:00") {
                return "12:00:00"
            } else {
                return dinnerStart
            }
        }

    val dinnerEndTime: String
        get() {
            val dinnerEnd = selectedWorkTime!!.dinner.second.format(DateTimeFormatter.ISO_LOCAL_TIME)
            if (dinnerEnd == "00:00:00") {
                return "12:30:00"
            } else {
                return dinnerEnd
            }
        }
}
