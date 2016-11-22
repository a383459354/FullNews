package com.fullnews.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fullnews.entity.BookClassifyBeans;
import com.zh.fullnews.R;

import java.util.List;

/**
 * Created by Administrator on 2016/11/19 0019.
 */

public class BookClassifyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<BookClassifyBeans.MaleBean> male;
    private List<BookClassifyBeans.FemaleBean> female;
    private List<BookClassifyBeans.PressBean> press;
    private Context mContext;

    private BookClassifyAdapter.OnItemClickListener mOnItemClickListener;
    private BookClassifyAdapter.OnItemLongClickListener mOnItemLongClickListener;

    public BookClassifyAdapter(Context context, List<BookClassifyBeans.MaleBean> male, List<BookClassifyBeans.FemaleBean> female, List<BookClassifyBeans.PressBean> press) {
        this.mContext = context;
        this.male = male;
        this.female = female;
        this.press = press;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view;
        switch (viewType) {
            case 0:
                view = LayoutInflater.from(mContext).inflate(R.layout.item_other_channel_header, parent, false);
                return new BookClassifyAdapter.MenTitleHolder(view);
            case 1:
                view = LayoutInflater.from(mContext).inflate(R.layout.item_classify, parent, false);
                return new BookClassifyAdapter.MenContentHolder(view);

            case 2:
                view = LayoutInflater.from(mContext).inflate(R.layout.item_other_channel_header, parent, false);
                return new BookClassifyAdapter.WumenTitleHolder(view);

            case 3:
                view = LayoutInflater.from(mContext).inflate(R.layout.item_classify, parent, false);
                return new BookClassifyAdapter.WumenContentHolder(view);

            case 4:
                view = LayoutInflater.from(mContext).inflate(R.layout.item_other_channel_header, parent, false);
                return new BookClassifyAdapter.PublishTitleHolder(view);

            case 5:
                view = LayoutInflater.from(mContext).inflate(R.layout.item_classify, parent, false);
                return new BookClassifyAdapter.PublishContentHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MenTitleHolder) {
            ((MenTitleHolder) holder).tvHeader.setText("男生频道");
            ((MenTitleHolder) holder).tvHeader.setTextSize(18);
        } else if (holder instanceof MenContentHolder) {
            ((MenContentHolder) holder).tvContent.setText(male.get(position - 1).getName());
            clickListeer(holder);
        } else if (holder instanceof WumenTitleHolder) {
            ((WumenTitleHolder) holder).tvHeader.setText("女生频道");
            ((WumenTitleHolder) holder).tvHeader.setTextSize(18);
        } else if (holder instanceof WumenContentHolder) {
            ((WumenContentHolder) holder).tvContent.setText(female.get(position - male.size() - 2).getName());
            clickListeer(holder);
        } else if (holder instanceof PublishTitleHolder) {
            ((PublishTitleHolder) holder).tvHeader.setText("出版频道");
            ((PublishTitleHolder) holder).tvHeader.setTextSize(18);
        } else if (holder instanceof PublishContentHolder) {
            ((PublishContentHolder) holder).tvContent.setText(press.get(position - male.size() - female.size() - 3).getName());
            clickListeer(holder);
        }
    }

    @Override
    public int getItemCount() {
        return male.size() + female.size() + press.size() + 3;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {    // 男生 标题部分
            return 0;
        } else if (position == male.size() + 1) {    // 女生 标题部分
            return 2;
        } else if (position == male.size() + female.size() + 2) {
            return 4;
        } else if (position > 0 && position < male.size() + 1) {
            return 1;
        } else if (position > male.size() + 1 && position < male.size() + female.size() + 2) {
            return 3;
        } else {
            return 5;
        }
    }


    public class MenTitleHolder extends RecyclerView.ViewHolder {

        private TextView tvHeader;

        public MenTitleHolder(View itemView) {
            super(itemView);
            tvHeader = (TextView) itemView.findViewById(R.id.textView2);
        }
    }

    public class MenContentHolder extends RecyclerView.ViewHolder {
        private TextView tvContent;

        public MenContentHolder(View itemView) {
            super(itemView);
            tvContent = (TextView) itemView.findViewById(R.id.textview_content);
        }
    }

    public class WumenTitleHolder extends RecyclerView.ViewHolder {

        private TextView tvHeader;

        public WumenTitleHolder(View itemView) {
            super(itemView);
            tvHeader = (TextView) itemView.findViewById(R.id.textView2);
        }
    }

    public class WumenContentHolder extends RecyclerView.ViewHolder {
        private TextView tvContent;

        public WumenContentHolder(View itemView) {
            super(itemView);
            tvContent = (TextView) itemView.findViewById(R.id.textview_content);
        }
    }

    public class PublishTitleHolder extends RecyclerView.ViewHolder {

        private TextView tvHeader;

        public PublishTitleHolder(View itemView) {
            super(itemView);
            tvHeader = (TextView) itemView.findViewById(R.id.textView2);
        }
    }

    public class PublishContentHolder extends RecyclerView.ViewHolder {
        private TextView tvContent;

        public PublishContentHolder(View itemView) {
            super(itemView);
            tvContent = (TextView) itemView.findViewById(R.id.textview_content);
        }
    }

    public void setOnItemClickListener(BookClassifyAdapter.OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setOnItemLongClickListener(BookClassifyAdapter.OnItemLongClickListener mOnItemLongClickListener) {
        this.mOnItemLongClickListener = mOnItemLongClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(View view, int position);
    }

    private void clickListeer(final RecyclerView.ViewHolder holder) {
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
}
