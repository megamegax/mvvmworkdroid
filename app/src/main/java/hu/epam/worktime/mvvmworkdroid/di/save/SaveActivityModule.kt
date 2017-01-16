package hu.epam.worktime.mvvmworkdroid.di.save

import android.app.FragmentManager
import android.content.Context

import dagger.Module
import dagger.Provides
import hu.epam.worktime.mvvmworkdroid.modules.dal.WorkItemDao
import hu.epam.worktime.mvvmworkdroid.modules.dal.WorkItemDaoImpl
import hu.epam.worktime.mvvmworkdroid.modules.save.model.SaveModel
import hu.epam.worktime.mvvmworkdroid.modules.save.router.SaveActivity
import hu.epam.worktime.mvvmworkdroid.modules.save.router.SaveRouter
import hu.epam.worktime.mvvmworkdroid.modules.save.viewmodel.SaveViewModel
import hu.epam.worktime.mvvmworkdroid.modules.services.WorkServiceApi

/**
 * Created by Mihaly_Hunyady on 2017. 01. 06..
 */

@Module
class SaveActivityModule(private val activity: SaveActivity) {

    @Provides
    internal fun providesSaveModel(workServiceApi: WorkServiceApi, dao: WorkItemDao): SaveModel {
        return SaveModel(workServiceApi, dao)
    }

    @Provides
    internal fun providesSaveViewModel(model: SaveModel, router: SaveRouter, context: Context, fragmentManager: FragmentManager): SaveViewModel {
        return SaveViewModel(model, router, context, fragmentManager)
    }

    @Provides
    internal fun providesFragmentManager(): FragmentManager {
        return activity.fragmentManager
    }

    @Provides
    internal fun providesDao(): WorkItemDao {
        return WorkItemDaoImpl()
    }

    @Provides
    internal fun provideSaveRouter(): SaveRouter {
        return activity
    }

    @Provides
    internal fun providesContext(): Context {
        return activity
    }

}
