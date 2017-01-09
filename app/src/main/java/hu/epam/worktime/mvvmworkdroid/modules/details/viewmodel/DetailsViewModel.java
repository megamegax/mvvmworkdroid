package hu.epam.worktime.mvvmworkdroid.modules.details.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import java.util.ArrayList;
import java.util.List;

import hu.epam.worktime.mvvmworkdroid.modules.details.model.DetailsModel;
import hu.epam.worktime.mvvmworkdroid.modules.details.router.DetailsRouter;
import hu.epam.worktime.mvvmworkdroid.modules.services.models.WorkTime;

/**
 * Created by Mihaly_Hunyady on 2017. 01. 06..
 */

public class DetailsViewModel extends BaseObservable {
    DetailsModel model;
    @Bindable
    private String arriveTime;
    @Bindable
    private String leaveTime;
    @Bindable
    private String currentDate;
    @Bindable
    private String dinnerStart;
    @Bindable
    private String dinnerEnd;
    private List<String> others = new ArrayList<>();

    public DetailsViewModel(DetailsModel model, DetailsRouter router) {
        this.model = model;
    }

    public void setSelectedWorkTime(WorkTime selectedWorkTime) {
        model.setSelectedWorkTime(selectedWorkTime);
        init();
    }

    private void init() {
        arriveTime = model.getArriveTime();
        leaveTime = model.getLeaveTime();
        currentDate = model.getCurrentDate();
        dinnerStart = model.getDinnerStartTime();
        dinnerEnd = model.getDinnerEndTime();
        notifyChange();
    }

    public String getArriveTime() {
        return arriveTime;
    }

       public String getLeaveTime() {
        return leaveTime;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public String getDinnerStart() {
        return dinnerStart;
    }

    public String getDinnerEnd() {
        return dinnerEnd;
    }

}
