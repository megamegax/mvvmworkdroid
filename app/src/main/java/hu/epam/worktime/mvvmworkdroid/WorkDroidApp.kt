package hu.epam.worktime.mvvmworkdroid

import android.app.Application

import com.jakewharton.threetenabp.AndroidThreeTen

import hu.epam.worktime.mvvmworkdroid.di.ApplicationComponent
import io.realm.Realm

/**
 * Created by Mihaly_Hunyady on 2016. 11. 24..
 */

class WorkDroidApp : Application() {

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        Realm.init(this)
        ApplicationComponent.inject(this)
    }

}
