package hu.epam.worktime.mvvmworkdroid;

import android.app.Application;
import hu.epam.worktime.mvvmworkdroid.di.ApiModule;
import hu.epam.worktime.mvvmworkdroid.di.ApplicationComponent;
import hu.epam.worktime.mvvmworkdroid.di.ApplicationModule;
import hu.epam.worktime.mvvmworkdroid.di.DaggerApplicationComponent;

/**
 * Created by Mihaly_Hunyady on 2016. 11. 24..
 */

public class WorkDroidApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Injector.inject(this);
    }

}

/**
 * Inner injector class to avoid the boiler-plate dagger coding in injected class.
 */
final class Injector {
    private static ApplicationComponent applicationComponent;

    private Injector() {

    }

    public static void inject(WorkDroidApp application) {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(application))
                .apiModule(new ApiModule("http://hunyady.ddns.net:8015/"))
                .build();
        applicationComponent.inject(application);
    }

    public static ApplicationComponent getComponent() {
        return applicationComponent;
    }
}