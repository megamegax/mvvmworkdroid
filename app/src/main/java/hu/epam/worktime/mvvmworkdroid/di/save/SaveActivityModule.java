package hu.epam.worktime.mvvmworkdroid.di.save;

import dagger.Module;
import dagger.Provides;
import hu.epam.worktime.mvvmworkdroid.modules.dal.WorkItemDao;
import hu.epam.worktime.mvvmworkdroid.modules.dal.WorkItemDaoImpl;
import hu.epam.worktime.mvvmworkdroid.modules.save.model.SaveModel;
import hu.epam.worktime.mvvmworkdroid.modules.save.router.SaveActivity;
import hu.epam.worktime.mvvmworkdroid.modules.save.router.SaveRouter;
import hu.epam.worktime.mvvmworkdroid.modules.save.viewmodel.SaveViewModel;
import hu.epam.worktime.mvvmworkdroid.modules.services.WorkServiceApi;

/**
 * Created by Mihaly_Hunyady on 2017. 01. 06..
 */

@Module
public class SaveActivityModule {

    private final SaveActivity activity;

    public SaveActivityModule(SaveActivity activity) {
        this.activity = activity;
    }

    @Provides
    SaveModel providesSaveModel(WorkServiceApi workServiceApi, WorkItemDao dao) {
        return new SaveModel(workServiceApi, dao);
    }

    @Provides
    SaveViewModel providesSaveViewModel(SaveModel model, SaveRouter router) {
        return new SaveViewModel(model, router);
    }

    @Provides
    WorkItemDao providesDao() {
        return new WorkItemDaoImpl();
    }

    @Provides
    SaveRouter provideSaveRouter() {
        return activity;
    }

}
