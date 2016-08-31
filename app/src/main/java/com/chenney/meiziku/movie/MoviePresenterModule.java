package com.chenney.meiziku.movie;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2016/8/29.
 */
@Module
public class MoviePresenterModule {

    private final MovieContract.View mView;

    public MoviePresenterModule(MovieContract.View mView) {
        this.mView = mView;
    }

    @Provides
    MovieContract.View provideMovieContractView(){
        return mView;
    }
}
