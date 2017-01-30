package hu.epam.worktime.mvvmworkdroid.modules.main.main.viewmodel

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.util.Log

import java.util.ArrayList

import hu.epam.worktime.mvvmworkdroid.common.widgets.viewpager.ViewItemViewModel
import hu.epam.worktime.mvvmworkdroid.modules.main.main.model.MainModel
import hu.epam.worktime.mvvmworkdroid.modules.main.main.router.MainRouter
import hu.epam.worktime.mvvmworkdroid.modules.services.models.WorkingStatistics

/**
 * Created by Mihaly_Hunyady on 2016. 12. 02..
 */

class MainViewModel(private val model: MainModel, private val router: MainRouter) : BaseObservable(), MainModel.ModelCallback {
    @Bindable
    private val views = ArrayList<ViewItemViewModel>()
    private var workTimes: WorkingStatistics? = null
    var isLoading: Boolean = false

    init {
        init(model)
    }

    fun addView(viewModel: ViewItemViewModel) {
        views.add(viewModel)
    }

    private fun init(model: MainModel) {
        model.callback = this
        refresh()
    }

    fun refresh() {
        model.loadWorkTimes()
        isLoading = true
    }

    fun onStop() {
        model.callback = null
    }

    fun onStart() {
        model.callback = this
    }

    override fun onWorkTimeLoaded(workTimes: WorkingStatistics) {
        this.workTimes = workTimes
    }


    override fun onWorkTimeLoadError(throwable: Throwable) {
        throwable.printStackTrace()
        isLoading = false
    }

    override fun onWorkTimeLoadCompleted() {
        isLoading = false
        views.forEach(ViewItemViewModel::onRefresh)
    }

    fun getViews(): List<ViewItemViewModel> {
        return views
    }

    companion object {
        private val TAG = "MainViewModel"
    }
}
