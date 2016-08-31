package com.chenney.meiziku.data.entity;

import android.support.v4.app.Fragment;

/**
 * Created by Administrator on 2016/8/24.
 */
public class TabHostItem {
    private String title;
    private int imageResource;
    private Class<? extends  Fragment> fragmentClass;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public Class<? extends Fragment> getFragmentClass() {
        return fragmentClass;
    }

    public void setFragmentClass(Class<? extends Fragment> fragmentClass) {
        this.fragmentClass = fragmentClass;
    }

    public TabHostItem(String title, int imageResource, Class<? extends Fragment> fragmentClass) {

        this.title = title;
        this.imageResource = imageResource;
        this.fragmentClass = fragmentClass;
    }
}
