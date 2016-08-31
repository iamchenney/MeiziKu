package com.chenney.meiziku.app;

import android.app.Application;

import com.chenney.meiziku.ApplicationModule;
import com.chenney.meiziku.data.source.component.ApiServiceComponent;
import com.chenney.meiziku.data.source.component.DaggerApiServiceComponent;
import com.chenney.meiziku.data.source.module.ApiServiceModule;

/**
 * Created by Administrator on 2016/8/24.
 */
public class MMApplication extends Application {

    private ApiServiceComponent mApiServiceComponent;

    @Override
    public void onCreate(){
        super.onCreate();
        mApiServiceComponent = DaggerApiServiceComponent.builder()
                .applicationModule(new ApplicationModule(getApplicationContext()))
                .apiServiceModule(new ApiServiceModule())
                .build();
    }

    public ApiServiceComponent getApiServiceComponent(){
        return mApiServiceComponent;
    }
}
