package hu.epam.worktime.mvvmworkdroid.di.main

import com.google.gson.Gson

import dagger.Module
import dagger.Provides
import hu.epam.worktime.mvvmworkdroid.di.scopes.PerActivity
import hu.epam.worktime.mvvmworkdroid.modules.dal.WorkItemDao
import hu.epam.worktime.mvvmworkdroid.modules.dal.WorkItemDaoImpl
import hu.epam.worktime.mvvmworkdroid.modules.main.list.model.MainListModel
import hu.epam.worktime.mvvmworkdroid.modules.main.list.viewmodel.MainListViewModel
import hu.epam.worktime.mvvmworkdroid.modules.main.main.model.MainModel
import hu.epam.worktime.mvvmworkdroid.modules.main.main.router.MainActivity
import hu.epam.worktime.mvvmworkdroid.modules.main.main.router.MainRouter
import hu.epam.worktime.mvvmworkdroid.modules.main.main.viewmodel.MainViewModel
import hu.epam.worktime.mvvmworkdroid.modules.main.stats.model.MainStatsModel
import hu.epam.worktime.mvvmworkdroid.modules.main.stats.viewmodel.MainStatsViewModel
import hu.epam.worktime.mvvmworkdroid.modules.services.WorkServiceApi
import hu.epam.worktime.mvvmworkdroid.modules.services.worker.CalculatorService

/**
 * Created by Mihaly_Hunyady on 2016. 12. 01..
 */
@Module
open class MainActivityModule(private val activity: MainActivity) {

    @Provides
    fun providesMainModel(workServiceApi: WorkServiceApi, calculatorService: CalculatorService, dao: WorkItemDao): MainModel {
        return MainModel(workServiceApi, calculatorService, dao)
    }

    @Provides
    fun providesMainViewModel(model: MainModel, mainRouter: MainRouter, mainListViewModel: MainListViewModel, mainStatsViewModel: MainStatsViewModel): MainViewModel {
        val mainViewModel = MainViewModel(model, mainRouter)
        mainViewModel.addView(mainStatsViewModel)
        mainViewModel.addView(mainListViewModel)
        return mainViewModel
    }

    @Provides
    fun providesDao(): WorkItemDao {
        return WorkItemDaoImpl()
    }

    @Provides
    @PerActivity
    fun providesMainListModel(dao: WorkItemDao): MainListModel {
        return MainListModel(dao)
    }

    @Provides
    @PerActivity
    fun providesMainListViewModel(model: MainListModel, mainRouter: MainRouter): MainListViewModel {
        return MainListViewModel(model, mainRouter)
    }

    @Provides
    @PerActivity
    fun providesMainStatsModel(dao: WorkItemDao): MainStatsModel {
        return MainStatsModel(dao)
    }

    @Provides
    @PerActivity
    fun providesMainStatsViewModel(model: MainStatsModel): MainStatsViewModel {
        return MainStatsViewModel(model)
    }

    @Provides
    fun provideMainRouter(): MainRouter {
        return activity
    }

    @Provides
    fun provideCalculatorService(): CalculatorService {
        return CalculatorService()
    }

    @Provides
    fun providesGson(): Gson {
        return Gson()
    }
}
