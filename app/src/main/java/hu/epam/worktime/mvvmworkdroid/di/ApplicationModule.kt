package hu.epam.worktime.mvvmworkdroid.di

import android.app.Application
import android.content.Context
import android.content.res.Resources
import dagger.Module
import dagger.Provides
import hu.epam.worktime.mvvmworkdroid.di.qualifiers.ApplicationContext

import javax.inject.Singleton

/**
 * Created by Mihaly_Hunyady on 2016. 11. 24..
 */

@Module
@Singleton
open class ApplicationModule(private val application: Application) {

    @Provides
    @ApplicationContext
    @Singleton
    fun provideContext(): Context {
        return application
    }

    @Provides
    @Singleton
    fun provideApplication(): Application {
        return application
    }


    @Provides
    @Singleton
    fun provideResources(): Resources {
        return application.resources
    }
}
