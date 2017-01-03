package hu.epam.worktime.mvvmworkdroid.modules.dal.model

import hu.epam.worktime.mvvmworkdroid.modules.dal.AutoIncrementable
import io.realm.Realm
import io.realm.RealmList
import io.realm.RealmObject
import org.threeten.bp.LocalTime

open class WorkingStatisticsEntity() : RealmObject(), AutoIncrementable {
    override fun setPrimaryKey(primaryKey: Int) {
        this.id = primaryKey
    }

    override fun getNextPrimaryKey(realm: Realm): Int? {
        return realm.where(WorkingStatisticsEntity::class.java).max("id").toInt() + 1
    }

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