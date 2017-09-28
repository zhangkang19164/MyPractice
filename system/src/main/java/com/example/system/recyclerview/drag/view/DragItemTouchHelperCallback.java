package com.example.system.recyclerview.drag.view;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * create time : 2017/09/08
 * desc        :
 */

public class DragItemTouchHelperCallback extends ItemTouchHelper.Callback {

    private OnItemTouchCallbackListener onItemTouchCallbackListener;
    //是否可以拖拽
    private boolean isCanDrag = true;
    //是否可以被滑动
    private boolean isCanSwiped = true;

    public DragItemTouchHelperCallback(OnItemTouchCallbackListener onItemTouchCallbackListener) {
        this.onItemTouchCallbackListener = onItemTouchCallbackListener;
    }

    public void setOnItemTouchCallbackListener(OnItemTouchCallbackListener onItemTouchCallbackListener) {
        this.onItemTouchCallbackListener = onItemTouchCallbackListener;
    }

    public OnItemTouchCallbackListener getOnItemTouchCallbackListener() {
        return onItemTouchCallbackListener;
    }

    public void setCanDrag(boolean canDrag) {
        isCanDrag = canDrag;
    }

    public void setCanSwiped(boolean canSwiped) {
        isCanSwiped = canSwiped;
    }

    //是否支持拖动
    @Override
    public boolean isLongPressDragEnabled() {
        return isCanDrag;
    }

    //是否支持滑动删除
    @Override
    public boolean isItemViewSwipeEnabled() {
        return isCanSwiped;
    }

    /**
     * 通过返回值来设置是否处理某次拖曳或者滑动事件
     *
     * @return 返回0 表示不不处理
     */
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            int dragFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            int swipedFlags = 0;
            return makeMovementFlags(dragFlags, swipedFlags);
        } else if (layoutManager instanceof LinearLayoutManager) {
            int orientation = ((LinearLayoutManager) layoutManager).getOrientation();
            int dragFlags = 0;
            int swipedFlags = 0;
            if (orientation == LinearLayoutManager.HORIZONTAL) {
                dragFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                swipedFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            } else if (orientation == LinearLayoutManager.VERTICAL) {
                dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                swipedFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            }
            return makeMovementFlags(dragFlags, swipedFlags);
        }

        return 0;
    }

    /**
     * 当Item被拖拽的时候被回调
     *
     * @param recyclerView recyclerView
     * @param viewHolder   拖拽的ViewHolder
     * @param target       目的地的viewHolder
     * @return
     */
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return null != onItemTouchCallbackListener && onItemTouchCallbackListener.onMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
    }

    //滑动删除后调用
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        if (null != onItemTouchCallbackListener) {
            onItemTouchCallbackListener.onSwiped(viewHolder.getAdapterPosition());
        }
    }


    //当item被选中的时候调用
    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
    }

    //当item 取消选中的时候调用
    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
    }

    public interface OnItemTouchCallbackListener {
        void onSwiped(int adapterPosition);

        boolean onMove(int srcPosition, int targetPosition);
    }
}
