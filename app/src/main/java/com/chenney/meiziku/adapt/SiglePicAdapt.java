package com.chenney.meiziku.adapt;

import android.content.Context;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chenney.meiziku.R;
import com.chenney.meiziku.data.entity.ShowBean;
import com.chenney.meiziku.util.Constants;

import java.util.List;

/**
 * Created by Administrator on 2016/8/26.
 */
public class SiglePicAdapt extends PagerAdapter {

    private List<ShowBean.PictureEntity> mDatas;
    private ShowBean.PictureEntity mData;

    private Context mContext;


    public SiglePicAdapt(Context context) {
        mContext = context;
    }

    public void setDatas(List<ShowBean.PictureEntity> mDatas) {
        this.mDatas = mDatas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        mData = mDatas.get(position);

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_pic_cover, null);

        ImageView coverImage = (ImageView) view.findViewById(R.id.cover_image);
        TextView tvPicClassiftyNum = (TextView)view.findViewById(R.id.tv_picClassiftyNum);

        Uri uri = Uri.parse(Constants.MEIZI_IMAGE_BASE_URL + mData.getSrc());

        Glide.with(mContext)
                .load(uri)
                .into(coverImage);
        tvPicClassiftyNum.setText((position+1)+"/"+getCount());


        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
