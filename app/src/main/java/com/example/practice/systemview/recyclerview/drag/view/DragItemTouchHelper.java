package com.example.practice.systemview.recyclerview.drag.view;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.Collections;
import java.util.List;

/**
 * create time : 2017/09/08
 * desc        :
 */

public class DragItemTouchHelper extends ItemTouchHelper {
    private static DragItemTouchHelper instance = null;
    private DragOnItemTouchCallback dragOnItemTouchCallback;

    public DragItemTouchHelper(Callback callback) {
        super(callback);
        if (callback instanceof DragCallback) {
            dragOnItemTouchCallback = (DragOnItemTouchCallback) ((DragCallback) callback).getOnItemTouchCallbackListener();
        }
    }

    @Override
    public void attachToRecyclerView(@Nullable RecyclerView recyclerView) {
        super.attachToRecyclerView(recyclerView);
    }

    @Override
    public void startSwipe(RecyclerView.ViewHolder viewHolder) {
        super.startSwipe(viewHolder);
    }

    private void refreshData(List<?> list) {
        dragOnItemTouchCallback.setList(list);
    }

    public static void staticAttachToRecyclerView(List<?> list, @Nullable RecyclerView recyclerView) {
        staticAttachToRecyclerView(list, recyclerView, true, false);
    }

    public static void staticAttachToRecyclerView(List<?> list, @Nullable RecyclerView recyclerView, boolean isCanDrag, boolean isCanSwiped) {
        synchronized (DragItemTouchHelper.class) {
            if (instance == null) {
                instance = new DragItemTouchHelper(new DragCallback(new DragOnItemTouchCallback(recyclerView, list), isCanDrag, isCanSwiped));
            }
        }
        instance.attachToRecyclerView(recyclerView);
    }

    //改变数据源后调用
    public static void staticRefreshData(List<?> list) {
        if (null == instance) {
            return;
        }
        instance.refreshData(list);
    }

    public static class DragCallback extends DragItemTouchHelperCallback {

       public DragCallback(OnItemTouchCallbackListener onItemTouchCallbackListener, boolean isCanDrag, boolean isCanSwiped) {
            super(onItemTouchCallbackListener);
            setCanDrag(isCanDrag);
            setCanSwiped(isCanSwiped);
        }

    }

    public static class DragOnItemTouchCallback implements DragItemTouchHelperCallback.OnItemTouchCallbackListener {
        private List<?> list;
        private RecyclerView recyclerView;
        private RecyclerView.Adapter adapter;

        public DragOnItemTouchCallback(RecyclerView recyclerView, List<?> list) {
            this.recyclerView = recyclerView;
            adapter = recyclerView.getAdapter();
            this.list = list;
        }

        @Override
        public void onSwiped(int adapterPosition) {
            if (null == adapter) {
                adapter = recyclerView.getAdapter();
            }
            if (null == adapter) {
                return;
            }
            list.remove(adapterPosition);
            //添加移除动画
            adapter.notifyItemRemoved(adapterPosition);
            //刷新列表，防止position错位
            adapter.notifyDataSetChanged();
        }

        @Override
        public boolean onMove(int srcPosition, int targetPosition) {
            if (null == adapter) {
                adapter = recyclerView.getAdapter();
            }
            if (null == adapter) {
                return false;
            }
            Collections.swap(list, srcPosition, targetPosition);
            //添加改变动画
            adapter.notifyItemMoved(srcPosition, targetPosition);
            //刷新列表，防止position错位
            adapter.notifyItemChanged(srcPosition);
            adapter.notifyItemChanged(targetPosition);
            return true;
        }

        public void setList(List<?> list) {
            this.list = list;
            adapter = null;
        }
    }

}
