package hu.epam.worktime.mvvmworkdroid.common.widgets.recyclerview

import java.util.ArrayList


import android.content.res.TypedArray
import android.databinding.BindingAdapter
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import hu.epam.worktime.mvvmworkdroid.common.widgets.recyclerview.decorator.SpacesItemDecorationForGridLayoutManager

/**
 * BindingAdapter which defines custom binding for recyclerview. It helps to avoid boilerplate codes by providing the opportunity to
 * setup adapter and layoutmanager from XML.
 * Created by Mate_Redecsi on 10/19/2016.
 */
object RecyclerViewBindingAdapter {

    @BindingAdapter("columns")
    fun setupRecyclerViewColumns(recyclerView: RecyclerView, columns: Int) {
        recyclerView.layoutManager = GridLayoutManager(recyclerView.context, columns)
        getAdapter(recyclerView).setColumn(columns)
    }

    @BindingAdapter("displayFullRows")
    fun setupRecyclerViewRoundDownFlag(recyclerView: RecyclerView, displayFullRows: Boolean) {
        getAdapter(recyclerView).displayFullRows(displayFullRows)
    }

    @BindingAdapter("source")
    fun setupRecyclerViewSource(recyclerView: RecyclerView, source: List<ListItemViewModel>) {
        getAdapter(recyclerView).setItems(source)
    }

    @BindingAdapter("itemLayout")
    fun setupRecyclerViewItemLayout(recyclerView: RecyclerView, itemLayout: Int) {
        getAdapter(recyclerView).setItemLayout(itemLayout)
    }

    @BindingAdapter("itemLayouts")
    fun setupRecyclerViewItemLayouts(recyclerView: RecyclerView, itemLayouts: TypedArray) {
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

        getAdapter(recyclerView).setItemLayouts(layoutIds)
    }

    @BindingAdapter("itemSpacing", "columns")
    fun setupRecyclerViewItemSpacing(recyclerView: RecyclerView, itemSpacing: Float, columns: Int) {
        setupRecyclerViewColumns(recyclerView, columns)
        recyclerView.addItemDecoration(
                SpacesItemDecorationForGridLayoutManager(itemSpacing.toInt(), columns))
    }

    private fun getAdapter(recyclerView: RecyclerView): RecyclerViewAdapter {
        var adapter: RecyclerViewAdapter? = recyclerView.adapter as RecyclerViewAdapter
        if (adapter == null) {
            adapter = RecyclerViewAdapter()
            recyclerView.adapter = adapter
        }
        return adapter
    }
}
