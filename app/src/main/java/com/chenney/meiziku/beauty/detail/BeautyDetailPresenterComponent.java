package com.chenney.meiziku.beauty.detail;

import com.chenney.meiziku.data.source.module.ApiServiceModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Administrator on 2016/8/26.
 */
@Singleton
@Component(modules = {BeautyDetailPresenterModule.class, ApiServiceModule.class})
public interface BeautyDetailPresenterComponent {

    void inject(BeautyDetailActivity activity);
}
