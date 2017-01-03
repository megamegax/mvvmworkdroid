package hu.epam.worktime.mvvmworkdroid.di.main;

import dagger.Module;
import dagger.Provides;
import hu.epam.worktime.mvvmworkdroid.di.scopes.PerActivity;
import hu.epam.worktime.mvvmworkdroid.modules.dal.WorkItemDao;
import hu.epam.worktime.mvvmworkdroid.modules.dal.WorkItemDaoImpl;
import hu.epam.worktime.mvvmworkdroid.modules.main.list.model.MainListModel;
import hu.epam.worktime.mvvmworkdroid.modules.main.list.viewmodel.MainListViewModel;
import hu.epam.worktime.mvvmworkdroid.modules.main.main.model.MainModel;
import hu.epam.worktime.mvvmworkdroid.modules.main.main.router.MainActivity;
import hu.epam.worktime.mvvmworkdroid.modules.main.main.router.MainRouter;
import hu.epam.worktime.mvvmworkdroid.modules.main.main.viewmodel.MainViewModel;
import hu.epam.worktime.mvvmworkdroid.modules.main.stats.model.MainStatsModel;
import hu.epam.worktime.mvvmworkdroid.modules.main.stats.viewmodel.MainStatsViewModel;
import hu.epam.worktime.mvvmworkdroid.modules.services.WorkServiceApi;
import hu.epam.worktime.mvvmworkdroid.modules.services.worker.CalculatorService;

/**
 * Created by Mihaly_Hunyady on 2016. 12. 01..
 */
@Module
public class MainActivityModule {

    private final MainActivity activity;

    public MainActivityModule(MainActivity activity) {
        this.activity = activity;
    }

    @Provides
    MainModel providesMainModel(WorkServiceApi workServiceApi, CalculatorService calculatorService, WorkItemDao dao) {
        return new MainModel(workServiceApi, calculatorService, dao);
    }

    @Provides
    MainViewModel providesMainViewModel(MainModel model, MainRouter mainRouter, MainListViewModel mainListViewModel, MainStatsViewModel mainStatsViewModel) {
        MainViewModel mainViewModel = new MainViewModel(model, mainRouter);
        mainViewModel.addView(mainListViewModel);
        mainViewModel.addView(mainStatsViewModel);
        return mainViewModel;
    }

    @Provides
    WorkItemDao providesDao() {
        return new WorkItemDaoImpl();
    }

    @Provides
    @PerActivity
    MainListModel providesMainListModel(WorkItemDao dao) {
        return new MainListModel(dao);
    }

    @Provides
    @PerActivity
    MainListViewModel providesMainListViewModel(MainListModel model, MainRouter mainRouter) {
        return new MainListViewModel(model, mainRouter);
    }

    @Provides
    @PerActivity
    MainStatsModel providesMainStatsModel(WorkItemDao dao) {
        return new MainStatsModel(dao);
    }

    @Provides
    @PerActivity
    MainStatsViewModel providesMainStatsViewModel(MainStatsModel model) {
        return new MainStatsViewModel(model);
    }

    @Provides
    MainRouter provideMainRouter() {
        return activity;
    }

    @Provides
    CalculatorService provideCalculatorService() {
        return new CalculatorService();
    }
}
