package com.chenney.meiziku.data.source;

import com.chenney.meiziku.data.entity.douban.Response;
import com.chenney.meiziku.data.entity.douban.Subject;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/8/24.
 */
public interface MovieApiService {

    @GET("v2/movie/top250")
    Observable<Response<Subject>> getTop250(@Query("start")int start, @Query("count")int count);
}
