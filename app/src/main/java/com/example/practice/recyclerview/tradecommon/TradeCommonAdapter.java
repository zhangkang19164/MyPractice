package com.example.practice.recyclerview.tradecommon;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.TextAppearanceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.practice.R;
import com.example.practice.recyclerview.tradecommon.bean.TradeCommonBaseBean;
import com.example.practice.recyclerview.tradecommon.bean.TradeCommonListBean;
import com.example.practice.recyclerview.tradecommon.bean.TradeCommonTitleBean;

import java.util.List;

/**
 * 交易通用Adapter
 */

public class TradeCommonAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<TradeCommonBaseBean> baseBeanList;
    private Context mContext;
    private OnItemClickListener onItemClickListener;

    public TradeCommonAdapter(Context mContext, List<TradeCommonBaseBean> baseBeanList) {
        this.mContext = mContext;
        this.baseBeanList = baseBeanList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TradeCommonBaseBean.BEAN_TYPE_TITLE) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_trade_common_title, parent, false);
            return new TradeCommonTitleViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_trade_common_list, parent, false);
            return new TradeCommonListViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != onItemClickListener) {
                    int position = holder.getAdapterPosition();
                    onItemClickListener.onClick(v, position, baseBeanList.get(position));
                }
            }
        });
        int viewType = getItemViewType(position);
        if (viewType == TradeCommonBaseBean.BEAN_TYPE_TITLE) {
            TradeCommonTitleViewHolder viewHolder = (TradeCommonTitleViewHolder) holder;
            TradeCommonTitleBean bean = (TradeCommonTitleBean) baseBeanList.get(position);
            viewHolder.textView.setText(bean.getTitleName());
            return;
        }
        if (viewType == TradeCommonBaseBean.BEAN_TYPE_LIST) {
            TradeCommonListViewHolder viewHolder = (TradeCommonListViewHolder) holder;
            TradeCommonListBean bean = (TradeCommonListBean) baseBeanList.get(position);
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(bean.getTitle() + "\n");
            spannableStringBuilder.append(chanTxTStyle(mContext, bean.getSubTitle(), R.style.trade_common_subtitle));
            viewHolder.textView.setText(spannableStringBuilder);
        }
    }

    @Override
    public int getItemCount() {
        return null == baseBeanList ? 0 : baseBeanList.size();
    }

    @Override
    public int getItemViewType(int position) {
        TradeCommonBaseBean baseBean = baseBeanList.get(position);
        if (baseBean instanceof TradeCommonTitleBean) {
            return TradeCommonBaseBean.BEAN_TYPE_TITLE;
        } else {
            return TradeCommonBaseBean.BEAN_TYPE_LIST;
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    //改变字体样式
    private SpannableString chanTxTStyle(Context context, String txt, int style) {
        SpannableString spannableString = new SpannableString(txt);
        spannableString.setSpan(new TextAppearanceSpan(context, style), 0, txt.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    private static class TradeCommonTitleViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public TradeCommonTitleViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.trade_common_title);
        }
    }

    private static class TradeCommonListViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public TradeCommonListViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.trade_common_list);
        }
    }

    public interface OnItemClickListener {
        void onClick(View view, int position, TradeCommonBaseBean baseBean);
    }
}
