package com.chenney.meiziku.data.source.module;

import com.chenney.meiziku.data.source.MeiziApiService;
import com.chenney.meiziku.data.source.MovieApiService;
import com.chenney.meiziku.util.Constants;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/8/24.
 */
@Module
public class ApiServiceModule {

    @Provides
    @Singleton
    protected OkHttpClient provideHttpClient(){
        return new OkHttpClient.Builder().connectTimeout(5000, TimeUnit.MILLISECONDS)
                .readTimeout(5000,TimeUnit.MILLISECONDS)
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
    }

    @Provides
    @Singleton
    protected RxJavaCallAdapterFactory provideRxJavaCallAdapterFactory(){
        return RxJavaCallAdapterFactory.create();
    }

    @Provides
    @Singleton
    protected GsonConverterFactory provideGsonConverterFactory(){
        return GsonConverterFactory.create();
    }

    @Provides
    @Singleton
    public MeiziApiService provideMeiziApiService(RxJavaCallAdapterFactory callAdapterFactory,GsonConverterFactory converterFactory,OkHttpClient client){
        return new Retrofit.Builder()
                .addCallAdapterFactory(callAdapterFactory)
                .addConverterFactory(converterFactory)
                .client(client)
                .baseUrl(Constants.BASE_URL_MEIZI)
                .build().create(MeiziApiService.class);

    }

    @Provides
    @Singleton
    public MovieApiService provideMovieApiService(RxJavaCallAdapterFactory callAdapterFactory, GsonConverterFactory converterFactory, OkHttpClient client){
        return new Retrofit.Builder()
                .addCallAdapterFactory(callAdapterFactory)
                .addConverterFactory(converterFactory)
                .client(client)
                .baseUrl(Constants.BASE_URL_MOVIE)
                .build().create(MovieApiService.class);

    }
}
