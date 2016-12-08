package hu.epam.worktime.mvvmworkdroid.di;

import dagger.Module;
import dagger.Provides;
import hu.epam.worktime.mvvmworkdroid.modules.main.model.MainModel;
import hu.epam.worktime.mvvmworkdroid.modules.main.router.MainActivity;
import hu.epam.worktime.mvvmworkdroid.modules.main.router.MainRouter;
import hu.epam.worktime.mvvmworkdroid.modules.main.viewmodel.MainViewModel;
import hu.epam.worktime.mvvmworkdroid.modules.services.WorkServiceApi;

/**
 *
 *
 * Created by Mihaly_Hunyady on 2016. 12. 01..
 */
@Module
public class MainActivityModule {

    private final MainActivity activity;

    public MainActivityModule(MainActivity activity) {
        this.activity = activity;
    }

    @Provides
    MainModel providesMainModel(WorkServiceApi workServiceApi) {
        return new MainModel(workServiceApi);
    }

    @Provides
    MainViewModel providesMainViewModel(MainModel model, MainRouter mainRouter) {
        return new MainViewModel(model, mainRouter);
    }

    @Provides
    MainRouter provideMainRouter() {
        return activity;
    }

}
