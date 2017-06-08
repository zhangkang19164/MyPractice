package com.example.practice.systemview.recyclerview.edit;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.practice.R;

import java.util.List;


/**
 * create time : 2017/05/15
 * desc        :
 */

public class RecyclerEditAdapter extends RecyclerView.Adapter<RecyclerEditAdapter.RecyclerEditViewHolder> {
    private static final String TAG = "RecyclerEditAdapter";
    private List<RecyclerEditBean> recyclerEditBeanList;
    private int focusPosition = -1;


    @Override
    public RecyclerEditViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_edit, parent, false);
        return new RecyclerEditViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerEditViewHolder holder, int position) {
        final int pos = position;
        final RecyclerEditBean recyclerEditBean = recyclerEditBeanList.get(position);


        holder.button.setText(recyclerEditBean.getName());
        String editTextStr = recyclerEditBean.getEditTextStr();
        if (!TextUtils.isEmpty(editTextStr)) {
            holder.editText.setText(editTextStr);
        } else {
            holder.editText.setText("0");
        }
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                recyclerEditBean.setEditTextStr(s.toString());
            }
        };
        holder.editText.addTextChangedListener(textWatcher);
        holder.editText.setTag(textWatcher);

        if (focusPosition == pos) {
            holder.editText.requestFocus();
            holder.editText.setSelection(holder.editText.getText().length());
        }
        holder.editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    focusPosition = pos;
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return null == recyclerEditBeanList ? 0 : recyclerEditBeanList.size();
    }

    @Override
    public void onViewRecycled(RecyclerEditViewHolder holder) {
        //防止监听错乱 在该方法中移除 已经设置的输入监听
        if (holder.editText.getTag() instanceof TextWatcher) {
            holder.editText.removeTextChangedListener((TextWatcher) holder.editText.getTag());
        }
    }

    public void setRecyclerEditBeanList(List<RecyclerEditBean> recyclerEditBeanList) {
        this.recyclerEditBeanList = recyclerEditBeanList;
        notifyDataSetChanged();
    }

    public void editText(EditText editText, String inputString, TextWatcher textWatcher) {
        if (editText instanceof TextWatcher) {
            editText.removeTextChangedListener((TextWatcher) editText.getTag());
        }
        if (!TextUtils.isEmpty(inputString)) {
            editText.setText(inputString);
        } else {
            editText.setText("");
        }
        editText.addTextChangedListener(textWatcher);
        editText.setTag(textWatcher);
    }

    class RecyclerEditViewHolder extends RecyclerView.ViewHolder {
        EditText editText;
        Button button;

        public RecyclerEditViewHolder(View itemView) {
            super(itemView);
            editText = (EditText) itemView.findViewById(R.id.edit_text);
            button = (Button) itemView.findViewById(R.id.button);
        }
    }
}
