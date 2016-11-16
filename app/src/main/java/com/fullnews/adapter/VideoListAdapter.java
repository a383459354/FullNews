package com.fullnews.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fullnews.entity.WYVideoBeans;
import com.fullnews.utils.ConversionTime;
import com.zh.fullnews.R;

import java.util.List;

/**
 * Created by Administrator on 2016/11/1 0001.
 */

public class VideoListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<WYVideoBeans.V9LG4B3A0Bean> mList;
    private Context mContext;

    private VideoListAdapter.OnItemClickListener mOnItemClickListener;
    private VideoListAdapter.OnItemLongClickListener mOnItemLongClickListener;

    public VideoListAdapter(Context context, List<WYVideoBeans.V9LG4B3A0Bean> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == 0) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_footer, parent, false);
            return new VideoListAdapter.Footer(view);
        }
        view = LayoutInflater.from(mContext).inflate(R.layout.item_video_list, parent, false);
        return new VideoListAdapter.VideoHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof VideoHolder) {
            ((VideoHolder) holder).tvTitle.setText(mList.get(position).getTitle());
            ((VideoHolder) holder).tvVideoDate.setText(ConversionTime.VideoLength(mList.get(position).getLength()));
            ((VideoHolder) holder).tvVideoSource.setText(mList.get(position).getTopicName());
            ((VideoHolder) holder).tvVideoPubDate.setText(ConversionTime.pubDate(mList.get(position).getPtime()));
            ImgGlide(mList.get(position).getCover(),((VideoHolder) holder).ivVideo);
        }
        if (mOnItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition();
                    Log.d("*--onclick",position+"------");
                    mOnItemClickListener.onItemClick(holder.itemView,position);
                }
            });
        }
        if (mOnItemLongClickListener!=null){
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int position = holder.getLayoutPosition();
                    mOnItemLongClickListener.onItemLongClick(holder.itemView,position);
                    //返回true 表示消耗了事件 事件不会继续传递
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            Log.d("footer", "-------------------------------");
            return 0;
        }
        return 1;
    }

    public class Footer extends RecyclerView.ViewHolder {
        ProgressBar pbBar;

        public Footer(View itemView) {
            super(itemView);
            pbBar = (ProgressBar) itemView.findViewById(R.id.pbBar);
        }
    }

    public class VideoHolder extends RecyclerView.ViewHolder {

        TextView tvTitle, tvVideoDate,tvVideoSource,tvVideoPubDate;
        ImageView ivVideo;

        public VideoHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.textview_video_title);
            tvVideoDate = (TextView) itemView.findViewById(R.id.textview_video_time);
            tvVideoSource = (TextView) itemView.findViewById(R.id.textView_video_Source);
            tvVideoPubDate = (TextView) itemView.findViewById(R.id.textView_video_pubdate);
            ivVideo=(ImageView)itemView.findViewById(R.id.imageView_item_video);
        }
    }

    public void ImgGlide(String url, ImageView imageView) {
        Log.d("positio----", "qwewqwqeeq------------------");
        Glide.with(mContext)
                .load(url)
                .centerCrop()
                .placeholder(R.mipmap.ic_crop_original_white_48dp)
                .crossFade()
                .into(imageView);
    }

    public void setOnItemClickListener(VideoListAdapter.OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setOnItemLongClickListener(VideoListAdapter.OnItemLongClickListener mOnItemLongClickListener) {
        this.mOnItemLongClickListener = mOnItemLongClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(View view, int position);
    }

}
