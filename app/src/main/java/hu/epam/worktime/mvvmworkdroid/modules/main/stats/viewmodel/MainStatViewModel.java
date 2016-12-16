package hu.epam.worktime.mvvmworkdroid.modules.main.stats.viewmodel;

import java.util.List;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;
import hu.epam.worktime.mvvmworkdroid.BR;
import hu.epam.worktime.mvvmworkdroid.common.mvvm.ViewModel;
import hu.epam.worktime.mvvmworkdroid.common.widgets.recyclerview.ListItemViewModel;
import hu.epam.worktime.mvvmworkdroid.modules.main.stats.model.MainStatsModel;

/**
 *
 *
 * Created by Mihaly_Hunyady on 2016. 12. 12..
 */

public class MainStatViewModel extends BaseObservable implements ViewModel {
    @Bindable
    private List<ListItemViewModel> workTimeItemViewModels;
    private final MainStatsModel model;
    private final static String TAG = "MainListViewModel";

    public MainStatViewModel(MainStatsModel model) {
        this.model = model;
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
