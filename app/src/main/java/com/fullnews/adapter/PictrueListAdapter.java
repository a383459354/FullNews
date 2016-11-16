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
import com.fullnews.entity.PictrueBeans;
import com.zh.fullnews.R;

import java.util.List;

/**
 * Created by Administrator on 2016/11/7 0007.
 */

public class PictrueListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<PictrueBeans.DataBean.ArticleBean> mList;
    private Context mContext;

    private PictrueListAdapter.OnItemClickListener mOnItemClickListener;
    private PictrueListAdapter.OnItemLongClickListener mOnItemLongClickListener;

    public PictrueListAdapter(Context context, List<PictrueBeans.DataBean.ArticleBean> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == 0) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_footer, parent, false);
            return new PictrueListAdapter.Footer(view);
        }
        view = LayoutInflater.from(mContext).inflate(R.layout.item_picture_list, parent, false);
        return new PictrueListAdapter.PictrueHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof PictrueListAdapter.PictrueHolder) {
            Log.d("title---", position + "--" + mList.get(position).getTitle());
            ((PictrueHolder) holder).tvTitle.setText(mList.get(position).getTitle());
            ((PictrueHolder) holder).tvPictrueSource.setText(mList.get(position).getSource_name());
//            ((PictrueHolder) holder).tvPictruePubDate.setText(ConversionTime.pubDate(mList.get(position).getGrab_time()));
            ImgGlide(mList.get(position).getImage().get(0).getUrl(), ((PictrueHolder) holder).ivPictrue);
        }
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition();
                    Log.d("*--onclick", position + "------");
                    mOnItemClickListener.onItemClick(holder.itemView, position);
                }
            });
        }
        if (mOnItemLongClickListener != null) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int position = holder.getLayoutPosition();
                    mOnItemLongClickListener.onItemLongClick(holder.itemView, position);
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

    public class PictrueHolder extends RecyclerView.ViewHolder {

        TextView tvTitle, tvPictrueSource, tvPictruePubDate;
        ImageView ivPictrue;

        public PictrueHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.textview_pictrue_title);
            tvPictrueSource = (TextView) itemView.findViewById(R.id.textview_pictrue_source);
            tvPictruePubDate = (TextView) itemView.findViewById(R.id.textview_pictrue_pubDate);
            ivPictrue = (ImageView) itemView.findViewById(R.id.imageview_pivtrue_img);
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

    public void setOnItemClickListener(PictrueListAdapter.OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setOnItemLongClickListener(PictrueListAdapter.OnItemLongClickListener mOnItemLongClickListener) {
        this.mOnItemLongClickListener = mOnItemLongClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(View view, int position);
    }

}
