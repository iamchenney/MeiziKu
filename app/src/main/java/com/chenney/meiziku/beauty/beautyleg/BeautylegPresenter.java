package com.chenney.meiziku.beauty.beautyleg;

import android.util.Log;

import com.chenney.meiziku.data.entity.ClassifyBean;
import com.chenney.meiziku.data.source.MeiziApiService;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/8/24.
 */
public class BeautylegPresenter implements BeautylegContract.Presenter {

    BeautylegContract.View mView;
    MeiziApiService apiService;

    @Inject
    public BeautylegPresenter(BeautylegContract.View mView, MeiziApiService apiService) {
        this.mView = mView;
        this.apiService = apiService;
    }

    @Override
    public void loadData() {
        apiService.getPicClassify(1,1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<ClassifyBean, String>() {
                    @Override
                    public String call(ClassifyBean classifyBean) {
                        return "success";
                    }
                })
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        Log.e("tt","api request completed!!!!");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("tt",e.getMessage());
                        mView.showPictures();
                    }

                    @Override
                    public void onNext(String classifyBean) {
                        mView.showPictures();
                    }
                });
    }
}
