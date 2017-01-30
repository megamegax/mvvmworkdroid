package hu.epam.worktime.mvvmworkdroid.modules.main.main.model

import hu.epam.worktime.mvvmworkdroid.modules.dal.WorkItemDao
import hu.epam.worktime.mvvmworkdroid.modules.services.WorkServiceApi
import hu.epam.worktime.mvvmworkdroid.modules.services.models.WorkDay
import hu.epam.worktime.mvvmworkdroid.modules.services.models.WorkTime
import hu.epam.worktime.mvvmworkdroid.modules.services.models.WorkingStatistics
import hu.epam.worktime.mvvmworkdroid.modules.services.worker.CalculatorService
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by Mihaly_Hunyady on 2016. 12. 12..
 */

class MainModel(private val workServiceApi: WorkServiceApi, private val calculatorService: CalculatorService, private val workItemDao: WorkItemDao) {
    var callback: MainModel.ModelCallback? = null
    var workingStatistics: WorkingStatistics? = null
    private lateinit var workTimes: List<WorkTime>

    fun loadWorkTimes() {
        workServiceApi.workTimes(2)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ onResult(it) }, { onError(it) }) { onWorkTimesCompleted() }
    }

    private fun loadWorkDays() {
        workServiceApi.workDays()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ onWorkDayResult(it) }, { onError(it) }) { onCompleted() }
    }

    private fun onWorkDayResult(workDays: List<WorkDay>?) {
        if (workDays != null && workDays.isNotEmpty()) {
            calculatorService.workDays = workDays
            this.workingStatistics = calculatorService.calculateStuffs(workTimes)
            workingStatistics?.let {
                workItemDao.saveWorkingStatistics(workingStatistics!!)
            }
        }

        callback?.run { onWorkTimeLoaded(workItemDao.find()) }

    }

    private fun onResult(workTimes: List<WorkTime>) {
        this.workTimes = workTimes
        loadWorkDays()
    }

    private fun onError(throwable: Throwable) {
        workingStatistics = workItemDao.find()
        callback?.run {
            onWorkTimeLoadError(throwable)
        }
    }

    private fun onWorkTimesCompleted() {
        loadWorkDays()
    }

    private fun onCompleted() {
        callback?.run {
            onWorkTimeLoadCompleted()
        }
    }

    interface ModelCallback {
        fun onWorkTimeLoaded(workTimes: WorkingStatistics)

        fun onWorkTimeLoadError(throwable: Throwable)

        fun onWorkTimeLoadCompleted()
    }
}
