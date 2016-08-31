package com.chenney.meiziku.data.source;

import com.chenney.meiziku.data.entity.ClassifyBean;
import com.chenney.meiziku.data.entity.ShowBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/8/24.
 */
public interface MeiziApiService {

    @GET("tnfs/api/list")
    Observable<ClassifyBean> getPicClassify(@Query("id") int id,@Query("page")int page);

    //分类中图片详细内容列表
    @GET("tnfs/api/show")
    Observable<ShowBean> getPicShow(@Query("id") long id);
}
