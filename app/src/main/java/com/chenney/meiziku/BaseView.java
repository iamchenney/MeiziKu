package com.chenney.meiziku;

import rx.Observable;

/**
 * Created by Administrator on 2016/8/25.
 */
public interface BaseView {

    void showMessage(String message);

    <V>Observable.Transformer<V,V> bind();
}
