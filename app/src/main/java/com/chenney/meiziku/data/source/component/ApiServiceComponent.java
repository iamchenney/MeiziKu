package com.chenney.meiziku.data.source.component;

import com.chenney.meiziku.ApplicationModule;
import com.chenney.meiziku.data.source.module.ApiServiceModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Administrator on 2016/8/24.
 */
@Singleton
@Component(modules = {ApiServiceModule.class, ApplicationModule.class})
public interface ApiServiceComponent {
}
