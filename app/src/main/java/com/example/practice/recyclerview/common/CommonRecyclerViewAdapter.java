package com.example.practice.recyclerview.common;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * 自定义 RecyclerView Adapter
 */

public abstract class CommonRecyclerViewAdapter<T> extends RecyclerView.Adapter<CommonViewHoler> {
	protected List<T> list;
	private int layoutId;
	private OnItemClickListener<T> onItemClickListener;

	public CommonRecyclerViewAdapter() {

	}

	public CommonRecyclerViewAdapter(int layoutId) {
		this.layoutId = layoutId;
	}

	public CommonRecyclerViewAdapter(List<T> list, int layoutId) {
		this.list = list;
		this.layoutId = layoutId;
	}

	public void setList(List<T> list) {
		if (null != list) {
			this.list = list;
			notifyDataSetChanged();
		}
	}

	public void addList(List<T> list) {
		if (null != list) {
			this.list.addAll(list);
			notifyDataSetChanged();
		}
	}

	public void addOne(T t) {
		if (null != list) {
			list.add(t);
			notifyDataSetChanged();
		}
	}

	public void cleanList() {
		if (null != list) {
			list.clear();
		}
	}

	public void removeOne(int index) {
		if (null != list) {
			list.remove(index);
			notifyDataSetChanged();
		}
	}

	public void removeOne(T t) {
		if (null != list) {
			list.remove(t);
			notifyDataSetChanged();
		}
	}

	public void removeList(List<T> list) {
		if (null != this.list) {
			if (null != list) {
				this.list.removeAll(list);
				notifyDataSetChanged();
			}

		}
	}

	@Override
	public CommonViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
		return new CommonViewHoler(view);
	}

	@Override
	public int getItemCount() {
		return null == list ? 0 : list.size();
	}

	@Override
	public void onBindViewHolder(CommonViewHoler holder, final int position) {
		holder.itemView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (null != onItemClickListener) {
					onItemClickListener.onItemClickListener(v, list.get(position));
				}
			}
		});
		holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				if (null != onItemClickListener) {
					onItemClickListener.onItemLongClickListener(v, list.get(position));
				}
				return false;
			}
		});
		convert(holder, list.get(position));
	}

	public abstract void convert(CommonViewHoler holder, T t);

	public void setOnItemClickListener(OnItemClickListener<T> onItemClickListener) {
		this.onItemClickListener = onItemClickListener;
	}
}
