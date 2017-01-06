package hu.epam.worktime.mvvmworkdroid.modules.save.model;

import hu.epam.worktime.mvvmworkdroid.modules.dal.WorkItemDao;
import hu.epam.worktime.mvvmworkdroid.modules.services.WorkServiceApi;

/**
 * Created by Mihaly_Hunyady on 2016. 12. 16..
 */

public class SaveModel {

    private final WorkItemDao workItemDao;
    private final WorkServiceApi workServiceApi;

    public SaveModel(WorkServiceApi workServiceApi, WorkItemDao workItemDao) {
        this.workServiceApi = workServiceApi;
        this.workItemDao = workItemDao;
    }
}
