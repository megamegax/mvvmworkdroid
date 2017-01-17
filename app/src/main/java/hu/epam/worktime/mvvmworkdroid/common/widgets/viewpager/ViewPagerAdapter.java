package hu.epam.worktime.mvvmworkdroid.common.widgets.viewpager;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import hu.epam.worktime.mvvmworkdroid.BR;


/**
 * Created by hunyadym on 2017. 01. 03..
 */

public class ViewPagerAdapter extends PagerAdapter {
    private List<ViewItemViewModel> items;
    private List<Integer> itemLayouts;
    private LayoutInflater inflater;

    public ViewPagerAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    /**
     * Get a View that displays the data at the specified position in the data set.
     *
     * @param position The position of the item within the adapter's data set of the item whose view we want.
     * @param pager    The ViewPager that this view will eventually be attached to.
     * @return A View corresponding to the data at the specified position.
     */
    public View getView(int position, ViewPager pager) {
        ViewDataBinding dataBinding = DataBindingUtil.inflate(LayoutInflater.from(pager.getContext()), itemLayouts.get(position), pager, false);
        dataBinding.setVariable(BR.viewModel, items.get(position));
        dataBinding.executePendingBindings();
        return dataBinding.getRoot();
    }


    @Override
    public int getCount() {
        return items != null ? items.size() : 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    /**
     * Create the page for the given position.
     *
     * @param container The containing View in which the page will be shown.
     * @param position  The page position to be instantiated.
     * @return Returns an Object representing the new page. This does not need
     * to be a View, but can be some other container of the page.
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ViewPager pager = (ViewPager) container;
        View view = getView(position, pager);

        pager.addView(view);

        return view;
    }

    /**
     * Remove a page for the given position.
     *
     * @param container The containing View from which the page will be removed.
     * @param position  The page position to be removed.
     * @param view      The same object that was returned by instantiateItem(View, int).
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object view) {
        ((ViewPager) container).removeView((View) view);
    }

    public void setItems(List<ViewItemViewModel> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public void setItemLayout(int itemLayout) {
        this.itemLayouts = Collections.singletonList(itemLayout);
    }

    public void setItemLayouts(List<Integer> itemLayouts) {
        this.itemLayouts = itemLayouts;
    }

}
