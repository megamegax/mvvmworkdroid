package hu.epam.worktime.mvvmworkdroid.di;

import dagger.Module;
import dagger.Provides;
import hu.epam.worktime.mvvmworkdroid.common.gsonjavatime.Converters;
import hu.hanprog.worktime.service.WorkServiceApi;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.inject.Singleton;
import com.google.gson.GsonBuilder;

/**
 *
 *
 * Created by Mihaly_Hunyady on 2016. 11. 24..
 */

@Module
public class ApiModule {

    private String mBaseUrl;

    public ApiModule(String mBaseUrl) {
        this.mBaseUrl = mBaseUrl;
    }

    @Provides
    GsonBuilder provideGsonBuilder() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder;
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
                .baseUrl(mBaseUrl)
                .client(okHttpClient)
                .build().create(WorkServiceApi.class);
    }
}
