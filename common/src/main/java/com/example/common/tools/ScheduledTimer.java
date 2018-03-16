package com.example.common.tools;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;

/**
 * Created on 2018/3/8
 * Title: 定时发送请求
 * Description:
 * author 张康
 * update 2018/3/8
 */
public abstract class ScheduledTimer {
    /**
     * 第一次执行的间隔
     */
    private final long mInitialDelay;

    /**
     * 之后每次执行的间隔
     */
    private final long mScheduledInterval;

    private static final int MSG = 1;

    /**
     * 表示定时器是否被取消的布尔值
     */
    private boolean mCancelled = false;

    /**
     * @param scheduledInterval 倒计时的间隔
     */
    public ScheduledTimer(long scheduledInterval) {
        this(0, scheduledInterval);
    }

    /**
     * @param initialDelay      第一次执行的间隔
     * @param scheduledInterval 倒计时的间隔
     */
    public ScheduledTimer(long initialDelay, long scheduledInterval) {
        mInitialDelay = initialDelay;
        mScheduledInterval = scheduledInterval;
    }

    /**
     * 开始执行
     */
    public synchronized final ScheduledTimer start() {
        mCancelled = false;
        mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG), mInitialDelay < 0 ? 0 : mInitialDelay);
        return this;
    }

    /**
     * 取消
     */
    public synchronized final void cancel() {
        mCancelled = true;
        mHandler.removeMessages(MSG);
        onFinish();
    }

    /**
     * 是否已经取消
     */
    public synchronized final boolean isCancelled() {
        return mCancelled;
    }

    /**
     * 每隔固定时间执行的方法
     */
    public abstract void onTick();

    /**
     * 结束的时候执行方法
     */
    public void onFinish() {

    }

    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            synchronized (ScheduledTimer.this) {
                if (mCancelled) {
                    return;
                }
                //记录一下执行方法之前的时间
                long lastTickStart = SystemClock.elapsedRealtime();
                onTick();
                // 考虑到onTick需要时间来执行
                long delay = lastTickStart + mScheduledInterval - SystemClock.elapsedRealtime();

                // 特殊情况：用户的onTick花费的时间间隔超过
                // 完成后，跳至下一个区间
                while (delay < 0) {
                    delay += mScheduledInterval;
                }
                sendMessageDelayed(obtainMessage(MSG), delay);
            }
        }
    };
}
