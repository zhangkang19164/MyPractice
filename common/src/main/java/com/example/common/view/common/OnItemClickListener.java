package com.example.common.view.common;

import android.view.View;

/**
 * 自定义 点击事件
 */

public interface OnItemClickListener<T> {
	void onItemClickListener(View view, T t);

	void onItemLongClickListener(View view, T t);
}
