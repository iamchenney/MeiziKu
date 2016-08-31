package com.chenney.meiziku.movie;

import com.chenney.meiziku.BasePresenter;
import com.chenney.meiziku.BaseView;
import com.chenney.meiziku.data.entity.douban.Subject;

import java.util.List;

/**
 * Created by Administrator on 2016/8/29.
 */
public interface MovieContract {

    interface View extends BaseView{
        void showMovieList(List<Subject> movies);
        void addMovielist(List<Subject> movies);
    }

    interface Presenter extends BasePresenter{
        void refreshMovieList();
        void loadMoreMovieList();
    }
}
