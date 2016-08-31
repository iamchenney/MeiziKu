package com.chenney.meiziku.beauty.beautyleg;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2016/8/24.
 */
@Module
public class BeautylegModule {

    private final BeautylegContract.View mView;

    BeautylegModule(BeautylegContract.View view){
        mView = view;
    }

    @Provides
    BeautylegContract.View provideBeautylegContractView(){
        return mView;
    }
}
