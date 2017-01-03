package hu.epam.worktime.mvvmworkdroid.modules.main.stats.model;

import java.util.Collections;

import hu.epam.worktime.mvvmworkdroid.modules.dal.WorkItemDao;
import hu.epam.worktime.mvvmworkdroid.modules.services.models.WorkingStatistics;

/**
 * Created by Mihaly_Hunyady on 2016. 12. 02..
 */

public class MainStatsModel {
    private final WorkItemDao workItemDao;


    public MainStatsModel(WorkItemDao dao) {
        this.workItemDao = dao;
    }

    public WorkingStatistics loadWorkDays() {
        WorkingStatistics workingStatistics = workItemDao.find();
        Collections.sort(workingStatistics.getWorkTimes());
        return workingStatistics;
    }

}
