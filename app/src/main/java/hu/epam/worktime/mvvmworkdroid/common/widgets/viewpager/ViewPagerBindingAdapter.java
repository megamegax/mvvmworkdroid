package hu.epam.worktime.mvvmworkdroid.common.widgets.viewpager;

import android.content.res.TypedArray;
import android.databinding.BindingAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import hu.epam.worktime.mvvmworkdroid.common.widgets.recyclerview.ListItemViewModel;
import hu.epam.worktime.mvvmworkdroid.common.widgets.recyclerview.RecyclerViewAdapter;

/**
 * Created by hunyadym on 2017. 01. 03..
 */

public class ViewPagerBindingAdapter {
    private ViewPagerBindingAdapter() {

    }

    @BindingAdapter("source")
    public static void setupViewPagerSource(ViewPager viewPager, List<ViewItemViewModel> source) {
        getAdapter(viewPager).setItems(source);
    }

    @BindingAdapter("itemLayout")
    public static void setupViewPagerItemLayout(ViewPager viewPager, int itemLayout) {
        getAdapter(viewPager).setItemLayout(itemLayout);
    }

    @BindingAdapter("itemLayouts")
    public static void setupViewPagerItemLayouts(ViewPager viewPager, TypedArray itemLayouts) {
        List<Integer> layoutIds = new ArrayList<>();
        int item;
        int i = 0;
        do {
            item = itemLayouts.getResourceId(i, -1);
            if (item >= 0) {
                layoutIds.add(item);
            }
            i++;
        } while (item >= 0);

        getAdapter(viewPager).setItemLayouts(layoutIds);
    }

    private static ViewPagerAdapter getAdapter(ViewPager viewPager) {
        ViewPagerAdapter adapter = (ViewPagerAdapter) viewPager.getAdapter();
        if (adapter == null) {
            adapter = new ViewPagerAdapter(viewPager.getContext());
            viewPager.setAdapter(adapter);
        }
        return adapter;
    }
}
