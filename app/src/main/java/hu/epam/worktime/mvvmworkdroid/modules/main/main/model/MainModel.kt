package hu.epam.worktime.mvvmworkdroid.modules.main.main.model

import android.util.Log

import hu.epam.worktime.mvvmworkdroid.modules.dal.WorkItemDao
import hu.epam.worktime.mvvmworkdroid.modules.services.WorkServiceApi
import hu.epam.worktime.mvvmworkdroid.modules.services.models.WorkDay
import hu.epam.worktime.mvvmworkdroid.modules.services.models.WorkTime
import hu.epam.worktime.mvvmworkdroid.modules.services.models.WorkingStatistics
import hu.epam.worktime.mvvmworkdroid.modules.services.models.preferences.UserProfile
import hu.epam.worktime.mvvmworkdroid.modules.services.worker.CalculatorService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Action0
import rx.functions.Action1
import rx.schedulers.Schedulers

/**
 * Created by Mihaly_Hunyady on 2016. 12. 12..
 */

class MainModel(private val workServiceApi: WorkServiceApi, private val calculatorService: CalculatorService, private val workItemDao: WorkItemDao) {
    private var callback: MainModel.ModelCallback? = null
    private var workingStatistics: WorkingStatistics? = null
    private lateinit var workTimes: List<WorkTime>

    fun loadWorkTimes() {
        workServiceApi.workTimes(2)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ workTimes -> onResult(workTimes) }, { throwable -> onError(throwable) }) { onWorkTimesCompleted() }
    }

    private fun loadWorkDays() {
        workServiceApi.workDays()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ workDays -> onWorkDayResult(workDays) }, { throwable -> onError(throwable) }) { onCompleted() }
    }

    private fun onWorkDayResult(workDays: List<WorkDay>) {
        calculatorService.workDays = workDays
        this.workingStatistics = calculatorService.calculateStuffs(workTimes)
        workingStatistics?.let {
            workItemDao.saveWorkingStatistics(workingStatistics!!)
        }
    }

    private fun onResult(workTimes: List<WorkTime>) {
        this.workTimes = workTimes
        loadWorkDays()
    }

    private fun onError(throwable: Throwable) {
        Log.d("WorkDroid", "hiba van:" + throwable.message)
        if (callback != null) {
            callback!!.onWorkTimeLoadError()
        }
    }

    private fun onWorkTimesCompleted() {
        loadWorkDays()
    }

    private fun onCompleted() {
        if (callback != null) {
            callback!!.onWorkTimeLoadCompleted()
        }
    }

    fun setCallback(callback: ModelCallback?) {
        this.callback = callback
        if (this.callback != null) {
            workingStatistics?.let {
                this.callback!!.onWorkTimeLoaded(workingStatistics!!)
            }
        }
    }

    fun getWorkTimes(): WorkingStatistics {
        return workingStatistics!!
    }

    interface ModelCallback {
        fun onWorkTimeLoaded(workTimes: WorkingStatistics)

        fun onWorkTimeLoadError()

        fun onWorkTimeLoadCompleted()
    }
}
