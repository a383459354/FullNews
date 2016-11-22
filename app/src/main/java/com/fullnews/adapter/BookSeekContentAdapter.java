package com.fullnews.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fullnews.entity.BookClassifyContentBeans;
import com.zh.fullnews.R;

import java.util.List;

/**
 * Created by Administrator on 2016/11/21 0021.
 */

public class BookSeekContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<BookClassifyContentBeans.BooksBean> mList;
    private Context mContext;

    private BookSeekContentAdapter.OnItemClickListener mOnItemClickListener;
    private BookSeekContentAdapter.OnItemLongClickListener mOnItemLongClickListener;

    public BookSeekContentAdapter(Context context, List<BookClassifyContentBeans.BooksBean> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_book_store_list, parent, false);
        return new BookSeekContentAdapter.BookSeekContentHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BookSeekContentAdapter.BookSeekContentHolder) {
            ((BookSeekContentAdapter.BookSeekContentHolder) holder).tvTitle.setText(mList.get(position).getTitle());
            ((BookSeekContentAdapter.BookSeekContentHolder) holder).tcAuther.setText(mList.get(position).getAuthor());
            ((BookSeekContentAdapter.BookSeekContentHolder) holder).tvBookRead.setText(mList.get(position).getLatelyFollower() + "人在看");
            ((BookSeekContentAdapter.BookSeekContentHolder) holder).tvBookInfo.setText(mList.get(position).getShortIntro());
            ImgGlide(mList.get(position).getCover(), ((BookSeekContentAdapter.BookSeekContentHolder) holder).ivBook);
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

    public class BookSeekContentHolder extends RecyclerView.ViewHolder {

        TextView tvTitle, tcAuther, tvBookRead, tvBookInfo;
        ImageView ivBook;

        public BookSeekContentHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.textView_book_store_title);
            tcAuther = (TextView) itemView.findViewById(R.id.textView_book_auther);
            tvBookRead = (TextView) itemView.findViewById(R.id.textView_book_read);
            tvBookInfo = (TextView) itemView.findViewById(R.id.textView_book_info);
            ivBook = (ImageView) itemView.findViewById(R.id.imageview_book_store);
        }
    }

    public void ImgGlide(String url, ImageView imageView) {
        url = "http://statics.zhuishushenqi.com" + url;
        Glide.with(mContext)
                .load(url)
                .centerCrop()
                .placeholder(R.mipmap.ic_crop_original_white_48dp)
                .crossFade()
                .into(imageView);
    }

    public void setOnItemClickListener(BookSeekContentAdapter.OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setOnItemLongClickListener(BookSeekContentAdapter.OnItemLongClickListener mOnItemLongClickListener) {
        this.mOnItemLongClickListener = mOnItemLongClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(View view, int position);
    }
}
