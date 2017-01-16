package hu.epam.worktime.mvvmworkdroid.di

import dagger.Module
import dagger.Provides
import hu.epam.worktime.mvvmworkdroid.common.gsonjavatime.Converters
import hu.epam.worktime.mvvmworkdroid.common.utils.ConstValues
import hu.epam.worktime.mvvmworkdroid.modules.services.WorkServiceApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

import com.google.gson.GsonBuilder

/**


 * Created by Mihaly_Hunyady on 2016. 11. 24..
 */

@Module
class ApiModule {

    @Provides
    internal fun provideGsonBuilder(): GsonBuilder {
        return GsonBuilder()
    }

    @Provides
    internal fun provideGsonConverterFactory(gsonBuilder: GsonBuilder): GsonConverterFactory {
        return GsonConverterFactory.create(Converters.registerAll(gsonBuilder).create())
    }

    @Provides
    internal fun provideOkhttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
        return client.build()
    }

    @Provides
    internal fun provideWorkService(gsonConverterFactory: GsonConverterFactory, okHttpClient: OkHttpClient): WorkServiceApi {
        return Retrofit.Builder()
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(ConstValues.WORK_SERVICE_BASE_URL)
                .client(okHttpClient)
                .build().create(WorkServiceApi::class.java)
    }
}
