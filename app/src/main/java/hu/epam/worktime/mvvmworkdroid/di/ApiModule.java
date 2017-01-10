package hu.epam.worktime.mvvmworkdroid.di;

import dagger.Module;
import dagger.Provides;
import hu.epam.worktime.mvvmworkdroid.common.gsonjavatime.Converters;
import hu.epam.worktime.mvvmworkdroid.common.utils.ConstValues;
import hu.epam.worktime.mvvmworkdroid.modules.services.WorkServiceApi;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import com.google.gson.GsonBuilder;

/**
 *
 *
 * Created by Mihaly_Hunyady on 2016. 11. 24..
 */

@Module
public class ApiModule {

    @Provides
    GsonBuilder provideGsonBuilder() {
        return new GsonBuilder();
    }

    @Provides
    GsonConverterFactory provideGsonConverterFactory(GsonBuilder gsonBuilder) {
        return GsonConverterFactory.create(Converters.registerAll(gsonBuilder).create());
    }

    @Provides
    OkHttpClient provideOkhttpClient() {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        return client.build();
    }

    @Provides
    WorkServiceApi provideWorkService(GsonConverterFactory gsonConverterFactory, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(ConstValues.WORK_SERVICE_BASE_URL)
                .client(okHttpClient)
                .build().create(WorkServiceApi.class);
    }
}
