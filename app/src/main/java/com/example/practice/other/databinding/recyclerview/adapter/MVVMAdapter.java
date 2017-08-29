package com.example.practice.other.databinding.recyclerview.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.TextAppearanceSpan;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.practice.BR;
import com.example.practice.R;

import java.util.List;
import java.util.Random;

/**
 * 交易通用Adapter
 */

public class MVVMAdapter extends RecyclerView.Adapter<MVVMViewHolder> {
    private List<String> baseBeanList;
    private Random random = new Random(System.currentTimeMillis());

    public MVVMAdapter(List<String> baseBeanList) {
        this.baseBeanList = baseBeanList;
    }

    @Override
    public MVVMViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding inflate = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.mvvm_list, parent, false);
        return new MVVMViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(final MVVMViewHolder holder, int position) {
        String string = baseBeanList.get(position);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(string + "\n");
        holder.setVariable(BR.text, spannableStringBuilder);
    }

    @Override
    public int getItemCount() {
        return null == baseBeanList ? 0 : baseBeanList.size();
    }


    //改变字体样式
    private SpannableString chanTxTStyle(Context context, String txt, int style) {
        SpannableString spannableString = new SpannableString(txt);
        spannableString.setSpan(new TextAppearanceSpan(context, style), 0, txt.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }


    public void add(String string) {
        int pos = random.nextInt(baseBeanList.size() + 1);
        baseBeanList.add(pos, string);
        notifyItemInserted(pos);
    }

    public void delete() {
        int pos = random.nextInt(baseBeanList.size());
        baseBeanList.remove(pos);
        notifyItemRemoved(pos);
    }
}
