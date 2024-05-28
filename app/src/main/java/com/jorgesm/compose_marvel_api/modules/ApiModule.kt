package com.jorgesm.compose_marvel_api.modules

import com.jorgesm.data.server.api.ApiServices
import com.jorgesm.data.server.repositoryImpl.ApiServicesRepositoryImpl
import com.jorgesm.data.utils.Const
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ApiModule {

    @Singleton
    @Provides
    fun provideApiClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(120, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()

    @Singleton
    @Provides
    fun provideRetrofitServices(client: OkHttpClient): ApiServices {
        return Retrofit.Builder()
            .baseUrl(Const.BASE_URL)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiServices::class.java)
    }

    @Singleton
    @Provides
    fun provideApiServicesRepositoryImpl(apiServices: ApiServices): ApiServicesRepositoryImpl =
        ApiServicesRepositoryImpl(apiServices)


}