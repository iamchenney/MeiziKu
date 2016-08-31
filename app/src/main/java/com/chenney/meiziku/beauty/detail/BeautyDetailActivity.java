package com.chenney.meiziku.beauty.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.chenney.meiziku.R;
import com.chenney.meiziku.data.source.module.ApiServiceModule;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/8/25.
 */
public class BeautyDetailActivity extends RxAppCompatActivity {

    @Inject
    BeautyDetailPresenter mPresenter;

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beauty_pic_detail);
        ButterKnife.bind(this);

        BeautyDetailFragment fragment = (BeautyDetailFragment) getSupportFragmentManager()
                .findFragmentById(R.id.contentFragment);

        int id = initData();

        DaggerBeautyDetailPresenterComponent.builder()
                .apiServiceModule(new ApiServiceModule())
                .beautyDetailPresenterModule(new BeautyDetailPresenterModule(fragment,id))
                .build().inject(this);

        initData();

    }

    private int initData(){
        Intent intent = getIntent();
        int id = intent.getExtras().getInt("id");
        String title = intent.getExtras().getString("title");
        tvTitle.setText(title);
        return id;
    }

    @OnClick(R.id.iv_back)
    public void onClick() {
        finish();
    }
}
