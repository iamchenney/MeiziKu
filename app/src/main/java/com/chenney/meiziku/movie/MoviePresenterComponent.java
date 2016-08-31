package com.chenney.meiziku.movie;

import com.chenney.meiziku.data.source.module.ApiServiceModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Administrator on 2016/8/29.
 */
@Singleton
@Component(modules = {MoviePresenterModule.class, ApiServiceModule.class})
public interface MoviePresenterComponent {

    void inject(MovieFragment fragment);
}
