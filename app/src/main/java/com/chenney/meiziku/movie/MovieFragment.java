package com.chenney.meiziku.movie;

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
import com.chenney.meiziku.adapt.MovieCoverAdapt;
import com.chenney.meiziku.data.entity.douban.Subject;
import com.chenney.meiziku.data.source.module.ApiServiceModule;
import com.trello.rxlifecycle.components.support.RxFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;

/**
 * Created by Administrator on 2016/8/25.
 */
public class MovieFragment extends RxFragment implements MovieContract.View{

    @BindView(R.id.recycle_view)
    RecyclerView mRecycleView;
    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout mSwipeLayout;

    private MovieCoverAdapt adapter;

    private SwipeRefreshHelper swipeRefreshHelper;

    private StaggeredGridLayoutManager gridLayoutManager;

    private List<Subject> dataList = new ArrayList<>();

    @Inject
    MoviePresenter mPresenter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movie_fragment, container, false);
        ButterKnife.bind(this, view);

        mSwipeLayout.setColorSchemeColors(Color.BLUE);

        DaggerMoviePresenterComponent.builder()
                .apiServiceModule(new ApiServiceModule())
                .moviePresenterModule(new MoviePresenterModule(this))
                .build().inject(this);

        return view;
    }

    public void onViewCreated(View view,Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);

        gridLayoutManager = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
        mRecycleView.setHasFixedSize(true);
        mRecycleView.setLayoutManager(gridLayoutManager);

        adapter = new MovieCoverAdapt(getContext());
        mRecycleView.setAdapter(new RecyclerAdapterWithHF(adapter));

        swipeRefreshHelper = new SwipeRefreshHelper(mSwipeLayout);

        swipeRefreshHelper.setOnSwipeRefreshListener(new SwipeRefreshHelper.OnSwipeRefreshListener() {
            @Override
            public void onfresh() {
                mPresenter.refreshMovieList();
            }
        });

        swipeRefreshHelper.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void loadMore() {
                mPresenter.loadMoreMovieList();
            }
        });

        adapter.setItemClickListener(new MovieCoverAdapt.ItemClickListener(){
            @Override
            public void onItemClick( int position, View view,Subject data) {
                showMessage(data.getTitle());
            }
        });

        mPresenter.start();
    }

    @Override
    public void showMovieList(List<Subject> movies) {
        dataList.clear();
        addMovielist(movies);
    }

    @Override
    public void addMovielist(List<Subject> movies) {
        dataList.addAll(movies);
        adapter.setmDatas(dataList);
        swipeRefreshHelper.refreshComplete();
        swipeRefreshHelper.setLoadMoreEnable(true);
        swipeRefreshHelper.loadMoreComplete(true);
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(mRecycleView,message,Snackbar.LENGTH_LONG);
    }

    @Override
    public <V> Observable.Transformer<V, V> bind() {
        return bindToLifecycle();
    }
}
