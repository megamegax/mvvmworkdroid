package hu.epam.worktime.mvvmworkdroid.di.details

import android.content.Context
import android.content.res.Resources

import dagger.Component
import hu.epam.worktime.mvvmworkdroid.di.ActivityModule
import hu.epam.worktime.mvvmworkdroid.di.ApplicationComponent
import hu.epam.worktime.mvvmworkdroid.di.qualifiers.ApplicationContext
import hu.epam.worktime.mvvmworkdroid.di.scopes.PerActivity
import hu.epam.worktime.mvvmworkdroid.modules.details.router.DetailsActivity

/**
 * Created by Mihaly_Hunyady on 2017. 01. 06..
 */

@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(DetailsActivityModule::class, ActivityModule::class))
@PerActivity
interface DetailsActivityComponent {

    fun inject(saveActivity: DetailsActivity)

    fun resources(): Resources

    @ApplicationContext
    fun context(): Context

    /**
     * Injector class for injecting the component into the activity.
     * The class help to avoid the boiler-plate dagger coding in injected class.
     */

    companion object {
        lateinit var component: DetailsActivityComponent
            private set

        fun buildComponent(activity: DetailsActivity): DetailsActivityComponent {
            component = DaggerDetailsActivityComponent.builder()
                    .applicationComponent(ApplicationComponent.component)
                    .activityModule(ActivityModule(activity))
                    .detailsActivityModule(DetailsActivityModule(activity))
                    .build()
            return component
        }

        fun setActivityComponent(activityComponent: DetailsActivityComponent) {
            DetailsActivityComponent.component = activityComponent
        }
    }
}
