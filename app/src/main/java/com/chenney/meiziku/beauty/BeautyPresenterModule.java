package com.chenney.meiziku.beauty;


import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2016/8/25.
 */
@Module
public class BeautyPresenterModule {

    private final BeautyContract.View mView;

    private final int type;

    public BeautyPresenterModule(BeautyContract.View mView,int type) {
        this.mView = mView;
        this.type = type;
    }

    @Provides
    BeautyContract.View provideBeautyContractView(){
        return mView;
    }

    @Provides
    int provideType(){
        return type;
    }
}
