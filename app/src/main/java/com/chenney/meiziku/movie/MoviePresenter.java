package com.chenney.meiziku.movie;

import com.chenney.meiziku.data.entity.douban.Response;
import com.chenney.meiziku.data.entity.douban.Subject;
import com.chenney.meiziku.data.source.MovieApiService;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/8/29.
 */
public class MoviePresenter implements MovieContract.Presenter {

    private final MovieContract.View mView;
    private final MovieApiService mApiService;

    private int pageSize = 20;
    private int page = 1;

    @Inject
    public MoviePresenter(MovieContract.View mView, MovieApiService mApiService) {
        this.mView = mView;
        this.mApiService = mApiService;
    }

    @Override
    public void refreshMovieList() {
        page = 1;
        loadMovie(page);
    }

    @Override
    public void loadMoreMovieList() {
        loadMovie(page++);
    }

    private void loadMovie(final int currentPage){
        int start = pageSize*(currentPage -1);
        mApiService.getTop250(start,pageSize)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map(new Func1<Response<Subject>, List<Subject>>() {
                    @Override
                    public List<Subject> call(Response<Subject> subjectResponse) {
                        return subjectResponse.getSubjects();
                    }
                })
                .subscribe(new Subscriber<List<Subject>>() {
                    @Override
                    public void onCompleted() {
                        mView.showMessage("complete");
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showMessage("error:"+e.getMessage());
                    }

                    @Override
                    public void onNext(List<Subject> subjects) {
                        if(currentPage == 1){
                            mView.showMovieList(subjects);
                        }else{
                            mView.addMovielist(subjects);
                        }
                    }
                });
    }

    @Override
    public void start() {
        refreshMovieList();
    }
}
