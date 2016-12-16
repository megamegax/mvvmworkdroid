package hu.epam.worktime.mvvmworkdroid.modules.main.list.model;

import android.util.Log;
import hu.epam.worktime.mvvmworkdroid.common.mvvm.Model;
import hu.epam.worktime.mvvmworkdroid.modules.services.WorkServiceApi;
import hu.epam.worktime.mvvmworkdroid.modules.services.models.WorkDay;
import hu.epam.worktime.mvvmworkdroid.modules.services.models.WorkTime;
import hu.epam.worktime.mvvmworkdroid.modules.services.models.WorkingStatistics;
import hu.epam.worktime.mvvmworkdroid.modules.services.worker.CalculatorService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * Created by Mihaly_Hunyady on 2016. 12. 02..
 */

public class MainListModel implements Model {
    private List<WorkTime> workTimes;

    public List<WorkTime> getWorkTimes() {
        return workTimes;
    }

    public void setWorkTimes(List<WorkTime> workTimes) {
        this.workTimes = workTimes;
    }

}
