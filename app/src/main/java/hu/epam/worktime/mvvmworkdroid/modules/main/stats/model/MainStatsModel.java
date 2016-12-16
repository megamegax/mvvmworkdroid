package hu.epam.worktime.mvvmworkdroid.modules.main.stats.model;

import java.util.List;

import hu.epam.worktime.mvvmworkdroid.common.mvvm.Model;
import hu.epam.worktime.mvvmworkdroid.modules.services.models.WorkTime;
import hu.epam.worktime.mvvmworkdroid.modules.services.models.WorkingStatistics;

/**
 *
 *
 * Created by Mihaly_Hunyady on 2016. 12. 02..
 */

public class MainStatsModel implements Model {
    private String dailyWorkTime;
    private String montlyWorkTime;
    private String daysToWork;
    private String workTimeLeft;
    private String avgWorkTime;

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
