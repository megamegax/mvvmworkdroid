package hu.epam.worktime.mvvmworkdroid.modules.details.model;

import hu.epam.worktime.mvvmworkdroid.modules.dal.WorkItemDao;
import hu.epam.worktime.mvvmworkdroid.modules.services.models.WorkTime;

/**
 * Created by Mihaly_Hunyady on 2017. 01. 06..
 */

public class DetailsModel {
    private WorkTime selectedWorkTime;

    public DetailsModel(WorkItemDao dao) {

    }

    public void setSelectedWorkTime(WorkTime selectedWorkTime) {
        this.selectedWorkTime = selectedWorkTime;
    }

    public String getArriveTime() {
        return "00:00:00";
    }

    public String getLeaveTime() {
        return "00:00:00";
    }

    public String getCurrentDate() {
        return "2000.10.10.";
    }

    public String getDinnerStartTime() {
        return "00:00:00";
    }

    public String getDinnerEndTime() {
        return "00:00:00";
    }
}
