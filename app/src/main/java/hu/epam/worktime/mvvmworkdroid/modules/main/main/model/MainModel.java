package hu.epam.worktime.mvvmworkdroid.modules.main.main.model;

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

import java.util.List;

/**
 *
 *
 * Created by Mihaly_Hunyady on 2016. 12. 12..
 */

public class MainModel implements Model {
    private final CalculatorService calculatorService;
    private MainModel.ModelCallback callback;
    private final WorkServiceApi workServiceApi;
    private WorkingStatistics workingStatistics;
    private List<WorkTime> workTimes;


    public MainModel(WorkServiceApi workServiceApi, CalculatorService calculatorService) {
        this.workServiceApi = workServiceApi;
        this.calculatorService = calculatorService;
    }

    public void loadWorkTimes() {
        workServiceApi.workTimes(2).enqueue(new Callback<List<WorkTime>>() {
            @Override
            public void onResponse(Call<List<WorkTime>> call, Response<List<WorkTime>> response) {
                onResult(response);
            }

            @Override
            public void onFailure(Call<List<WorkTime>> call, Throwable t) {
                onError(t);
            }
        });
    }

    private void loadWorkDays() {
        workServiceApi.workDays().enqueue(new Callback<List<WorkDay>>() {
            @Override
            public void onResponse(Call<List<WorkDay>> call, Response<List<WorkDay>> response) {
                onWorkDayResult(response);
            }

            @Override
            public void onFailure(Call<List<WorkDay>> call, Throwable t) {
                onError(t);
            }
        });
    }

    private void onWorkDayResult(Response<List<WorkDay>> workDays) {
        calculatorService.setWorkDays(workDays.body());
        this.workingStatistics = calculatorService.calculateStuffs(workTimes);

        onCompleted();
    }

    private void onResult(Response<List<WorkTime>> workTimes) {
        this.workTimes = workTimes.body();
        loadWorkDays();
    }

    private void onError(Throwable throwable) {
        Log.d("WorkDroid", "hiba van:" + throwable.getMessage());
    }

    private void onCompleted() {
        if (callback != null) {
            callback.onWorkTimeLoadCompleted();
        }
    }

    public void setCallback(ModelCallback callback) {
        this.callback = callback;
        if (this.callback != null && workingStatistics != null) {
            this.callback.onWorkTimeLoaded(workingStatistics);
        }
    }

    public WorkingStatistics getWorkTimes() {
        return workingStatistics;
    }

    public interface ModelCallback {
        void onWorkTimeLoaded(WorkingStatistics workTimes);

        void onWorkTimeLoadError();

        void onWorkTimeLoadCompleted();
    }
}
