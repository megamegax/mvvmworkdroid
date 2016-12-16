package hu.epam.worktime.mvvmworkdroid.di;

import dagger.Component;
import hu.epam.worktime.mvvmworkdroid.di.main.MainActivityComponent;
import hu.epam.worktime.mvvmworkdroid.di.scopes.FragmentScope;
import hu.epam.worktime.mvvmworkdroid.modules.main.list.MainListFragment;

/**
 * Created by Mihaly_Hunyady on 2016. 12. 16..
 */
@Component(dependencies = MainActivityComponent.class)
@FragmentScope
public interface FragmentListComponent {

    void inject(MainListFragment mainActivity);

    /**
     * Injector class for injecting the component into the activity.
     * The class help to avoid the boiler-plate dagger coding in injected class.
     */

    final class Injector {
        private static FragmentListComponent activityComponent;

        private Injector() {
        }

        public static FragmentListComponent buildComponent() {
            activityComponent = DaggerFragmentListComponent.builder()
                    .mainActivityComponent(MainActivityComponent.Injector.getComponent())
                    .build();
            return activityComponent;
        }

        public static void setActivityComponent(FragmentListComponent activityComponent) {
            FragmentListComponent.Injector.activityComponent = activityComponent;
        }

        public static FragmentListComponent getComponent() {
            return activityComponent;
        }
    }
}