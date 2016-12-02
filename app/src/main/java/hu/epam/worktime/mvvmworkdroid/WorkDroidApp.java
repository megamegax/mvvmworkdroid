package hu.epam.worktime.mvvmworkdroid;

import android.app.Application;
import hu.epam.worktime.mvvmworkdroid.di.ApplicationComponent;

import com.jakewharton.threetenabp.AndroidThreeTen;

/**
 * Created by Mihaly_Hunyady on 2016. 11. 24..
 */

public class WorkDroidApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AndroidThreeTen.init(this);
        ApplicationComponent.Injector.inject(this);
    }

}
