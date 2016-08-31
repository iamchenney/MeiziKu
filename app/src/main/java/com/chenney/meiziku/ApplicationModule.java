package com.chenney.meiziku;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2016/8/24.
 */
@Module
public class ApplicationModule {

    private final Context mContext;

    public ApplicationModule(Context context){
        mContext = context;
    }

    @Provides
    Context provideContext(){
        return mContext;
    }
}
