package hu.epam.worktime.mvvmworkdroid.modules.main.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;
import hu.epam.worktime.mvvmworkdroid.BR;
import hu.epam.worktime.mvvmworkdroid.common.mvvm.ViewModel;
import hu.epam.worktime.mvvmworkdroid.common.widgets.recyclerview.ListItemViewModel;
import hu.epam.worktime.mvvmworkdroid.modules.main.model.MainListModel;
import hu.epam.worktime.mvvmworkdroid.modules.main.router.MainRouter;
import hu.epam.worktime.mvvmworkdroid.modules.services.models.WorkTime;
import hu.epam.worktime.mvvmworkdroid.modules.services.models.WorkingStatistics;

import java.util.ArrayList;
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

        setWorkTimeItemViewModels(transformToItemViewModels(model.getWorkTimes()));

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
    public void onStop() {

    }

    @Override
    public void onStart() {

    }

    private void setWorkTimeItemViewModels(List<ListItemViewModel> workTimeItemViewModels) {
        this.workTimeItemViewModels = workTimeItemViewModels;
        Log.d(TAG, workTimeItemViewModels.size() + "");
        notifyPropertyChanged(BR.workTimeItemViewModels);
    }

    public List<ListItemViewModel> getWorkTimeItemViewModels() {
        return workTimeItemViewModels;
    }

}
