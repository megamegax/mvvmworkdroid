package hu.epam.worktime.mvvmworkdroid.modules.dal

import hu.epam.worktime.mvvmworkdroid.modules.dal.model.PairEntity
import hu.epam.worktime.mvvmworkdroid.modules.dal.model.WorkTimeEntity
import hu.epam.worktime.mvvmworkdroid.modules.dal.model.WorkingStatisticsEntity
import hu.epam.worktime.mvvmworkdroid.modules.services.models.WorkTime
import hu.epam.worktime.mvvmworkdroid.modules.services.models.WorkingStatistics
import io.realm.Realm
import io.realm.RealmList
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalTime
import org.threeten.bp.format.DateTimeFormatter
import java.util.*


/**
 * WorkItemDao implementation
 * Created by hunyadym on 2016. 12. 21..
 */
class WorkItemDaoImpl : WorkItemDao {


    override fun find(): WorkingStatistics {
        val realm = Realm.getDefaultInstance();
        val entity = realm.where(WorkingStatisticsEntity::class.java).findFirst()
        return convertEntityToModel(entity)
    }

    override fun saveWorkingStatistics(workingStatistics: WorkingStatistics) {
        val realm = Realm.getDefaultInstance();

        val entity = realm.where(WorkingStatisticsEntity::class.java).equalTo("id", workingStatistics.id).findFirst()

        if (entity != null) {
            updateTask(entity, workingStatistics)
        } else {
            realm.executeTransaction {
                val newEntity = realm.createObject(WorkingStatisticsEntity::class.java)
                newEntity.avgWorkTime = workingStatistics.avgWorkTime.format(DateTimeFormatter.ISO_TIME)
                newEntity.dailyWorkTime = workingStatistics.dailyWorkTime.format(DateTimeFormatter.ISO_TIME)
                newEntity.daysToWork = workingStatistics.daysToWork
                newEntity.montlyWorkTime = workingStatistics.montlyWorkTime.format(DateTimeFormatter.ISO_TIME)
                newEntity.workTimes = convertWorkTimesToWorkTimeEntities(workingStatistics.workTimes)
                newEntity.workTimeLeft = workingStatistics.workTimeLeft
                newEntity.id = workingStatistics.id
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
            entity.id = workingStatistics.id
            entity.workTimeLeft = workingStatistics.workTimeLeft
        }
    }

    private fun convertWorkTimesToWorkTimeEntities(workTimes: List<WorkTime>): RealmList<WorkTimeEntity> {
        val realmList = RealmList<WorkTimeEntity>()
        val realm = Realm.getDefaultInstance()

        workTimes.forEach {
            val entity = realm.copyToRealm(WorkTimeEntity(
                    arrive = it.arrive.format(DateTimeFormatter.ISO_TIME),
                    date = it.date.format(DateTimeFormatter.ISO_DATE),
                    dinner = convertPairToPairEntity(it.dinner),
                    leave = it.leave.format(DateTimeFormatter.ISO_TIME),
                    nettoWork = it.nettoWork.format(DateTimeFormatter.ISO_TIME),
                    offtime = convertPairListToPairEntityList(it.offtime)))
            realmList.add(entity)

        }
        return realmList
    }

    private fun convertPairListToPairEntityList(list: List<Pair<LocalTime, LocalTime>>): RealmList<PairEntity> {
        val realmList = RealmList<PairEntity>()
        list.forEach { realmList.add(convertPairToPairEntity(it)) }
        return realmList
    }

    private fun convertPairEntityListToPairList(realmList: RealmList<PairEntity>): List<Pair<LocalTime, LocalTime>> {
        val list = ArrayList<Pair<LocalTime, LocalTime>>()
        realmList.forEach { convertPairEntityToPair(it) }
        return list
    }

    private fun convertPairEntityToPair(pairEntity: PairEntity): Pair<LocalTime, LocalTime> {
        return LocalTime.parse(pairEntity.first) to LocalTime.parse(pairEntity.second)
    }

    private fun convertPairToPairEntity(pair: Pair<LocalTime, LocalTime>): PairEntity {
        val realm = Realm.getDefaultInstance()
        val pairEntity = realm.createObject(PairEntity::class.java)
        pairEntity.first = pair.first.format(DateTimeFormatter.ISO_TIME)
        pairEntity.second = pair.second.format(DateTimeFormatter.ISO_TIME)
        return pairEntity
    }

    private fun convertEntityToModel(entity: WorkingStatisticsEntity): WorkingStatistics {
        val model = WorkingStatistics(
                dailyWorkTime = LocalTime.parse(entity.dailyWorkTime),
                avgWorkTime = LocalTime.parse(entity.avgWorkTime),
                daysToWork = entity.daysToWork,
                montlyWorkTime = LocalTime.parse(entity.montlyWorkTime),
                workTimeLeft = entity.workTimeLeft,
                workTimes = convertWorkTimesEntityToWorkTime(entity.workTimes)

        )
        return model
    }

    private fun convertWorkTimesEntityToWorkTime(realmList: RealmList<WorkTimeEntity>): List<WorkTime> {
        val workTimes = ArrayList<WorkTime>()
        realmList.forEach {
            workTimes.add(WorkTime(
                    arrive = LocalTime.parse(it.arrive),
                    nettoWork = LocalTime.parse(it.nettoWork),
                    date = LocalDate.parse(it.date),
                    offtime = convertPairEntityListToPairList(it.offtime),
                    dinner = convertPairEntityToPair(it.dinner),
                    leave = LocalTime.parse(it.leave)
            ))
        }
        return workTimes
    }


    override fun deleteAll() {
        val realm = Realm.getDefaultInstance()
        realm.executeTransaction {
            realm.deleteAll()
        }
    }
}