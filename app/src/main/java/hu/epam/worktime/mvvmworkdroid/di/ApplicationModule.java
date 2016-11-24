package hu.epam.worktime.mvvmworkdroid.di;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import dagger.Module;
import dagger.Provides;
import hu.epam.worktime.mvvmworkdroid.di.qualifiers.ApplicationContext;

import javax.inject.Singleton;

/**
 * Created by Mihaly_Hunyady on 2016. 11. 24..
 */

@Module
@Singleton
public class ApplicationModule {
    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @ApplicationContext
    @Singleton
    Context provideContext() {
        return application;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return application;
    }


    @Provides
    @Singleton
    Resources provideResources() {
        return application.getResources();
    }
}
