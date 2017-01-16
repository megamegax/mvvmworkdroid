package hu.epam.worktime.mvvmworkdroid.di.details

import android.content.Context

import com.google.gson.Gson

import dagger.Module
import dagger.Provides
import hu.epam.worktime.mvvmworkdroid.modules.dal.WorkItemDao
import hu.epam.worktime.mvvmworkdroid.modules.dal.WorkItemDaoImpl
import hu.epam.worktime.mvvmworkdroid.modules.details.model.DetailsModel
import hu.epam.worktime.mvvmworkdroid.modules.details.router.DetailsActivity
import hu.epam.worktime.mvvmworkdroid.modules.details.router.DetailsRouter
import hu.epam.worktime.mvvmworkdroid.modules.details.viewmodel.DetailsViewModel

/**
 * Created by Mihaly_Hunyady on 2017. 01. 06..
 */

@Module
open class DetailsActivityModule(private val activity: DetailsActivity) {

    @Provides
    fun providesDetailsModel(dao: WorkItemDao): DetailsModel {
        return DetailsModel(dao)
    }

    @Provides
    fun providesDetailsViewModel(model: DetailsModel, router: DetailsRouter): DetailsViewModel {
        return DetailsViewModel(model, router)
    }

    @Provides
    fun providesDao(): WorkItemDao {
        return WorkItemDaoImpl()
    }

    @Provides
    fun provideDetailsRouter(): DetailsRouter {
        return activity
    }

    @Provides
    fun providesContext(): Context {
        return activity
    }

    @Provides
    fun providesGson(): Gson {
        return Gson()
    }
}
