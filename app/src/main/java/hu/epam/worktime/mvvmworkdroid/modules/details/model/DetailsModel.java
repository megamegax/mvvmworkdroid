package hu.epam.worktime.mvvmworkdroid.modules.details.model;

import org.threeten.bp.format.DateTimeFormatter;

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
        return selectedWorkTime.getArrive().format(DateTimeFormatter.ISO_LOCAL_TIME);
    }

    public String getLeaveTime() {
        return selectedWorkTime.getLeave().format(DateTimeFormatter.ISO_LOCAL_TIME);
    }

    public String getCurrentDate() {
        return selectedWorkTime.getDate().format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    public String getDinnerStartTime() {
        String dinnerStart = selectedWorkTime.getDinner().getFirst().format(DateTimeFormatter.ISO_LOCAL_TIME);
        if (dinnerStart.equals("00:00:00")) {
            return "12:00:00";
        } else {
            return dinnerStart;
        }
    }

    public String getDinnerEndTime() {
        String dinnerEnd = selectedWorkTime.getDinner().getSecond().format(DateTimeFormatter.ISO_LOCAL_TIME);
        if (dinnerEnd.equals("00:00:00")) {
            return "12:30:00";
        } else {
            return dinnerEnd;
        }
    }
}
