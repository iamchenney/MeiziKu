package com.chenney.meiziku.beauty.detail;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chenney.meiziku.R;
import com.chenney.meiziku.adapt.SiglePicAdapt;
import com.chenney.meiziku.data.entity.ShowBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;

/**
 * Created by Administrator on 2016/8/26.
 */
public class BeautyDetailFragment extends Fragment implements BeautyDetailContract.View{

    BeautyDetailContract.Presenter mPresenter;

    @BindView(R.id.view_pager)
    ViewPager viewPager;

    private SiglePicAdapt adapt;
    private int id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_beauty_pic_detail, container, false);
        ButterKnife.bind(this, view);

        Bundle bundle = getArguments();

        if(bundle != null){
            id = bundle.getInt("id");
        }

        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        mPresenter.start();
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);

        adapt = new SiglePicAdapt(getActivity());

        viewPager.setAdapter(adapt);

    }

    @Override
    public void showPicture(List<ShowBean.PictureEntity> datas) {
        adapt.setDatas(datas);
    }

    @Override
    public void setPresenter(BeautyDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void back() {
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(viewPager,message,Snackbar.LENGTH_LONG);
    }

    @Override
    public <V> Observable.Transformer<V, V> bind() {
        return null;
    }
}
