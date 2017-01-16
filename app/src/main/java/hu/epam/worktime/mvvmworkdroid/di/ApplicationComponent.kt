package hu.epam.worktime.mvvmworkdroid.di

import android.app.Application
import android.content.Context
import android.content.res.Resources
import dagger.Component
import hu.epam.worktime.mvvmworkdroid.WorkDroidApp
import hu.epam.worktime.mvvmworkdroid.di.qualifiers.ApplicationContext
import hu.epam.worktime.mvvmworkdroid.modules.services.WorkServiceApi

import javax.inject.Singleton

/**
 * Created by Mihaly_Hunyady on 2016. 11. 24..
 */

@Singleton
@Component(modules = arrayOf(ApplicationModule::class, ApiModule::class))
interface ApplicationComponent {

    fun inject(workDroidApp: WorkDroidApp)

    @ApplicationContext
    fun context(): Context

    fun application(): Application

    fun resources(): Resources

    fun workServiceApi(): WorkServiceApi

    /**
     * Inner injector class to avoid the boiler-plate dagger coding in injected class.
     */
    object Injector {
        lateinit var component: ApplicationComponent
            private set

        fun inject(application: WorkDroidApp) {
            component = DaggerApplicationComponent.builder()
                    .applicationModule(ApplicationModule(application))
                    .build()
            component.inject(application)
        }
    }
}
