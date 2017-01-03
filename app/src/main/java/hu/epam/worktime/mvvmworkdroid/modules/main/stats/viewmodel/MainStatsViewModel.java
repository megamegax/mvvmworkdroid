package hu.epam.worktime.mvvmworkdroid.modules.main.stats.viewmodel;

import android.databinding.Bindable;

import org.threeten.bp.format.DateTimeFormatter;

import hu.epam.worktime.mvvmworkdroid.BR;
import hu.epam.worktime.mvvmworkdroid.common.widgets.viewpager.ViewItemViewModel;
import hu.epam.worktime.mvvmworkdroid.modules.main.stats.model.MainStatsModel;
import hu.epam.worktime.mvvmworkdroid.modules.services.models.WorkingStatistics;

/**
 * Created by Mihaly_Hunyady on 2016. 12. 12..
 */

public class MainStatsViewModel extends ViewItemViewModel {
    private final static String TAG = "MainStatsViewModel";
    private final MainStatsModel model;
    @Bindable
    private String dailyWorkTime;
    @Bindable
    private String montlyWorkTime;
    @Bindable
    private String daysToWork;
    @Bindable
    private String workTimeLeft;
    @Bindable
    private String avgWorkTime;

    public MainStatsViewModel(MainStatsModel model) {
        this.model = model;
    }

    public void setWorkingStatistics(WorkingStatistics workingStatistics) {
        setAvgWorkTime(workingStatistics.getAvgWorkTime().format(DateTimeFormatter.ISO_LOCAL_TIME));
        setDailyWorkTime(workingStatistics.getDailyWorkTime().format(DateTimeFormatter.ISO_LOCAL_TIME));
        setDaysToWork(String.valueOf(workingStatistics.getDaysToWork()));
        setMontlyWorkTime(workingStatistics.getMontlyWorkTime().format(DateTimeFormatter.ISO_LOCAL_TIME));
        setWorkTimeLeft(workingStatistics.getWorkTimeLeft());
        notifyPropertyChanged(BR.viewModel);
    }

    public MainStatsModel getModel() {
        return model;
    }

    @Override
    public void onRefresh() {
        setWorkingStatistics(model.loadWorkDays());
    }


    public String getDailyWorkTime() {
        return dailyWorkTime;
    }

    public void setDailyWorkTime(String dailyWorkTime) {
        this.dailyWorkTime = dailyWorkTime;
    }

    public String getMontlyWorkTime() {
        return montlyWorkTime;
    }

    public void setMontlyWorkTime(String montlyWorkTime) {
        this.montlyWorkTime = montlyWorkTime;
    }

    public String getDaysToWork() {
        return daysToWork;
    }

    public void setDaysToWork(String daysToWork) {
        this.daysToWork = daysToWork;
    }

    public String getWorkTimeLeft() {
        return workTimeLeft;
    }

    public void setWorkTimeLeft(String workTimeLeft) {
        this.workTimeLeft = workTimeLeft;
    }

    public String getAvgWorkTime() {
        return avgWorkTime;
    }

    public void setAvgWorkTime(String avgWorkTime) {
        this.avgWorkTime = avgWorkTime;
    }
}
