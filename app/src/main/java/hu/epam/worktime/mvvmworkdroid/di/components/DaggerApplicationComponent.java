package hu.epam.worktime.mvvmworkdroid.di.components;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import hu.epam.worktime.mvvmworkdroid.WorkDroidApp;
import hu.epam.worktime.mvvmworkdroid.di.ApiModule;
import hu.epam.worktime.mvvmworkdroid.di.ApplicationComponent;
import hu.epam.worktime.mvvmworkdroid.di.ApplicationModule;
import hu.epam.worktime.mvvmworkdroid.di.ApplicationModule_ProvideApplicationFactory;
import hu.epam.worktime.mvvmworkdroid.di.ApplicationModule_ProvideContextFactory;
import hu.epam.worktime.mvvmworkdroid.di.ApplicationModule_ProvideResourcesFactory;

import javax.inject.Provider;

/**
 * Created by Mihaly_Hunyady on 2016. 11. 24..
 */

public class DaggerApplicationComponent implements ApplicationComponent {

    private Provider<Context> provideContextProvider;

    private Provider<Application> provideApplicationProvider;

    private Provider<Resources> provideResourcesProvider;


    private DaggerApplicationComponent(Builder builder) {
        assert builder != null;
        initialize(builder);
    }

    public static Builder builder() {
        return new Builder();
    }


    @Override
    public void inject(WorkDroidApp workDroidApp) {

    }


    @Override
    public Context context() {
        return provideContextProvider.get();
    }

    @Override
    public Application application() {
        return provideApplicationProvider.get();
    }

    @Override
    public Resources resources() {
        return provideResourcesProvider.get();
    }

        private void initialize(final Builder builder){
            this.provideContextProvider =
              DoubleCheck.provider(
                ApplicationModule_ProvideContextFactory.create(builder.applicationModule));

            this.provideApplicationProvider =
              DoubleCheck.provider(
                ApplicationModule_ProvideApplicationFactory.create(builder.applicationModule));

            this.provideResourcesProvider =
              DoubleCheck.provider(
                ApplicationModule_ProvideResourcesFactory.create(builder.applicationModule));

        }

    public static final class Builder {
        private ApplicationModule applicationModule;

        private ApiModule apiModule;

        private Builder() {}

        public ApplicationComponent build() {
            if (applicationModule == null) {
                throw new IllegalStateException(
                  ApplicationModule.class.getCanonicalName() + " must be set");
            }
            if (apiModule == null) {
                this.apiModule = new ApiModule();
            }
            return new DaggerApplicationComponent(this);
        }

        public Builder applicationModule(ApplicationModule applicationModule) {
            this.applicationModule = Preconditions.checkNotNull(applicationModule);
            return this;
        }

        public Builder apiModule(ApiModule apiModule) {
            this.apiModule = Preconditions.checkNotNull(apiModule);
            return this;
        }
    }
}
