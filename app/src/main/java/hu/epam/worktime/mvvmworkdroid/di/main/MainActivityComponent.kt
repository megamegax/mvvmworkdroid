package hu.epam.worktime.mvvmworkdroid.di.main

import android.content.Context
import android.content.res.Resources
import dagger.Component
import hu.epam.worktime.mvvmworkdroid.di.ActivityModule
import hu.epam.worktime.mvvmworkdroid.di.ApplicationComponent
import hu.epam.worktime.mvvmworkdroid.di.qualifiers.ApplicationContext
import hu.epam.worktime.mvvmworkdroid.di.scopes.PerActivity
import hu.epam.worktime.mvvmworkdroid.modules.main.main.router.MainActivity
import hu.epam.worktime.mvvmworkdroid.modules.main.main.viewmodel.MainViewModel
import hu.epam.worktime.mvvmworkdroid.modules.services.WorkServiceApi
import hu.epam.worktime.mvvmworkdroid.modules.services.worker.CalculatorService

/**


 * Created by Mihaly_Hunyady on 2016. 12. 01..
 */

@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(MainActivityModule::class, ActivityModule::class))
@PerActivity
interface MainActivityComponent {

    fun inject(mainActivity: MainActivity)

    fun resources(): Resources

    @ApplicationContext
    fun context(): Context

    fun calculatorService(): CalculatorService

    fun workServiceApi(): WorkServiceApi

    fun mainViewModel(): MainViewModel

    /**
     * Injector class for injecting the component into the activity.
     * The class help to avoid the boiler-plate dagger coding in injected class.
     */

    object Injector {
        lateinit var component: MainActivityComponent
            private set

        fun buildComponent(activity: MainActivity): MainActivityComponent {
            component = DaggerMainActivityComponent.builder()
                    .applicationComponent(ApplicationComponent.Injector.component)
                    .activityModule(ActivityModule(activity))
                    .mainActivityModule(MainActivityModule(activity))
                    .build()
            return component
        }

        fun setActivityComponent(activityComponent: MainActivityComponent) {
            Injector.component = activityComponent
        }
    }
}
