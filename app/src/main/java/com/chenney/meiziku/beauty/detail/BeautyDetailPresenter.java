package com.chenney.meiziku.beauty.detail;

import com.chenney.meiziku.data.entity.ShowBean;
import com.chenney.meiziku.data.source.MeiziApiService;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/8/26.
 */
public class BeautyDetailPresenter implements BeautyDetailContract.Presenter {

    BeautyDetailContract.View mView;
    MeiziApiService mApiService;
    int pictureId;

    @Inject
    public BeautyDetailPresenter(BeautyDetailContract.View view, MeiziApiService apiService,int id){
        mView = view;
        mApiService = apiService;
        pictureId = id;
    }

    @Inject
    void setPresenter(){
        mView.setPresenter(this);
    }

    @Override
    public void loadPicture() {
        mApiService.getPicShow(pictureId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map(new Func1<ShowBean, List<ShowBean.PictureEntity>>() {
                    @Override
                    public List<ShowBean.PictureEntity> call(ShowBean showBean) {
                        return showBean.getList();
                    }
                }).subscribe(new Subscriber<List<ShowBean.PictureEntity>>() {
                    @Override
                    public void onCompleted() {
                        mView.showMessage("complete");
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showMessage("erroe:"+e.getMessage());
                    }

                    @Override
                    public void onNext(List<ShowBean.PictureEntity> pictureEntities) {
                        mView.showPicture(pictureEntities);
                    }
                });
    }

    @Override
    public void start() {
        loadPicture();
    }
}
