package com.chenney.meiziku.beauty;

import com.chenney.meiziku.data.entity.ClassifyBean;
import com.chenney.meiziku.data.source.MeiziApiService;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/8/25.
 */
public class BeautyPresenter implements BeautyContract.Presenter {

    private final BeautyContract.View mView;
    private final MeiziApiService mApiService;

    private int type = 1;
    private int page = 1;

    @Inject
    public BeautyPresenter(BeautyContract.View mView, MeiziApiService mApiService,int type) {
        this.mView = mView;
        this.type = type;
        this.mApiService = mApiService;
    }

    @Override
    public void loadMorePictures() {
        loadPictures(page++);
    }

    @Override
    public void refreshPictures(){
        page = 1;
        loadPictures(page);
    }

    private void loadPictures(final int currentPage){
        mView.showLoading(true);

        Subscription subscription = mApiService.getPicClassify(type,currentPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<ClassifyBean, List<ClassifyBean.TngouEntity>>() {
                    @Override
                    public List<ClassifyBean.TngouEntity> call(ClassifyBean classifyBean) {
                        return classifyBean.getTngou();
                    }
                })
                .subscribe(new Subscriber<List<ClassifyBean.TngouEntity>>() {
                    @Override
                    public void onCompleted() {
                        mView.showMessage("complete");
                        mView.showLoading(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showMessage(e.getMessage());
                        mView.showLoading(false);
                    }

                    @Override
                    public void onNext(List<ClassifyBean.TngouEntity> datas) {
                        if(currentPage == 1){
                            mView.showPictureList(datas);
                        }else {
                            mView.addPictureList(datas);
                        }

                        mView.showLoading(false);
                    }
                });
    }

    @Override
    public void start() {
        refreshPictures();
    }
}
