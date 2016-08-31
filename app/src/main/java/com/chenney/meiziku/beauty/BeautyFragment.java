package com.chenney.meiziku.beauty;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chenney.meiziku.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/8/25.
 */
public class BeautyFragment extends Fragment {

    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;

    private String[] beautyTypes = {"性感美女", "日韩美女", "丝袜美腿", "美女照片", "美女写真", "清纯美女", "性感车模"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_beauty, null);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);

        mViewPager.setAdapter(new FragmentPagerAdapter(getFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return BeautyPicFragmentContent.newInstance(position);
            }

            @Override
            public int getCount() {
                return beautyTypes.length;
            }

            @Override
            public CharSequence getPageTitle(int position){
                return beautyTypes[position];
            }
        });

        mTabLayout.setupWithViewPager(mViewPager);
    }
}
