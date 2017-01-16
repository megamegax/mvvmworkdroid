package hu.epam.worktime.mvvmworkdroid.modules.details.viewmodel

import android.databinding.BaseObservable
import android.databinding.Bindable

import java.util.ArrayList

import hu.epam.worktime.mvvmworkdroid.modules.details.model.DetailsModel
import hu.epam.worktime.mvvmworkdroid.modules.details.router.DetailsRouter
import hu.epam.worktime.mvvmworkdroid.modules.services.models.WorkTime

/**
 * Created by Mihaly_Hunyady on 2017. 01. 06..
 */

class DetailsViewModel(internal var model: DetailsModel, router: DetailsRouter) : BaseObservable() {
    @Bindable
    var arriveTime: String? = null
        private set
    @Bindable
    var leaveTime: String? = null
        private set
    @Bindable
    var currentDate: String? = null
        private set
    @Bindable
    var dinnerStart: String? = null
        private set
    @Bindable
    var dinnerEnd: String? = null
        private set
    @Bindable
    private val others = ArrayList<String>()

    fun setSelectedWorkTime(selectedWorkTime: WorkTime) {
        model.setSelectedWorkTime(selectedWorkTime)
        init()
    }

    private fun init() {
        arriveTime = model.arriveTime
        leaveTime = model.leaveTime
        currentDate = model.currentDate
        dinnerStart = model.dinnerStartTime
        dinnerEnd = model.dinnerEndTime
        notifyChange()
    }

}
