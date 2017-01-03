package hu.epam.worktime.mvvmworkdroid.modules.main.stats.viewmodel;

import android.databinding.Bindable;

import org.threeten.bp.format.DateTimeFormatter;

import hu.epam.worktime.mvvmworkdroid.BR;
import hu.epam.worktime.mvvmworkdroid.common.mvvm.ViewModel;
import hu.epam.worktime.mvvmworkdroid.common.widgets.viewpager.ViewItemViewModel;
import hu.epam.worktime.mvvmworkdroid.modules.main.stats.model.MainStatsModel;
import hu.epam.worktime.mvvmworkdroid.modules.services.models.WorkingStatistics;

/**
 * Created by Mihaly_Hunyady on 2016. 12. 12..
 */

public class MainStatsViewModel extends ViewItemViewModel implements ViewModel {
    private WorkingStatistics workingStatistics;
    @Bindable
    private final MainStatsModel model;
    private final static String TAG = "MainStatsViewModel";

    public MainStatsViewModel(MainStatsModel model) {
        this.model = model;
    }

    @Override
    public void onStop() {

    }

    @Override
    public void onStart() {
        //model.getAdat();
        notifyPropertyChanged(BR.model);

    }

    public void setWorkingStatistics(WorkingStatistics workingStatistics) {
        this.workingStatistics = workingStatistics;
        model.setAvgWorkTime(workingStatistics.getAvgWorkTime().format(DateTimeFormatter.ISO_LOCAL_TIME));
        model.setDailyWorkTime(workingStatistics.getDailyWorkTime().format(DateTimeFormatter.ISO_LOCAL_TIME));
        model.setDaysToWork(String.valueOf(workingStatistics.getDaysToWork()));
        model.setMontlyWorkTime(workingStatistics.getMontlyWorkTime().format(DateTimeFormatter.ISO_LOCAL_TIME));
        model.setWorkTimeLeft(workingStatistics.getWorkTimeLeft());
        notifyPropertyChanged(BR.model);
    }

    public WorkingStatistics getWorkingStatistics() {
        return workingStatistics;
    }

    public MainStatsModel getModel() {
        return model;
    }
}
