package hu.epam.worktime.mvvmworkdroid.modules.main.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;
import hu.epam.worktime.mvvmworkdroid.BR;
import hu.epam.worktime.mvvmworkdroid.common.mvvm.ViewModel;
import hu.epam.worktime.mvvmworkdroid.common.widgets.recyclerview.ListItemViewModel;
import hu.epam.worktime.mvvmworkdroid.modules.main.model.MainModel;
import hu.epam.worktime.mvvmworkdroid.modules.main.router.MainRouter;
import hu.epam.worktime.mvvmworkdroid.modules.services.models.WorkTime;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * Created by Mihaly_Hunyady on 2016. 12. 02..
 */

public class MainViewModel extends BaseObservable implements ViewModel, MainModel.ModelCallback {

    private final MainRouter router;
    private final MainModel model;
    private List<WorkTime> workTimes;
    private boolean loading;
    @Bindable
    private List<ListItemViewModel> workTimeItemViewModels;
    private final static String TAG = "MainViewModel";

    public MainViewModel(MainModel model, MainRouter router) {
        this.model = model;
        this.router = router;
        init(model);
    }

    private void init(MainModel model) {
        Log.d(TAG, "elindult");
        model.setCallback(this);
        loadWorkTimes(model);
    }

    private void loadWorkTimes(MainModel model) {
        model.loadWorkTimes();
        setLoading(true);
    }

    @Override
    public void onStop() {
        model.setCallback(null);
    }

    @Override
    public void onStart() {
        model.setCallback(this);
    }

    @Override
    public void onWorkTimeLoaded(List<WorkTime> workTimes) {
        this.workTimes = workTimes;
        setWorkTimeItemViewModels(transformToItemViewModels(workTimes));
    }

    private List<ListItemViewModel> transformToItemViewModels(List<WorkTime> workTimes) {
        List<ListItemViewModel> itemViewModels = new ArrayList<>();
        for (WorkTime workTime : workTimes) {
            WorkTimeViewModel workTimeViewModel = new WorkTimeViewModel(router);
            workTimeViewModel.setWorkTime(workTime);
            itemViewModels.add(workTimeViewModel);
        }
        return itemViewModels;
    }

    private void setWorkTimeItemViewModels(List<ListItemViewModel> workTimeItemViewModels) {
        this.workTimeItemViewModels = workTimeItemViewModels;
        Log.d(TAG, workTimeItemViewModels.size() + "");
        notifyPropertyChanged(BR.workTimeItemViewModels);
    }

    @Override
    public void onWorkTimeLoadError() {
        setLoading(false);
    }

    @Override
    public void onWorkTimeLoadCompleted() {
        setLoading(false);
        onWorkTimeLoaded(model.getWorkTimes());
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
    }

    public boolean isLoading() {
        return loading;
    }

    public List<ListItemViewModel> getWorkTimeItemViewModels() {
        return workTimeItemViewModels;
    }

}
