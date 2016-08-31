package com.chenney.meiziku.beauty.beautyleg;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chenney.meiziku.R;
import com.chenney.meiziku.data.source.module.ApiServiceModule;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/8/23.
 */
public class BeautylegFragment extends Fragment implements BeautylegContract.View {

    @Inject
    BeautylegPresenter mBeautylegPresenter;
    @BindView(R.id.tv)
    TextView tv;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        DaggerBeautylegComponent.builder()
                .apiServiceModule(new ApiServiceModule())
                .beautylegModule(new BeautylegModule(this))
                .build().inject(this);

        ButterKnife.bind(this, view);

        mBeautylegPresenter.loadData();
        return view;
    }

    @Override
    public void showPictures() {
        tv.setText("sssss");
    }
}
