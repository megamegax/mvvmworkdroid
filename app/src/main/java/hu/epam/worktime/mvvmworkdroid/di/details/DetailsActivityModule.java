package hu.epam.worktime.mvvmworkdroid.di.details;

import android.content.Context;

import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;
import hu.epam.worktime.mvvmworkdroid.modules.dal.WorkItemDao;
import hu.epam.worktime.mvvmworkdroid.modules.dal.WorkItemDaoImpl;
import hu.epam.worktime.mvvmworkdroid.modules.details.model.DetailsModel;
import hu.epam.worktime.mvvmworkdroid.modules.details.router.DetailsActivity;
import hu.epam.worktime.mvvmworkdroid.modules.details.router.DetailsRouter;
import hu.epam.worktime.mvvmworkdroid.modules.details.viewmodel.DetailsViewModel;

/**
 * Created by Mihaly_Hunyady on 2017. 01. 06..
 */

@Module
public class DetailsActivityModule {

    private final DetailsActivity activity;

    public DetailsActivityModule(DetailsActivity activity) {
        this.activity = activity;
    }

    @Provides
    DetailsModel providesDetailsModel(WorkItemDao dao) {
        return new DetailsModel(dao);
    }

    @Provides
    DetailsViewModel providesDetailsViewModel(DetailsModel model, DetailsRouter router) {
        return new DetailsViewModel(model, router);
    }

    @Provides
    WorkItemDao providesDao() {
        return new WorkItemDaoImpl();
    }

    @Provides
    DetailsRouter provideDetailsRouter() {
        return activity;
    }

    @Provides
    Context providesContext() {
        return activity;
    }

    @Provides
    Gson providesGson() {
        return new Gson();
    }
}
