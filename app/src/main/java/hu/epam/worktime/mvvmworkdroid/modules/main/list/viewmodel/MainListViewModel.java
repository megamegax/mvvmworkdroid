package hu.epam.worktime.mvvmworkdroid.modules.main.list.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;
import hu.epam.worktime.mvvmworkdroid.BR;
import hu.epam.worktime.mvvmworkdroid.common.mvvm.ViewModel;
import hu.epam.worktime.mvvmworkdroid.common.widgets.recyclerview.ListItemViewModel;
import hu.epam.worktime.mvvmworkdroid.modules.main.list.model.MainListModel;
import hu.epam.worktime.mvvmworkdroid.modules.main.main.router.MainRouter;

import java.util.List;

/**
 *
 *
 * Created by Mihaly_Hunyady on 2016. 12. 12..
 */

public class MainListViewModel extends BaseObservable implements ViewModel {
    @Bindable
    private List<ListItemViewModel> workTimeItemViewModels;
    private final MainListModel model;
    private final MainRouter router;
    private final static String TAG = "MainListViewModel";

    public MainListViewModel(MainListModel model, MainRouter mainRouter) {
        this.model = model;
        this.router = mainRouter;
    }

    @Override
    public void onStop() {

    }

    @Override
    public void onStart() {

    }

    public void setWorkTimeItemViewModels(List<ListItemViewModel> workTimeItemViewModels) {
        this.workTimeItemViewModels = workTimeItemViewModels;
        Log.d(TAG, workTimeItemViewModels.size() + "");
        notifyPropertyChanged(BR.workTimeItemViewModels);
    }

    public List<ListItemViewModel> getWorkTimeItemViewModels() {
        return workTimeItemViewModels;
    }

}
