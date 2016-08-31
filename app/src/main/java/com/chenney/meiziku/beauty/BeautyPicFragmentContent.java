package com.chenney.meiziku.beauty;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chanven.lib.cptr.loadmore.OnLoadMoreListener;
import com.chanven.lib.cptr.loadmore.SwipeRefreshHelper;
import com.chanven.lib.cptr.recyclerview.RecyclerAdapterWithHF;
import com.chenney.meiziku.R;
import com.chenney.meiziku.adapt.PicCoverAdapt;
import com.chenney.meiziku.beauty.detail.BeautyDetailActivity;
import com.chenney.meiziku.data.entity.ClassifyBean;
import com.chenney.meiziku.data.source.module.ApiServiceModule;
import com.trello.rxlifecycle.components.support.RxFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by Administrator on 2016/8/25.
 */
public class BeautyPicFragmentContent extends RxFragment implements SwipeRefreshLayout.OnRefreshListener, BeautyContract.View {

    public static final String BUNDLE_ARG_TYPE = "beauty_type";

    @BindView(R.id.recycle_view)
    RecyclerView mRecycleView;
    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout mSwipeLayout;

    private SwipeRefreshHelper swipeRefreshHelper;

    private List<ClassifyBean.TngouEntity> dataList = new ArrayList<>();

    private int type = 1;

    @Inject
    BeautyPresenter mPresenter;

    private StaggeredGridLayoutManager gridLayoutManager;
    private PicCoverAdapt adapt;

    public static BeautyPicFragmentContent newInstance(int type){
        BeautyPicFragmentContent fragment = new BeautyPicFragmentContent();
        Bundle args = new Bundle();
        args.putInt(BUNDLE_ARG_TYPE,type);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        if(getArguments() != null){
            type = getArguments().getInt(BUNDLE_ARG_TYPE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_beauty_pic_content, null);
        ButterKnife.bind(this, view);

        mSwipeLayout.setColorSchemeColors(Color.BLUE);

        DaggerBeautyComponent.builder()
                .apiServiceModule(new ApiServiceModule())
                .beautyPresenterModule(new BeautyPresenterModule(this,type+1))
                .build().inject(this);

        mPresenter.start();

        return view;
    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);

//        mSwipeLayout.setOnRefreshListener(this);

        gridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        mRecycleView.setHasFixedSize(true);
        mRecycleView.setLayoutManager(gridLayoutManager);

        adapt = new PicCoverAdapt(getActivity());
        mRecycleView.setAdapter(new RecyclerAdapterWithHF(adapt));

//        mRecycleView.addOnScrollListener(new RecycleViewScrollListener(getContext()) {
//            @Override
//            public void onLoad(int currentPage) {
//                onRefresh();
//            }
//        });

        swipeRefreshHelper = new SwipeRefreshHelper(mSwipeLayout);

        swipeRefreshHelper.setOnSwipeRefreshListener(new SwipeRefreshHelper.OnSwipeRefreshListener() {
            @Override
            public void onfresh() {
                mPresenter.refreshPictures();
            }
        });

        swipeRefreshHelper.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void loadMore() {
                mPresenter.loadMorePictures();
            }
        });

        adapt.setItemClickListener(new PicCoverAdapt.PicCoverItemClickListener() {
            @Override
            public void onItemClick(View view, int position, ClassifyBean.TngouEntity data) {
                Intent intent = new Intent(getActivity(), BeautyDetailActivity.class);

                int id = data.getId();
                String title = data.getTitle();

                intent.putExtra("id",id);
                intent.putExtra("title",title);

                Observable.just(intent)
                        .throttleFirst(1000, TimeUnit.MILLISECONDS)
                        .subscribe(new Subscriber<Intent>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(Intent intent) {
                                startActivity(intent);
                            }
                        });

            }
        });

    }


    @Override
    public void onRefresh() {
        mPresenter.refreshPictures();
    }

    @Override
    public void showPictureList(List<ClassifyBean.TngouEntity> picList) {
        dataList.clear();
        addPictureList(picList);
    }

    @Override
    public void addPictureList(List<ClassifyBean.TngouEntity> picList){
        dataList.addAll(picList);
        adapt.setDatas(dataList);
        swipeRefreshHelper.refreshComplete();
        swipeRefreshHelper.setLoadMoreEnable(true);
        swipeRefreshHelper.loadMoreComplete(true);
    }

    @Override
    public void showPictureDetailUi(String picId) {

    }

    @Override
    public void showLoading(boolean show) {
        mSwipeLayout.setRefreshing(show);
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(mRecycleView, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public <V> Observable.Transformer<V, V> bind() {
        return bindToLifecycle();
    }
}
