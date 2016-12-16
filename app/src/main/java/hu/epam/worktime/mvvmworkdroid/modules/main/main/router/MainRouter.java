package hu.epam.worktime.mvvmworkdroid.modules.main.main.router;

import hu.epam.worktime.mvvmworkdroid.modules.services.models.WorkTime;

/**
 *
 *
 * Created by Mihaly_Hunyady on 2016. 12. 02..
 */

public interface MainRouter {
    void openDetails(WorkTime workTime);

    void openNewEntry();
}
