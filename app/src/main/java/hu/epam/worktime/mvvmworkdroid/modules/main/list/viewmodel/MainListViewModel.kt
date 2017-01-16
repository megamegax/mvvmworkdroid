package hu.epam.worktime.mvvmworkdroid.modules.main.list.viewmodel

import android.databinding.Bindable
import android.util.Log

import java.util.ArrayList

import hu.epam.worktime.mvvmworkdroid.BR
import hu.epam.worktime.mvvmworkdroid.common.widgets.recyclerview.ListItemViewModel
import hu.epam.worktime.mvvmworkdroid.common.widgets.viewpager.ViewItemViewModel
import hu.epam.worktime.mvvmworkdroid.modules.main.list.model.MainListModel
import hu.epam.worktime.mvvmworkdroid.modules.main.main.router.MainRouter
import hu.epam.worktime.mvvmworkdroid.modules.main.main.viewmodel.WorkTimeViewModel
import hu.epam.worktime.mvvmworkdroid.modules.services.models.WorkTime

/**
 * Created by Mihaly_Hunyady on 2016. 12. 12..
 */

class MainListViewModel(private val model: MainListModel, private val router: MainRouter) : ViewItemViewModel() {
    @Bindable
    var workTimeItemViewModels: List<ListItemViewModel>? = null
        set(workTimeItemViewModels) {
            field = workTimeItemViewModels
            Log.d(TAG, workTimeItemViewModels?.size.toString() + "<- adatok beérkezve")
            notifyPropertyChanged(BR.workTimeItemViewModels)
        }

    private fun transformToItemViewModels(workTimes: List<WorkTime>): List<ListItemViewModel> {
        val itemViewModels = ArrayList<ListItemViewModel>()
        for (workTime in workTimes) {
            val workTimeViewModel = WorkTimeViewModel(router)
            workTimeViewModel.setWorkTime(workTime)
            itemViewModels.add(workTimeViewModel)
        }
        return itemViewModels
    }

    override fun onRefresh() {
        workTimeItemViewModels = transformToItemViewModels(model.loadWorkDays().workTimes)
    }

    companion object {
        private val TAG = "MainListViewModel"
    }
}
