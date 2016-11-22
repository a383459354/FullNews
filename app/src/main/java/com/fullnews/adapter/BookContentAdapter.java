package com.fullnews.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fullnews.entity.BookContentBeans;
import com.zh.fullnews.R;

import java.util.List;

/**
 * Created by Administrator on 2016/11/22 0022.
 */

public class BookContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<BookContentBeans.ChapterBean> mList;
    private Context mContext;

    private BookContentAdapter.OnItemClickListener mOnItemClickListener;
    private BookContentAdapter.OnItemLongClickListener mOnItemLongClickListener;

    public BookContentAdapter(Context context, List<BookContentBeans.ChapterBean> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_book_content_list, parent, false);
        return new BookContentAdapter.BookContentHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BookContentAdapter.BookContentHolder) {
            ((BookContentAdapter.BookContentHolder) holder).tvTitle.setText(mList.get(position).getBody());
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

    public class BookContentHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;

        public BookContentHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.textview_read_content);
        }
    }

    public void setOnItemClickListener(BookContentAdapter.OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setOnItemLongClickListener(BookContentAdapter.OnItemLongClickListener mOnItemLongClickListener) {
        this.mOnItemLongClickListener = mOnItemLongClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(View view, int position);
    }
}
