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
import com.fullnews.entity.JiaodianBeans;
import com.zh.fullnews.R;

import java.util.List;

/**
 * Created by Administrator on 2016/11/16 0016.
 */

public class JiaodianAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    //定义一个集合，接收从Activity中传递过来的数据和上下文
    private List<JiaodianBeans.DataBean> mList;
    private Context mContext;

    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    public JiaodianAdapter(Context context, List<JiaodianBeans.DataBean> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        Log.d("viewtype123456", viewType + "qwwwwwwwwww");
        if (viewType == 1) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_news_list0, parent, false);
            return new FirstHolder(view);
        } else if (viewType == 2) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_news_list1, parent, false);
            return new SecondHolder(view);
        } else if (viewType == 5) {
            Log.d("++++++++++++++++++++++", "+++++++++++++++++++++++++++");
            view = LayoutInflater.from(mContext).inflate(R.layout.item_footer, parent, false);
            return new Footer(view);
        } else {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof FirstHolder) {
            ((FirstHolder) holder).tvTitle.setText(mList.get(position).getTitle());
            ((FirstHolder) holder).tvSource.setText(mList.get(position).getWriter());
            ((FirstHolder) holder).tvPubDate.setText(mList.get(position).getNewstime());
        } else if (holder instanceof SecondHolder) {
            ((SecondHolder) holder).ivImg.setImageResource(R.mipmap.ic_crop_original_white_48dp);
            String url = mList.get(position).getTitlepic();
            Log.d("+++++url++++", "+++++" + url);
            ImgGlide(url, ((SecondHolder) holder).ivImg);
            ((SecondHolder) holder).tvTitle.setText(mList.get(position).getTitle());
            ((SecondHolder) holder).tvSource.setText(mList.get(position).getWriter());
            ((SecondHolder) holder).tvPubDate.setText(mList.get(position).getNewstime());
        }  else {
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
    public int getItemViewType(int position) {
        Log.d("position", position + "qwewqwqeeq");
        if (position + 1 == getItemCount()) {
            Log.d("footer", "-------------------------------");
            return 5;
        }
        if (mList.get(position).getTitlepic()!=null) {
            return 2;
        }  else {
            return 1;
        }
    }


    public class Footer extends RecyclerView.ViewHolder {
        ProgressBar pbBar;

        public Footer(View itemView) {
            super(itemView);
            pbBar = (ProgressBar) itemView.findViewById(R.id.pbBar);
        }
    }

    public class FirstHolder extends RecyclerView.ViewHolder {

        TextView tvTitle, tvSource, tvPubDate;

        public FirstHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvSource = (TextView) itemView.findViewById(R.id.tvSource);
            tvPubDate = (TextView) itemView.findViewById(R.id.tvPubDate);
        }
    }

    public class SecondHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvSource, tvPubDate;
        ImageView ivImg;

        public SecondHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvSource = (TextView) itemView.findViewById(R.id.tvSource);
            tvPubDate = (TextView) itemView.findViewById(R.id.tvPubDate);
            ivImg = (ImageView) itemView.findViewById(R.id.ivImg);
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

    public void setOnItemClickListener(JiaodianAdapter.OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setOnItemLongClickListener(JiaodianAdapter.OnItemLongClickListener mOnItemLongClickListener) {
        this.mOnItemLongClickListener = mOnItemLongClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(View view, int position);
    }

}
