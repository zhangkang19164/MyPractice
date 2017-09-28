package com.example.system.recyclerview.tradecommon.itemdecoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.system.R;


/**
 * 交易通用分隔线
 */

public class TradeCommonItemDecoration extends RecyclerView.ItemDecoration {


    private TradeCommonItemDecorationCallBack callBack;
    private Context mContext;
    private Paint mPaint;
    //组之间的分隔线高度
    private int groupDividerHeight;
    //组内分隔线高度
    private int itemDividerHeight;

    public TradeCommonItemDecoration(Context context, TradeCommonItemDecorationCallBack callBack) {
        this(context, callBack, R.dimen.group_divider_height, R.dimen.item_divider_height);
    }

    public TradeCommonItemDecoration(Context context, TradeCommonItemDecorationCallBack callBack, int groupDividerHeight, int itemDividerHeight) {
        this.mContext = context;
        this.callBack = callBack;
        mPaint = new Paint();
        this.groupDividerHeight = mContext.getResources().getDimensionPixelSize(groupDividerHeight);
        this.itemDividerHeight = mContext.getResources().getDimensionPixelSize(itemDividerHeight);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildAdapterPosition(view);
        if (isNeedDrawGroupDivider(position)) {
            outRect.top = groupDividerHeight;
        }
        outRect.bottom = itemDividerHeight;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        drawItemDivider(c, parent);
//        drawGroupDivider(c, parent);
    }
    //绘制组之间的分隔线
    private void drawGroupDivider(Canvas c, RecyclerView parent){
        int count = parent.getChildCount();
        mPaint.setColor(ContextCompat.getColor(mContext, R.color.colorPrimary));
        for (int i = 0; i < count; i++) {
            View view = parent.getChildAt(i);
            int position = parent.getChildAdapterPosition(view);
            if (isNeedDrawGroupDivider(position)) {
                c.drawRect(view.getLeft(),view.getTop()-groupDividerHeight,view.getRight(),view.getTop(),mPaint);
            }
        }
    }
    //绘制组内分隔线
    private void drawItemDivider(Canvas c, RecyclerView parent) {
        int count = parent.getChildCount();
        mPaint.setColor(ContextCompat.getColor(mContext, R.color._E0E0E0));
        for (int i = 0; i < count; i++) {
            View view = parent.getChildAt(i);
            int position = parent.getChildAdapterPosition(view);
            if (!callBack.isItemNeedDrawDivider(position)) {
                continue;
            }
            if (!callBack.isInGroupEnd(position)) {
                c.drawRect(view.getLeft(), view.getBottom(), view.getRight(), view.getBottom() + itemDividerHeight, mPaint);
            }
        }
    }

    //判断是否需要绘制组之间的分隔线
    private boolean isNeedDrawGroupDivider(int position) {
        return position != 0 && callBack.isInGroupFirst(position);
    }

    public interface TradeCommonItemDecorationCallBack {
        //是否是分组中的第一个
        boolean isInGroupFirst(int position);

        //是否是分组中的最后一个
        boolean isInGroupEnd(int position);

        //单个item是否需要绘制分隔线
        boolean isItemNeedDrawDivider(int position);
    }
}
