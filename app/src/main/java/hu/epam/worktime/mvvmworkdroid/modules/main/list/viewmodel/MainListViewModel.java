package hu.epam.worktime.mvvmworkdroid.modules.main.list.viewmodel;

import android.databinding.Bindable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import hu.epam.worktime.mvvmworkdroid.BR;
import hu.epam.worktime.mvvmworkdroid.common.widgets.recyclerview.ListItemViewModel;
import hu.epam.worktime.mvvmworkdroid.common.widgets.viewpager.ViewItemViewModel;
import hu.epam.worktime.mvvmworkdroid.modules.main.list.model.MainListModel;
import hu.epam.worktime.mvvmworkdroid.modules.main.main.router.MainRouter;
import hu.epam.worktime.mvvmworkdroid.modules.main.main.viewmodel.WorkTimeViewModel;
import hu.epam.worktime.mvvmworkdroid.modules.services.models.WorkTime;

/**
 * Created by Mihaly_Hunyady on 2016. 12. 12..
 */

public class MainListViewModel extends ViewItemViewModel {
    private final static String TAG = "MainListViewModel";
    private final MainListModel model;
    private final MainRouter router;
    @Bindable
    private List<ListItemViewModel> workTimeItemViewModels;

    public MainListViewModel(MainListModel model, MainRouter mainRouter) {
        this.model = model;
        this.router = mainRouter;
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

    public List<ListItemViewModel> getWorkTimeItemViewModels() {
        return workTimeItemViewModels;
    }

    public void setWorkTimeItemViewModels(List<ListItemViewModel> workTimeItemViewModels) {
        this.workTimeItemViewModels = workTimeItemViewModels;
        Log.d(TAG, workTimeItemViewModels.size() + "<- adatok beérkezve");
        notifyPropertyChanged(BR.workTimeItemViewModels);
    }

    @Override
    public void onRefresh() {
        setWorkTimeItemViewModels(transformToItemViewModels(model.loadWorkDays().getWorkTimes()));
    }
}
