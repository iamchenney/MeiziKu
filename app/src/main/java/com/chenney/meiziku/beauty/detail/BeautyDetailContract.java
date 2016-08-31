package com.chenney.meiziku.beauty.detail;

import com.chenney.meiziku.BasePresenter;
import com.chenney.meiziku.BaseView;
import com.chenney.meiziku.data.entity.ShowBean;

import java.util.List;

/**
 * Created by Administrator on 2016/8/26.
 */
public interface BeautyDetailContract {

    interface View extends BaseView{
        void showPicture(List<ShowBean.PictureEntity> datas);
        void setPresenter(BeautyDetailContract.Presenter presenter);
        void back();
    }

    interface Presenter extends BasePresenter{
        void loadPicture();
    }
}
