package com.fullnews.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zh.fullnews.R;

import java.util.List;

/**
 * Created by Administrator on 2016/11/21 0021.
 */

public class BookSeekHistoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<String> mData;

    private BookSeekHistoryAdapter.OnItemClickListener mOnItemClickListener;
    private BookSeekHistoryAdapter.OnItemLongClickListener mOnItemLongClickListener;

    public BookSeekHistoryAdapter(Context context, List<String> data){
        this.mContext=context;
        this.mData=data;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.item_book_history_list,parent,false);
        return new BookSeekHistoryAdapter.ViewHolders(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BookSeekHistoryAdapter.ViewHolders){
            ((BookSeekHistoryAdapter.ViewHolders) holder).tv.setText(mData.get(position));
        }
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition();
                    Log.d("*--onclick", position + "------");
                    mOnItemClickListener.onItemHistoryClick(holder.itemView, position);
                }
            });
        }
        if (mOnItemLongClickListener != null) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int position = holder.getLayoutPosition();
                    mOnItemLongClickListener.onItemAutoLongClick(holder.itemView, position);
                    //返回true 表示消耗了事件 事件不会继续传递
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolders extends RecyclerView.ViewHolder {

        TextView tv;

        public ViewHolders(View itemView) {
            super(itemView);
            tv=(TextView)itemView.findViewById(R.id.textview_history);
        }
    }

    public void setOnItemClickListener(BookSeekHistoryAdapter.OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setOnItemLongClickListener(BookSeekHistoryAdapter.OnItemLongClickListener mOnItemLongClickListener) {
        this.mOnItemLongClickListener = mOnItemLongClickListener;
    }

    public interface OnItemClickListener {
        void onItemHistoryClick(View view, int position);
    }

    public interface OnItemLongClickListener {
        void onItemAutoLongClick(View view, int position);
    }
}

