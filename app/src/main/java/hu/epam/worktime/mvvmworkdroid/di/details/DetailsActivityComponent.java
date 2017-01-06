package hu.epam.worktime.mvvmworkdroid.di.details;

import android.content.Context;
import android.content.res.Resources;

import dagger.Component;
import hu.epam.worktime.mvvmworkdroid.di.ActivityModule;
import hu.epam.worktime.mvvmworkdroid.di.ApplicationComponent;
import hu.epam.worktime.mvvmworkdroid.di.qualifiers.ApplicationContext;
import hu.epam.worktime.mvvmworkdroid.di.scopes.PerActivity;
import hu.epam.worktime.mvvmworkdroid.modules.details.router.DetailsActivity;

/**
 * Created by Mihaly_Hunyady on 2017. 01. 06..
 */

@Component(dependencies = ApplicationComponent.class, modules = {DetailsActivityModule.class, ActivityModule.class})
@PerActivity
public interface DetailsActivityComponent {

    void inject(DetailsActivity saveActivity);

    Resources resources();

    @ApplicationContext
    Context context();

    /**
     * Injector class for injecting the component into the activity.
     * The class help to avoid the boiler-plate dagger coding in injected class.
     */

    final class Injector {
        private static DetailsActivityComponent activityComponent;

        private Injector() {
        }

        public static DetailsActivityComponent buildComponent(DetailsActivity activity) {
            activityComponent = DaggerDetailsActivityComponent.builder()
                    .applicationComponent(ApplicationComponent.Injector.getComponent())
                    .activityModule(new ActivityModule(activity))
                    .detailsActivityModule(new DetailsActivityModule(activity))
                    .build();
            return activityComponent;
        }

        public static void setActivityComponent(DetailsActivityComponent activityComponent) {
            DetailsActivityComponent.Injector.activityComponent = activityComponent;
        }

        public static DetailsActivityComponent getComponent() {
            return activityComponent;
        }
    }
}
