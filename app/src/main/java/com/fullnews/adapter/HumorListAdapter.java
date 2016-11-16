package com.fullnews.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.fullnews.entity.HumorBeans;
import com.zh.fullnews.R;

import java.util.List;

/**
 * Created by Administrator on 2016/11/8 0008.
 */

public class HumorListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<HumorBeans.DataBean.ArticleBean> mList;
    private Context mContext;

    public HumorListAdapter(Context context, List<HumorBeans.DataBean.ArticleBean> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == 0) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_footer, parent, false);
            return new HumorListAdapter.Footer(view);
        }
        view = LayoutInflater.from(mContext).inflate(R.layout.item_humor_list, parent, false);
        return new HumorListAdapter.HumorHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HumorListAdapter.HumorHolder) {
            ((HumorListAdapter.HumorHolder) holder).tvContent.setText(mList.get(position).getSummary());
            ((HumorListAdapter.HumorHolder) holder).tvPictrueSource.setText(mList.get(position).getSource_name());
//            ((HumorHolder) holder).tvPictruePubDate.setText(ConversionTime.pubDate(mList.get(position).getGrab_time()));
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

    public class HumorHolder extends RecyclerView.ViewHolder {

        TextView tvContent, tvPictrueSource, tvPictruePubDate;

        public HumorHolder(View itemView) {
            super(itemView);
            tvContent = (TextView) itemView.findViewById(R.id.textview_humor_content);
            tvPictrueSource = (TextView) itemView.findViewById(R.id.textview_humor_source);
            tvPictruePubDate = (TextView) itemView.findViewById(R.id.textview_humor_pubDate);
        }
    }
}
