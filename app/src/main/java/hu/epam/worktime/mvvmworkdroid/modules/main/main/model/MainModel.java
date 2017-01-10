package hu.epam.worktime.mvvmworkdroid.modules.main.main.model;

import android.util.Log;

import java.util.List;

import hu.epam.worktime.mvvmworkdroid.modules.dal.WorkItemDao;
import hu.epam.worktime.mvvmworkdroid.modules.services.WorkServiceApi;
import hu.epam.worktime.mvvmworkdroid.modules.services.models.WorkDay;
import hu.epam.worktime.mvvmworkdroid.modules.services.models.WorkTime;
import hu.epam.worktime.mvvmworkdroid.modules.services.models.WorkingStatistics;
import hu.epam.worktime.mvvmworkdroid.modules.services.worker.CalculatorService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Mihaly_Hunyady on 2016. 12. 12..
 */

public class MainModel {
    private final CalculatorService calculatorService;
    private final WorkItemDao workItemDao;
    private final WorkServiceApi workServiceApi;
    private MainModel.ModelCallback callback;
    private WorkingStatistics workingStatistics;
    private List<WorkTime> workTimes;

    public MainModel(WorkServiceApi workServiceApi, CalculatorService calculatorService, WorkItemDao workItemDao) {
        this.workItemDao = workItemDao;
        this.workServiceApi = workServiceApi;
        this.calculatorService = calculatorService;
    }

    public void loadWorkTimes() {
        workServiceApi.workTimes(2)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<WorkTime>>() {
                    @Override
                    public void call(List<WorkTime> workTimes) {
                        onResult(workTimes);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        onError(throwable);
                    }
                }, new Action0() {
                    @Override
                    public void call() {
                        onWorkTimesCompleted();
                    }
                });
    }

    private void loadWorkDays() {
        workServiceApi.workDays()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<WorkDay>>() {
                    @Override
                    public void call(List<WorkDay> workDays) {
                        onWorkDayResult(workDays);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        onError(throwable);
                    }
                }, new Action0() {
                    @Override
                    public void call() {
                        onCompleted();
                    }
                });
    }

    private void onWorkDayResult(List<WorkDay> workDays) {
        calculatorService.setWorkDays(workDays);
        this.workingStatistics = calculatorService.calculateStuffs(workTimes);
        workItemDao.saveWorkingStatistics(workingStatistics);
    }

    private void onResult(List<WorkTime> workTimes) {
        this.workTimes = workTimes;
        loadWorkDays();
    }

    private void onError(Throwable throwable) {
        Log.d("WorkDroid", "hiba van:" + throwable.getMessage());
        if (callback != null) {
            callback.onWorkTimeLoadError();
        }
    }

    private void onWorkTimesCompleted() {
        loadWorkDays();
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
