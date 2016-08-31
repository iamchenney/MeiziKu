package com.chenney.meiziku.beauty.detail;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2016/8/26.
 */
@Module
public class BeautyDetailPresenterModule {

    private final BeautyDetailContract.View mView;

    private final  int pictureId;

    public BeautyDetailPresenterModule(BeautyDetailContract.View mView,int pictureId) {
        this.mView = mView;
        this.pictureId = pictureId;
    }

    @Provides
    BeautyDetailContract.View provideBeautyDetailContractView(){
        return mView;
    }

    @Provides
    int provideBeautyDetailId(){
        return pictureId;
    }
}
