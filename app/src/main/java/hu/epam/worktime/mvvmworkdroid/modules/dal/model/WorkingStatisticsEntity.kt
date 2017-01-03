package hu.epam.worktime.mvvmworkdroid.modules.dal.model

import io.realm.RealmList
import io.realm.RealmObject

open class WorkingStatisticsEntity() : RealmObject() {

    var id: Int = 0
    lateinit var dailyWorkTime: String
    lateinit var montlyWorkTime: String
    var daysToWork: Int = 0
    lateinit var workTimeLeft: String
    lateinit var avgWorkTime: String
    lateinit var workTimes: RealmList<WorkTimeEntity>


    constructor(dailyWorkTime: String,
                montlyWorkTime: String,
                daysToWork: Int,
                workTimeLeft: String,
                avgWorkTime: String,
                workTimes: RealmList<WorkTimeEntity>) : this() {

        this.dailyWorkTime = dailyWorkTime
        this.montlyWorkTime = montlyWorkTime
        this.daysToWork = daysToWork
        this.workTimeLeft = workTimeLeft
        this.avgWorkTime = avgWorkTime
        this.workTimes = workTimes
    }
}