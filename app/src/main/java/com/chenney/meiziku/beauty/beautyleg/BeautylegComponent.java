package com.chenney.meiziku.beauty.beautyleg;

import com.chenney.meiziku.data.source.module.ApiServiceModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Administrator on 2016/8/24.
 */
@Singleton
@Component(modules = {BeautylegModule.class, ApiServiceModule.class})
public interface BeautylegComponent {
    void inject(BeautylegFragment fragment);
}
