package com.chenney.meiziku.beauty;

import com.chenney.meiziku.data.source.module.ApiServiceModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Administrator on 2016/8/25.
 */
@Singleton
@Component(modules = {BeautyPresenterModule.class, ApiServiceModule.class})
public interface BeautyComponent {

    void inject(BeautyPicFragmentContent fragment);
}
