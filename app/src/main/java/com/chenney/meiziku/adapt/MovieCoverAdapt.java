package com.chenney.meiziku.adapt;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chenney.meiziku.R;
import com.chenney.meiziku.data.entity.douban.Subject;
import com.chenney.meiziku.data.entity.douban.User;
import com.chenney.meiziku.widget.LabelView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/8/30.
 */
public class MovieCoverAdapt extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<Subject> mDatas;
    private Subject mData;
    private Context mContext;

    private ItemClickListener itemClickListener;

    public MovieCoverAdapt(Context mContext) {
        this.mContext = mContext;
    }

    public void setmDatas(List<Subject> mDatas) {
        this.mDatas = mDatas;
        this.notifyDataSetChanged();
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.movie_cover_item, null);
        return new ChildViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        mData = mDatas.get(position);

        ChildViewHolder viewHolder = (ChildViewHolder) holder;

        Uri uri = Uri.parse(mData.getImages().getLarge());

        Glide.with(mContext)
                .load(uri).into(viewHolder.movieCoverImg);
        User[] directors = mData.getDirectors();
        StringBuilder sb = new StringBuilder();
        for (User user : directors) {
            sb.append(user.getName());
            sb.append("; ");
        }
        viewHolder.movieDirector.setText(sb.toString());

        sb.delete(0,sb.length());

        User[] actors = mData.getCasts();
        for (User actor : actors) {
            sb.append(actor.getName());
            sb.append("; ");
        }
        viewHolder.movieActor.setText(sb.toString());

        viewHolder.movieName.setText(mData.getTitle());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClick(position,v,mDatas.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    public interface ItemClickListener{
        void onItemClick(int position, View view, Subject subject);
    }

    static class ChildViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.movie_cover_img)
        ImageView movieCoverImg;
        @BindView(R.id.movie_name)
        LabelView movieName;
        @BindView(R.id.movie_director)
        LabelView movieDirector;
        @BindView(R.id.movie_actor)
        LabelView movieActor;

        public ChildViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
