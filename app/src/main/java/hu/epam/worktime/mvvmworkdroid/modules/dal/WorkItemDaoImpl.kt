package hu.epam.worktime.mvvmworkdroid.modules.dal

import hu.epam.worktime.mvvmworkdroid.modules.dal.model.WorkTimeEntity
import hu.epam.worktime.mvvmworkdroid.modules.dal.model.WorkingStatisticsEntity
import hu.epam.worktime.mvvmworkdroid.modules.services.models.WorkTime
import hu.epam.worktime.mvvmworkdroid.modules.services.models.WorkingStatistics
import io.realm.Realm
import io.realm.RealmList
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter


/**
 * WorkItemDao implementation
 * Created by hunyadym on 2016. 12. 21..
 */
class WorkItemDaoImpl : WorkItemDao {


    override fun findAll(): List<WorkingStatistics> {
        val realm = Realm.getDefaultInstance();
        val taskEntities = realm.where(WorkingStatisticsEntity::class.java).findAll()
        return taskEntities.map { convertEntityToTask(it) }
    }

    override fun findWorkingStatisticsByDate(date: LocalDate): WorkingStatistics {
        val realm = Realm.getDefaultInstance();
        val taskEntity = realm.where(WorkingStatisticsEntity::class.java).equalTo("date", date.format(DateTimeFormatter.ISO_DATE)).findFirst()
        return convertEntityToTask(taskEntity)

    }

    override fun saveWorkingStatistics(workingStatistics: WorkingStatistics) {
        val realm = Realm.getDefaultInstance();
        if (workingStatistics.id != null) {
            val entity = realm.where(WorkingStatisticsEntity::class.java).equalTo("id", workingStatistics.id).findFirst()
            updateTask(entity, workingStatistics)
        } else {
            realm.executeTransaction {
                val entity = realm.createObject(WorkingStatisticsEntity::class.java)
                entity.avgWorkTime = workingStatistics.avgWorkTime.format(DateTimeFormatter.ISO_TIME)
                entity.dailyWorkTime = workingStatistics.dailyWorkTime.format(DateTimeFormatter.ISO_TIME)
                entity.daysToWork = workingStatistics.daysToWork
                entity.montlyWorkTime = workingStatistics.montlyWorkTime.format(DateTimeFormatter.ISO_TIME)
                entity.workTimes = convertWorkTimesToWorkTimeEntities(workingStatistics.workTimes)
                entity.id = entity.getNextPrimaryKey(realm) ?: 0
                entity.workTimeLeft = workingStatistics.workTimeLeft
            }
        }
    }


    private fun updateTask(entity: WorkingStatisticsEntity, workingStatistics: WorkingStatistics) {
        val realm = Realm.getDefaultInstance()
        realm.executeTransaction {
            entity.avgWorkTime = workingStatistics.avgWorkTime.format(DateTimeFormatter.ISO_TIME)
            entity.dailyWorkTime = workingStatistics.dailyWorkTime.format(DateTimeFormatter.ISO_TIME)
            entity.daysToWork = workingStatistics.daysToWork
            entity.montlyWorkTime = workingStatistics.montlyWorkTime.format(DateTimeFormatter.ISO_TIME)
            entity.workTimes = convertWorkTimesToWorkTimeEntities(workingStatistics.workTimes)
            entity.id = workingStatistics.id!!
            entity.workTimeLeft = workingStatistics.workTimeLeft
        }
    }

    private fun convertWorkTimesToWorkTimeEntities(workTimes: List<WorkTime>): RealmList<WorkTimeEntity> {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun convertEntityToTask(entity: WorkingStatisticsEntity): WorkingStatistics {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
        //return WorkingStatistics()
    }


    override fun deleteAll() {
        val realm = Realm.getDefaultInstance()
        realm.executeTransaction {
            realm.deleteAll()
        }
    }
}