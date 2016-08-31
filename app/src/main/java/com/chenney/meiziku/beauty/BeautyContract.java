package com.chenney.meiziku.beauty;

import com.chenney.meiziku.BasePresenter;
import com.chenney.meiziku.BaseView;
import com.chenney.meiziku.data.entity.ClassifyBean;

import java.util.List;

/**
 * Created by Administrator on 2016/8/25.
 */
public interface BeautyContract {

    interface View extends BaseView{

        void showPictureList(List<ClassifyBean.TngouEntity> picList);

        void addPictureList(List<ClassifyBean.TngouEntity> picList);
        void showPictureDetailUi(String picId);

        void showLoading(boolean show);
    }

    interface Presenter extends BasePresenter{

        void refreshPictures();

        void loadMorePictures();
    }
}
