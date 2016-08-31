package com.chenney.meiziku.adapt;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chenney.meiziku.R;
import com.chenney.meiziku.data.entity.ClassifyBean;
import com.chenney.meiziku.util.Constants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/8/25.
 */
public class PicCoverAdapt extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;

    private List<ClassifyBean.TngouEntity> mDatas;
    private ClassifyBean.TngouEntity mData;

    private PicCoverItemClickListener listener;

    public PicCoverAdapt(Context context){
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_pic_cover, null);
        return new ChildViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        mData = mDatas.get(position);

        ChildViewHolder holder = (ChildViewHolder) viewHolder;

        Uri uri = Uri.parse(Constants.MEIZI_IMAGE_BASE_URL + mData.getImg());

        Glide.with(mContext).load(uri).into(holder.coverImage);

        holder.tvPicClassiftyNum.setText(mData.getSize() + "张");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    listener.onItemClick(v,position,mDatas.get(position));
                }
            }
        });

    }

    public void setDatas(List<ClassifyBean.TngouEntity> datas) {
        this.mDatas = datas;
        this.notifyDataSetChanged();
    }

    public void setItemClickListener(PicCoverItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    public interface PicCoverItemClickListener{
        void onItemClick(View view,int position,ClassifyBean.TngouEntity data);
    }

    static class ChildViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cover_image)
        ImageView coverImage;
        @BindView(R.id.tv_picClassiftyNum)
        TextView tvPicClassiftyNum;

        public ChildViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
