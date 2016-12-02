package hu.epam.worktime.mvvmworkdroid.modules.main.model;

import android.util.Log;
import hu.epam.worktime.mvvmworkdroid.modules.services.models.WorkTime;
import hu.hanprog.worktime.service.WorkServiceApi;
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

public class MainModel {
    private ModelCallback callback;
    private final WorkServiceApi workServiceApi;
    private List<WorkTime> workTimes = new ArrayList<>();

    public MainModel(WorkServiceApi workServiceApi) {
        this.workServiceApi = workServiceApi;
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

    private void onResult(Response<List<WorkTime>> workTimes) {
        Log.d("WorkDroid", "ok");
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
        if (this.callback != null && !workTimes.isEmpty()) {
            this.callback.onWorkTimeLoaded(workTimes);
        }
    }

    public List<WorkTime> getWorkTimes() {
        return workTimes;
    }

    public interface ModelCallback {
        void onWorkTimeLoaded(List<WorkTime> workTimes);

        void onWorkTimeLoadError();

        void onWorkTimeLoadCompleted();
    }
}
