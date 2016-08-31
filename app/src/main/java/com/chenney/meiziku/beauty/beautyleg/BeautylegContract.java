package com.chenney.meiziku.beauty.beautyleg;

/**
 * Created by Administrator on 2016/8/24.
 */
public interface BeautylegContract {

    interface View{
        void showPictures();
    }

    interface Presenter{
        void loadData();
    }
}
