package hu.epam.worktime.mvvmworkdroid.common.widgets.viewpager

import android.databinding.BaseObservable


/**
 * Created by hunyadym on 2017. 01. 03..
 */

abstract class ViewItemViewModel : BaseObservable() {
    abstract fun onRefresh()
}
