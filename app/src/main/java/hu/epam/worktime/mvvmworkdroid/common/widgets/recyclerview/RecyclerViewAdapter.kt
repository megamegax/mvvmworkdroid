package hu.epam.worktime.mvvmworkdroid.common.widgets.recyclerview

import java.util.Collections

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import hu.epam.worktime.mvvmworkdroid.BR

/**
 * Common adapter which is used for each recyclerview when using databinding.
 * Created by Mate_Redecsi on 10/20/2016.
 */
class RecyclerViewAdapter : RecyclerView.Adapter<BindingViewHolder>() {
    private var column: Int = 0
    private var displayFullRows: Boolean = false
    private var items: List<ListItemViewModel>? = null
    private var viewTypes: List<Int>? = null
    private var itemLayouts: List<Int>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        val itemLayout = itemLayouts!![if (viewTypes != null) viewTypes!!.indexOf(viewType) else viewType]
        val dataBinding = DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(parent.context), itemLayout, parent, false)
        return BindingViewHolder(dataBinding)
    }

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        holder.binding.setVariable(BR.viewModel, items!![position])
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        var itemCount = if (items == null) 0 else items!!.size
        if (itemCount > column && displayFullRows) {
            itemCount = getRoundDownItemCount(itemCount)
        }
        return itemCount
    }

    override fun getItemViewType(position: Int): Int {
        return items!![position].viewType
    }

    fun setItems(items: List<ListItemViewModel>) {
        this.items = items
        notifyDataSetChanged()
    }

    fun setItemLayout(itemLayout: Int) {
        this.itemLayouts = listOf(itemLayout)
    }

    fun setItemLayouts(itemLayouts: List<Int>) {
        this.itemLayouts = itemLayouts
    }

    fun setViewTypes(viewTypes: List<Int>) {
        this.viewTypes = viewTypes
    }

    fun setColumn(column: Int) {
        this.column = column
    }

    fun displayFullRows(displayFullRows: Boolean) {
        this.displayFullRows = displayFullRows
    }

    private fun getRoundDownItemCount(itemCount: Int): Int {
        return itemCount - itemCount % column
    }
}
