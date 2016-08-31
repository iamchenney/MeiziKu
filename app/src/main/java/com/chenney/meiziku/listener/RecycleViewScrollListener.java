package com.chenney.meiziku.listener;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Administrator on 2016/8/26.
 */
public abstract class RecycleViewScrollListener extends RecyclerView.OnScrollListener {

    private Context mContext;

    private int previousTotal = 0;
    private boolean loading = true;
    int firstVisibleItem, visibleItemCount, totalItemCount;

    private int currentPage = 1;

    public RecycleViewScrollListener(Context context){
        mContext = context;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView,int newState){
        super.onScrollStateChanged(recyclerView,newState);
    }

    @Override
    public void onScrolled(RecyclerView recyclerView,int dx,int dy){
        super.onScrolled(recyclerView,dx,dy);

        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);

        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = layoutManager.getItemCount();
        firstVisibleItem = layoutManager.findFirstVisibleItemPosition();

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false;
                previousTotal = totalItemCount;
            }
        }
        if (!loading
                && (totalItemCount - visibleItemCount) <= firstVisibleItem) {
            currentPage++;
            onLoad(currentPage);
            loading = true;
        }
    }

    public abstract void onLoad(int currentPage);
}
