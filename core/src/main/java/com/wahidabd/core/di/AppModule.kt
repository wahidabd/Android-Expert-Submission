package com.wahidabd.core.di

import android.content.Context
import com.wahidabd.core.BuildConfig
import com.wahidabd.core.common.MyDispatchers
import com.wahidabd.core.common.SafeCall
import com.wahidabd.core.data.source.local.room.MovieDatabase
import com.wahidabd.core.data.source.remote.network.MovieService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSafeCall() = SafeCall()

    @Provides
    @Singleton
    fun provideMyDispatchers() = MyDispatchers()

    @Provides
    @Singleton
    fun provideOkHttpInterceptor() = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
        else HttpLoggingInterceptor.Level.NONE
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        interceptor: HttpLoggingInterceptor,
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .addInterceptor(Interceptor { chain ->
            val request = chain.request().newBuilder()
            val originalUrl = chain.request().url
            val url = originalUrl.newBuilder()
                .addQueryParameter("api_key", BuildConfig.API_KEY)
                .build()
            request.url(url)
            chain.proceed(request.build())
        })
        .connectTimeout(120, TimeUnit.SECONDS)
        .readTimeout(120, TimeUnit.SECONDS)
        .build()

    @Provides
    @Singleton
    fun provideRetrofitClient(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideMovieService(retrofit: Retrofit): MovieService =
        retrofit.create(MovieService::class.java)

    @Provides
    @Singleton
    fun provideMovieDatabase(@ApplicationContext context: Context) =
        MovieDatabase.getDatabase(context)
}