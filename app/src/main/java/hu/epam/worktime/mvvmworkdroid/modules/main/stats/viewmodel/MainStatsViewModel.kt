package hu.epam.worktime.mvvmworkdroid.modules.main.stats.viewmodel

import android.databinding.Bindable

import org.threeten.bp.format.DateTimeFormatter

import hu.epam.worktime.mvvmworkdroid.BR
import hu.epam.worktime.mvvmworkdroid.common.widgets.viewpager.ViewItemViewModel
import hu.epam.worktime.mvvmworkdroid.modules.main.stats.model.MainStatsModel
import hu.epam.worktime.mvvmworkdroid.modules.services.models.WorkingStatistics

/**
 * Created by Mihaly_Hunyady on 2016. 12. 12..
 */

class MainStatsViewModel(val model: MainStatsModel) : ViewItemViewModel() {
    @Bindable
    var dailyWorkTime: String? = null
    @Bindable
    var montlyWorkTime: String? = null
    @Bindable
    var daysToWork: String? = null
    @Bindable
    var workTimeLeft: String? = null
    @Bindable
    var avgWorkTime: String? = null

    fun setWorkingStatistics(workingStatistics: WorkingStatistics) {
        avgWorkTime = workingStatistics.avgWorkTime.format(DateTimeFormatter.ISO_LOCAL_TIME)
        dailyWorkTime = workingStatistics.dailyWorkTime.format(DateTimeFormatter.ISO_LOCAL_TIME)
        daysToWork = workingStatistics.daysToWork.toString()
        montlyWorkTime = workingStatistics.montlyWorkTime.format(DateTimeFormatter.ISO_LOCAL_TIME)
        workTimeLeft = workingStatistics.workTimeLeft
        notifyPropertyChanged(BR.avgWorkTime)
        notifyPropertyChanged(BR.dailyWorkTime)
        notifyPropertyChanged(BR.daysToWork)
        notifyPropertyChanged(BR.workTimeLeft)
        notifyPropertyChanged(BR.montlyWorkTime)
    }

    override fun onRefresh() {
        setWorkingStatistics(model.loadWorkDays())
    }

    companion object {
        private val TAG = "MainStatsViewModel"
    }
}
