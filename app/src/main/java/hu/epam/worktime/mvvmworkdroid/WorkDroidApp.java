package hu.epam.worktime.mvvmworkdroid;

import android.app.Application;
import hu.epam.worktime.mvvmworkdroid.di.ApplicationComponent;

/**
 * Created by Mihaly_Hunyady on 2016. 11. 24..
 */

public class WorkDroidApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationComponent.Injector.inject(this);
    }

}
