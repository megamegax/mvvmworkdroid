package hu.epam.worktime.mvvmworkdroid.di;

import android.app.Activity;
import android.content.Context;
import dagger.Module;
import dagger.Provides;
import hu.epam.worktime.mvvmworkdroid.di.qualifiers.ActivityContext;

/**
 *
 *
 * Created by Mihaly_Hunyady on 2016. 12. 01..
 */
@Module
public class ActivityModule {

    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return activity;
    }

}
