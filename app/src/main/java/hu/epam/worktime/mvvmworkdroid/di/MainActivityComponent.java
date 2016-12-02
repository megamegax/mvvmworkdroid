package hu.epam.worktime.mvvmworkdroid.di;

import android.content.Context;
import android.content.res.Resources;
import dagger.Component;
import hu.epam.worktime.mvvmworkdroid.di.qualifiers.ApplicationContext;
import hu.epam.worktime.mvvmworkdroid.modules.main.view.MainActivity;
import hu.hanprog.worktime.service.WorkServiceApi;

import javax.inject.Singleton;

/**
 *
 *
 * Created by Mihaly_Hunyady on 2016. 12. 01..
 */
@Component(dependencies = ApplicationModule.class, modules = {ActivityModule.class, ApiModule.class})
public interface MainActivityComponent {
    void inject(MainActivity mainActivity);

    Resources resources();

    @ApplicationContext
    Context context();

    WorkServiceApi workServiceApi();

    /**
     * Injector class for injecting the component into the activity.
     * The class help to avoid the boiler-plate dagger coding in injected class.
     */

    final class Injector {
        private static MainActivityComponent activityComponent;

        private Injector() {
        }

        public static MainActivityComponent buildComponent(MainActivity activity) {
            activityComponent = DaggerMainActivityComponent.builder()
                    .activityModule(new ActivityModule(activity))
                    .apiModule(new ApiModule("http://hunyady.ddns.net:8015/"))
                    .build();
            return activityComponent;
        }

        public static void setActivityComponent(MainActivityComponent activityComponent) {
            Injector.activityComponent = activityComponent;
        }

        public static MainActivityComponent getComponent() {
            return activityComponent;
        }
    }
}
