package com.aotuman.nbahubu.di

import com.aotuman.nbahubu.BuildConfig
import com.aotuman.nbahubu.data.remote.headline.HeadLineService
import com.aotuman.nbahubu.data.remote.news.NewsCommentService
import com.aotuman.nbahubu.data.remote.news.NewsService
import com.aotuman.nbahubu.data.remote.player.PlayerService
import com.aotuman.nbahubu.data.remote.temp.NewsServiceTemp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton


/**
 * <pre>
 *     author: aotuman
 *     date  : 2021/05/11
 *     desc  :
 * </pre>
 */

@Module
@InstallIn(ApplicationComponent::class)
object NetWorkModule {

    /**
     * @Provides 常用于被 @Module 注解标记类的内部的方法，并提供依赖项对象。
     * @Singleton 提供单例
     */
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
        }
        return OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .build()
    }

    @Provides
    @Singleton
    @Named("retrofit_nba_new")
    fun provideRetrofitNBANew(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://api.nba.cn/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    @Named("retrofit_nba")
    fun provideRetrofitNBA(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://sportsnba.qq.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    @Named("retrofit_kbs")
    fun provideRetrofitKBS(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://kbs.coral.qq.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providePlayerService(@Named("retrofit_nba")retrofit: Retrofit): PlayerService {
        return retrofit.create(PlayerService::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsService(@Named("retrofit_nba")retrofit: Retrofit): NewsService {
        return retrofit.create(NewsService::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsServiceTemp(@Named("retrofit_nba_new")retrofit: Retrofit): NewsServiceTemp {
        return retrofit.create(NewsServiceTemp::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsCommentService(@Named("retrofit_kbs")retrofit: Retrofit): NewsCommentService {
        return retrofit.create(NewsCommentService::class.java)
    }

    @Provides
    @Singleton
    fun provideHeadLineService(@Named("retrofit_nba_new")retrofit: Retrofit): HeadLineService {
        return retrofit.create(HeadLineService::class.java)
    }
}
