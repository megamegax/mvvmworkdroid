package hu.epam.worktime.mvvmworkdroid.di.save;

import android.content.Context;
import android.content.res.Resources;

import dagger.Component;
import hu.epam.worktime.mvvmworkdroid.di.ActivityModule;
import hu.epam.worktime.mvvmworkdroid.di.ApplicationComponent;

import hu.epam.worktime.mvvmworkdroid.di.qualifiers.ApplicationContext;
import hu.epam.worktime.mvvmworkdroid.di.scopes.PerActivity;
import hu.epam.worktime.mvvmworkdroid.modules.save.router.SaveActivity;
import hu.epam.worktime.mvvmworkdroid.modules.services.WorkServiceApi;

/**
 * Created by Mihaly_Hunyady on 2017. 01. 06..
 */

@Component(dependencies = ApplicationComponent.class, modules = {SaveActivityModule.class, ActivityModule.class})
@PerActivity
public interface SaveActivityComponent {

    void inject(SaveActivity saveActivity);

    Resources resources();

    @ApplicationContext
    Context context();

    WorkServiceApi workServiceApi();

    /**
     * Injector class for injecting the component into the activity.
     * The class help to avoid the boiler-plate dagger coding in injected class.
     */

    final class Injector {
        private static SaveActivityComponent activityComponent;

        private Injector() {
        }

        public static SaveActivityComponent buildComponent(SaveActivity activity) {
            activityComponent = DaggerSaveActivityComponent.builder()
                    .applicationComponent(ApplicationComponent.Injector.getComponent())
                    .activityModule(new ActivityModule(activity))
                    .saveActivityModule(new SaveActivityModule(activity))
                    .build();
            return activityComponent;
        }

        public static void setActivityComponent(SaveActivityComponent activityComponent) {
            SaveActivityComponent.Injector.activityComponent = activityComponent;
        }

        public static SaveActivityComponent getComponent() {
            return activityComponent;
        }
    }
}
