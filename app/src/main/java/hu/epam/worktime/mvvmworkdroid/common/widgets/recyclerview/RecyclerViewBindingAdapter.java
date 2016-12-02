package hu.epam.worktime.mvvmworkdroid.common.widgets.recyclerview;

import java.util.ArrayList;
import java.util.List;

import com.hcom.android.modules.common.presenter.recycleview.decoration.SpacesItemDecorationForGridLayoutManager;

import android.content.res.TypedArray;
import android.databinding.BindingAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * BindingAdapter which defines custom binding for recyclerview. It helps to avoid boilerplate codes by providing the opportunity to
 * setup adapter and layoutmanager from XML.
 * Created by Mate_Redecsi on 10/19/2016.
 */
public final class RecyclerViewBindingAdapter {
    private RecyclerViewBindingAdapter() {
    }

    @BindingAdapter("columns")
    public static void setupRecyclerViewColumns(RecyclerView recyclerView, int columns) {
        recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), columns));
        getAdapter(recyclerView).setColumn(columns);
    }

    @BindingAdapter("displayFullRows")
    public static void setupRecyclerViewRoundDownFlag(RecyclerView recyclerView, boolean displayFullRows) {
        getAdapter(recyclerView).displayFullRows(displayFullRows);
    }

    @BindingAdapter("source")
    public static void setupRecyclerViewSource(RecyclerView recyclerView, List<ListItemViewModel> source) {
        getAdapter(recyclerView).setItems(source);
    }

    @BindingAdapter("itemLayout")
    public static void setupRecyclerViewItemLayout(RecyclerView recyclerView, int itemLayout) {
        getAdapter(recyclerView).setItemLayout(itemLayout);
    }

    @BindingAdapter("itemLayouts")
    public static void setupRecyclerViewItemLayouts(RecyclerView recyclerView, TypedArray itemLayouts) {
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

        getAdapter(recyclerView).setItemLayouts(layoutIds);
    }

    @BindingAdapter({"itemSpacing", "columns"})
    public static void setupRecyclerViewItemSpacing(RecyclerView recyclerView, float itemSpacing, int columns) {
        setupRecyclerViewColumns(recyclerView, columns);
        recyclerView.addItemDecoration(
                new SpacesItemDecorationForGridLayoutManager((int) itemSpacing, columns));
    }

    private static RecyclerViewAdapter getAdapter(RecyclerView recyclerView) {
        RecyclerViewAdapter adapter = (RecyclerViewAdapter) recyclerView.getAdapter();
        if (adapter == null) {
            adapter = new RecyclerViewAdapter();
            recyclerView.setAdapter(adapter);
        }
        return adapter;
    }
}
