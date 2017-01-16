package hu.epam.worktime.mvvmworkdroid.di.save

import android.content.Context
import android.content.res.Resources

import dagger.Component
import hu.epam.worktime.mvvmworkdroid.di.ActivityModule
import hu.epam.worktime.mvvmworkdroid.di.ApplicationComponent

import hu.epam.worktime.mvvmworkdroid.di.qualifiers.ApplicationContext
import hu.epam.worktime.mvvmworkdroid.di.scopes.PerActivity
import hu.epam.worktime.mvvmworkdroid.modules.save.router.SaveActivity
import hu.epam.worktime.mvvmworkdroid.modules.services.WorkServiceApi

/**
 * Created by Mihaly_Hunyady on 2017. 01. 06..
 */

@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(SaveActivityModule::class, ActivityModule::class))
@PerActivity
interface SaveActivityComponent {

    fun inject(saveActivity: SaveActivity)

    fun resources(): Resources

    @ApplicationContext
    fun context(): Context

    fun workServiceApi(): WorkServiceApi

    /**
     * Injector class for injecting the component into the activity.
     * The class help to avoid the boiler-plate dagger coding in injected class.
     */

    object Injector {
        lateinit var component: SaveActivityComponent
            private set

        fun buildComponent(activity: SaveActivity): SaveActivityComponent {
            component = DaggerSaveActivityComponent.builder()
                    .applicationComponent(ApplicationComponent.Injector.component)
                    .activityModule(ActivityModule(activity))
                    .saveActivityModule(SaveActivityModule(activity))
                    .build()
            return component
        }

        fun setActivityComponent(activityComponent: SaveActivityComponent) {
            SaveActivityComponent.Injector.component = activityComponent
        }
    }
}
