package hu.epam.worktime.mvvmworkdroid.modules.main.main.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;

import hu.epam.worktime.mvvmworkdroid.common.mvvm.ViewModel;
import hu.epam.worktime.mvvmworkdroid.common.widgets.recyclerview.ListItemViewModel;
import hu.epam.worktime.mvvmworkdroid.common.widgets.viewpager.ViewItemViewModel;
import hu.epam.worktime.mvvmworkdroid.modules.main.list.viewmodel.MainListViewModel;
import hu.epam.worktime.mvvmworkdroid.modules.main.main.model.MainModel;
import hu.epam.worktime.mvvmworkdroid.modules.main.main.router.MainActivity;
import hu.epam.worktime.mvvmworkdroid.modules.main.main.router.MainRouter;
import hu.epam.worktime.mvvmworkdroid.modules.main.stats.viewmodel.MainStatsViewModel;
import hu.epam.worktime.mvvmworkdroid.modules.services.models.WorkTime;
import hu.epam.worktime.mvvmworkdroid.modules.services.models.WorkingStatistics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Mihaly_Hunyady on 2016. 12. 02..
 */

public class MainViewModel extends BaseObservable implements ViewModel, MainModel.ModelCallback {
    private final MainRouter router;
    private final MainModel model;
    private WorkingStatistics workTimes;
    private boolean loading;
    @Bindable
    private final List<ViewItemViewModel> views = new ArrayList<>();

    private final static String TAG = "MainViewModel";

    public MainViewModel(MainModel model, MainRouter router) {
        this.model = model;
        this.router = router;
        init(model);
    }

    public void addView(ViewItemViewModel viewModel) {
        views.add(viewModel);
    }

    private void init(MainModel model) {
        Log.d(TAG, "elindult");
        model.setCallback(this);
        refresh();
    }

    public void refresh() {
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
    public void onWorkTimeLoaded(WorkingStatistics workTimes) {
        this.workTimes = workTimes;
        Collections.sort(this.workTimes.getWorkTimes());
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

    public List<ViewItemViewModel> getViews() {
        return views;
    }
}
