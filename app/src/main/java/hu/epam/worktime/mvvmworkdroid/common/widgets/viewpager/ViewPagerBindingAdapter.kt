package hu.epam.worktime.mvvmworkdroid.common.widgets.viewpager

import android.content.res.TypedArray
import android.databinding.BindingAdapter
import android.support.v4.view.ViewPager
import android.support.v7.widget.RecyclerView

import java.util.ArrayList

import hu.epam.worktime.mvvmworkdroid.common.widgets.recyclerview.ListItemViewModel
import hu.epam.worktime.mvvmworkdroid.common.widgets.recyclerview.RecyclerViewAdapter

/**
 * Created by hunyadym on 2017. 01. 03..
 */

object ViewPagerBindingAdapter {

    @BindingAdapter("source")
    fun setupViewPagerSource(viewPager: ViewPager, source: List<ViewItemViewModel>) {
        getAdapter(viewPager).setItems(source)
    }

    @BindingAdapter("itemLayouts")
    fun setupViewPagerItemLayouts(viewPager: ViewPager, itemLayouts: TypedArray) {
        val layoutIds = ArrayList<Int>()
        var item: Int
        var i = 0
        do {
            item = itemLayouts.getResourceId(i, -1)
            if (item >= 0) {
                layoutIds.add(item)
            }
            i++
        } while (item >= 0)

        getAdapter(viewPager).setItemLayouts(layoutIds)
    }

    private fun getAdapter(viewPager: ViewPager): ViewPagerAdapter {
        var adapter: ViewPagerAdapter? = viewPager.adapter as ViewPagerAdapter
        if (adapter == null) {
            adapter = ViewPagerAdapter(viewPager.context)
            viewPager.adapter = adapter
        }
        return adapter
    }
}
