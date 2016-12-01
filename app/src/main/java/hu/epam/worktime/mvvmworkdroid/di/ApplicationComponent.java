package hu.epam.worktime.mvvmworkdroid.di;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import dagger.Component;
import hu.epam.worktime.mvvmworkdroid.WorkDroidApp;
import hu.epam.worktime.mvvmworkdroid.di.qualifiers.ApplicationContext;
import hu.hanprog.worktime.service.WorkServiceApi;

import javax.inject.Singleton;

/**
 * Created by Mihaly_Hunyady on 2016. 11. 24..
 */

@Singleton
@Component(modules = {ApplicationModule.class, ApiModule.class})
public interface ApplicationComponent {

    void inject(WorkDroidApp workDroidApp);

    @ApplicationContext
    Context context();

    Application application();

    Resources resources();

    WorkServiceApi workServiceApi();
}
