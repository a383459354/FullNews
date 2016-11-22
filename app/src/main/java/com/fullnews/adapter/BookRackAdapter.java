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
import com.fullnews.entity.ReadHistoryEntity;
import com.zh.fullnews.R;

import java.util.List;

/**
 * Created by Administrator on 2016/11/22 0022.
 */

public class BookRackAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ReadHistoryEntity> mList;
    private Context mContext;

    private BookRackAdapter.OnItemClickListener mOnItemClickListener;
    private BookRackAdapter.OnItemLongClickListener mOnItemLongClickListener;

    public BookRackAdapter(Context context, List<ReadHistoryEntity> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_book_rack_list, parent, false);
        return new BookRackAdapter.BookRackHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BookRackAdapter.BookRackHolder) {
            ((BookRackAdapter.BookRackHolder) holder).tvTitle.setText(mList.get(position).getName());
            ((BookRackAdapter.BookRackHolder) holder).tcAuther.setText(mList.get(position).getAuther());
            ((BookRackAdapter.BookRackHolder) holder).tvChapter.setText(mList.get(position).getChatper());
            ImgGlide(mList.get(position).getImg(), ((BookRackAdapter.BookRackHolder) holder).ivBook);
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

    public class BookRackHolder extends RecyclerView.ViewHolder {

        TextView tvTitle, tcAuther, tvChapter;
        ImageView ivBook;

        public BookRackHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.textView_book_rack_title);
            tcAuther = (TextView) itemView.findViewById(R.id.textView_book_auther);
            tvChapter = (TextView) itemView.findViewById(R.id.textView_book_chatper);
            ivBook = (ImageView) itemView.findViewById(R.id.imageview_book_rack);
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

    public void setOnItemClickListener(BookRackAdapter.OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setOnItemLongClickListener(BookRackAdapter.OnItemLongClickListener mOnItemLongClickListener) {
        this.mOnItemLongClickListener = mOnItemLongClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(View view, int position);
    }
}
