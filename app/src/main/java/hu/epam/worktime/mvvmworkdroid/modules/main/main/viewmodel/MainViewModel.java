package hu.epam.worktime.mvvmworkdroid.modules.main.main.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import hu.epam.worktime.mvvmworkdroid.common.widgets.viewpager.ViewItemViewModel;
import hu.epam.worktime.mvvmworkdroid.modules.main.main.model.MainModel;
import hu.epam.worktime.mvvmworkdroid.modules.main.main.router.MainRouter;
import hu.epam.worktime.mvvmworkdroid.modules.services.models.WorkingStatistics;

/**
 * Created by Mihaly_Hunyady on 2016. 12. 02..
 */

public class MainViewModel extends BaseObservable implements MainModel.ModelCallback {
    private final static String TAG = "MainViewModel";
    private final MainRouter router;
    private final MainModel model;
    @Bindable
    private final List<ViewItemViewModel> views = new ArrayList<>();
    private WorkingStatistics workTimes;
    private boolean loading;

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

    public void onStop() {
        model.setCallback(null);
    }

    public void onStart() {
        model.setCallback(this);
    }

    @Override
    public void onWorkTimeLoaded(WorkingStatistics workTimes) {
        this.workTimes = workTimes;
    }


    @Override
    public void onWorkTimeLoadError() {
        setLoading(false);
    }

    @Override
    public void onWorkTimeLoadCompleted() {
        setLoading(false);
        onWorkTimeLoaded(model.getWorkTimes());
        for (ViewItemViewModel view : views) {
            view.onRefresh();
        }
    }

    public boolean isLoading() {
        return loading;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
    }

    public List<ViewItemViewModel> getViews() {
        return views;
    }
}
