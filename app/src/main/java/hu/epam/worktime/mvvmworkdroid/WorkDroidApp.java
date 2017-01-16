package hu.epam.worktime.mvvmworkdroid;

import android.app.Application;

import com.jakewharton.threetenabp.AndroidThreeTen;

import hu.epam.worktime.mvvmworkdroid.di.ApplicationComponent;
import io.realm.Realm;

/**
 * Created by Mihaly_Hunyady on 2016. 11. 24..
 */

public class WorkDroidApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AndroidThreeTen.init(this);
        Realm.init(this);
        ApplicationComponent.Injector.INSTANCE.inject(this);
    }

}
