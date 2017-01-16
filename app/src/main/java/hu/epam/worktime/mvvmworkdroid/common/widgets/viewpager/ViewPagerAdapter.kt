package hu.epam.worktime.mvvmworkdroid.common.widgets.viewpager

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import java.util.Collections

import hu.epam.worktime.mvvmworkdroid.BR


/**
 * Created by hunyadym on 2017. 01. 03..
 */

class ViewPagerAdapter(context: Context) : PagerAdapter() {
    private var items: List<ViewItemViewModel>? = null
    private var itemLayouts: List<Int>? = null
    private val inflater: LayoutInflater

    init {
        this.inflater = LayoutInflater.from(context)
    }

    /**
     * Get a View that displays the data at the specified position in the data set.

     * @param position The position of the item within the adapter's data set of the item whose view we want.
     * *
     * @param pager    The ViewPager that this view will eventually be attached to.
     * *
     * @return A View corresponding to the data at the specified position.
     */
    fun getView(position: Int, pager: ViewPager): View {
        val dataBinding = DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(pager.context), itemLayouts!![position], pager, false)
        dataBinding.setVariable(BR.viewModel, items!![position])
        dataBinding.executePendingBindings()
        return dataBinding.root
    }


    override fun getCount(): Int {
        return if (items != null) items!!.size else 0
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    /**
     * Create the page for the given position.

     * @param container The containing View in which the page will be shown.
     * *
     * @param position  The page position to be instantiated.
     * *
     * @return Returns an Object representing the new page. This does not need
     * * to be a View, but can be some other container of the page.
     */
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val pager = container as ViewPager
        val view = getView(position, pager)

        pager.addView(view)

        return view
    }

    /**
     * Remove a page for the given position.

     * @param container The containing View from which the page will be removed.
     * *
     * @param position  The page position to be removed.
     * *
     * @param view      The same object that was returned by instantiateItem(View, int).
     */
    override fun destroyItem(container: ViewGroup, position: Int, view: Any) {
        (container as ViewPager).removeView(view as View)
    }

    fun setItems(items: List<ViewItemViewModel>) {
        this.items = items
        notifyDataSetChanged()
    }

    fun setItemLayout(itemLayout: Int) {
        this.itemLayouts = listOf(itemLayout)
    }

    fun setItemLayouts(itemLayouts: List<Int>) {
        this.itemLayouts = itemLayouts
    }

}
