package hu.epam.worktime.mvvmworkdroid.di

import android.app.Activity
import android.content.Context
import dagger.Module
import dagger.Provides
import hu.epam.worktime.mvvmworkdroid.di.qualifiers.ActivityContext

/**


 * Created by Mihaly_Hunyady on 2016. 12. 01..
 */
@Module
open class ActivityModule(private val activity: Activity) {

    @Provides
    @ActivityContext
    fun provideContext(): Context {
        return activity
    }

}
