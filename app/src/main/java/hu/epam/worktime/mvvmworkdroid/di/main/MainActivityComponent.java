package hu.epam.worktime.mvvmworkdroid.di.main;

import android.content.Context;
import android.content.res.Resources;
import dagger.Component;
import hu.epam.worktime.mvvmworkdroid.di.ActivityModule;
import hu.epam.worktime.mvvmworkdroid.di.ApplicationComponent;
import hu.epam.worktime.mvvmworkdroid.di.qualifiers.ApplicationContext;
import hu.epam.worktime.mvvmworkdroid.di.scopes.PerActivity;
import hu.epam.worktime.mvvmworkdroid.modules.main.main.router.MainActivity;
import hu.epam.worktime.mvvmworkdroid.modules.main.main.viewmodel.MainViewModel;
import hu.epam.worktime.mvvmworkdroid.modules.services.WorkServiceApi;
import hu.epam.worktime.mvvmworkdroid.modules.services.worker.CalculatorService;

/**
 *
 *
 * Created by Mihaly_Hunyady on 2016. 12. 01..
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {MainActivityModule.class, ActivityModule.class})
public interface MainActivityComponent {

    void inject(MainActivity mainActivity);

    Resources resources();

    @ApplicationContext
    Context context();

    CalculatorService calculatorService();

    WorkServiceApi workServiceApi();

    MainViewModel mainViewModel();

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
                    .applicationComponent(ApplicationComponent.Injector.getComponent())
                    .activityModule(new ActivityModule(activity))
                    .mainActivityModule(new MainActivityModule(activity))
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
