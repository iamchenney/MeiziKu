package com.chenney.meiziku;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.chenney.meiziku.beauty.BeautyFragment;
import com.chenney.meiziku.beauty.beautyleg.BeautylegFragment;
import com.chenney.meiziku.beauty.uniform.UniformFragment;
import com.chenney.meiziku.data.entity.TabHostItem;
import com.chenney.meiziku.data.source.MeiziApiService;
import com.chenney.meiziku.movie.MovieFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MeiziActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.realtabcontent)
    FrameLayout realtabcontent;
    @BindView(android.R.id.tabcontent)
    FrameLayout tabcontent;
    @BindView(android.R.id.tabhost)
    FragmentTabHost mTabHost;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    private ActionBarDrawerToggle mToggle;

    private LayoutInflater mInflater;

    private List<TabHostItem> mTabs = new ArrayList<>(5);

    @Inject
    public MeiziApiService meiziApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meizi);
        ButterKnife.bind(this);

        initView();

        initTab();

    }

    /**
     * 初始化布局控件
     */
    private void initView() {
        setupDrawerContent(navView);
        setSupportActionBar(toolbar);

        mToggle = new ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.open,R.string.close);
        mToggle.syncState();

    }

    private void initTab() {
        TabHostItem home = new TabHostItem("美女", R.drawable.tab_main, BeautyFragment.class);
        TabHostItem hot = new TabHostItem("电影", R.drawable.tab_message, MovieFragment.class);
        TabHostItem hot1 = new TabHostItem("丝袜美腿", R.drawable.tab_message, BeautylegFragment.class);
        TabHostItem hot2 = new TabHostItem("制服诱惑", R.drawable.tab_message, UniformFragment.class);

        mTabs.add(home);
        mTabs.add(hot);
        mTabs.add(hot1);
        mTabs.add(hot2);

        mInflater = LayoutInflater.from(this);

        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        for (TabHostItem tab : mTabs) {
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(tab.getTitle());
            tabSpec.setIndicator(builderIndiator(tab));
            mTabHost.addTab(tabSpec, tab.getFragmentClass(), null);
        }

        if (Build.VERSION.SDK_INT >= 11) {
            //去掉分割线
            mTabHost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
        }
        mTabHost.setCurrentTab(0);
    }

    /**
     * 创建indiator
     *
     * @param tab
     * @return
     */
    private View builderIndiator(TabHostItem tab) {
        View view = mInflater.inflate(R.layout.bottom_tab_item, null);

        ImageView img = (ImageView) view.findViewById(R.id.imageview);
        TextView text = (TextView) view.findViewById(R.id.textview);
        img.setBackgroundResource(tab.getImageResource());
        text.setText(tab.getTitle());

        return view;
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                item.setChecked(true);
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }
}
