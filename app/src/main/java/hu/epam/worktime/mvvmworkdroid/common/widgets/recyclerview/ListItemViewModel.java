package hu.epam.worktime.mvvmworkdroid.common.widgets.recyclerview;

import android.databinding.BaseObservable;

/**
 * Abstract viewmodel for list items which provides the viewtype of the item.
 * Created by Mate_Redecsi on 10/21/2016.
 */
public abstract class ListItemViewModel extends BaseObservable {
    public abstract int getViewType();
}
