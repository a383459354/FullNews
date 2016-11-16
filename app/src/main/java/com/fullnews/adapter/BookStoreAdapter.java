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
import com.fullnews.entity.BookHomeBeans;
import com.zh.fullnews.R;

import java.util.List;

/**
 * Created by Administrator on 2016/11/11 0011.
 */

public class BookStoreAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<BookHomeBeans.DataBean.PhBean.BookListBean> mList;
    private Context mContext;

    private BookStoreAdapter.OnItemClickListener mOnItemClickListener;
    private BookStoreAdapter.OnItemLongClickListener mOnItemLongClickListener;

    public BookStoreAdapter(Context context, List<BookHomeBeans.DataBean.PhBean.BookListBean> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == 0) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_footer, parent, false);
            return new BookStoreAdapter.Footer(view);
        }
        view = LayoutInflater.from(mContext).inflate(R.layout.item_book_store_list, parent, false);
        return new BookStoreAdapter.BookStoreHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BookStoreAdapter.BookStoreHolder) {
            Log.d("title---", position + "--" + mList.get(position).getBookname());
            ((BookStoreAdapter.BookStoreHolder) holder).tvTitle.setText(mList.get(position).getBookname());
            ((BookStoreAdapter.BookStoreHolder) holder).tvBookRead.setText(mList.get(position).getNum_click()+"次阅读");
            ((BookStoreAdapter.BookStoreHolder) holder).tvBookInfo.setText(mList.get(position).getBook_info());
            ImgGlide(mList.get(position).getBook_cover(), ((BookStoreHolder) holder).ivBook);
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

    public class BookStoreHolder extends RecyclerView.ViewHolder {

        TextView tvTitle, tvBookRead, tvBookInfo;
        ImageView ivBook;

        public BookStoreHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.textView_book_store_title);
            tvBookRead = (TextView) itemView.findViewById(R.id.textView_book_read);
            tvBookInfo = (TextView) itemView.findViewById(R.id.textView_book_info);
            ivBook = (ImageView) itemView.findViewById(R.id.imageview_book_store);
        }
    }

    public void ImgGlide(String url, ImageView imageView) {
        url="http://oss-asq-img.11222.cn/bcv/middle/"+url;
        Glide.with(mContext)
                .load(url)
                .centerCrop()
                .placeholder(R.mipmap.ic_crop_original_white_48dp)
                .crossFade()
                .into(imageView);
    }

    public void setOnItemClickListener(BookStoreAdapter.OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setOnItemLongClickListener(BookStoreAdapter.OnItemLongClickListener mOnItemLongClickListener) {
        this.mOnItemLongClickListener = mOnItemLongClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(View view, int position);
    }

}
