package hu.epam.worktime.mvvmworkdroid.modules.main.viewmodel;

import android.databinding.BaseObservable;
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

public class MainViewModel extends BaseObservable implements MainModel.ModelCallback {

    private final MainRouter router;
    private final MainModel model;
    private List<WorkTime> workTimes;
    private boolean loading;
    private List<ListItemViewModel> workTimeItemViewModels;

    public MainViewModel(MainModel model, MainRouter router) {
        this.model = model;
        this.router = router;

        initMainModel(model);
    }

    private void initMainModel(MainModel model) {
        model.setCallback(this);
        if (workTimes == null || workTimes.isEmpty()) {
            onWorkTimeLoaded(new ArrayList<WorkTime>());
        }
        loadWorkTimes(model);
    }

    private void loadWorkTimes(MainModel model) {
        model.loadWorkTimes();
        setLoading(true);
    }

    @Override
    public void onWorkTimeLoaded(List<WorkTime> workTimes) {
        setWorkTimeItemViewModels(transformToItemViewModels(workTimes));
    }

    private List<ListItemViewModel> transformToItemViewModels(List<WorkTime> workTimes) {
        return null;
    }

    private void setWorkTimeItemViewModels(List<ListItemViewModel> workTimeItemViewModels) {
        this.workTimeItemViewModels = workTimeItemViewModels;
        notifyPropertyChanged(BR.viewModel);
    }

    @Override
    public void onWorkTimeLoadError() {
        setLoading(false);
    }

    @Override
    public void onWorkTimeLoadCompleted() {
        setLoading(false);
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
