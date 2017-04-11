package com.example.practice.systemview.expandablelistview.type.adapter;

import android.content.Context;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.TextAppearanceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.practice.R;
import com.example.practice.systemview.expandablelistview.type.bean.ChildBean;
import com.example.practice.systemview.expandablelistview.type.bean.GroupBean;

import java.util.List;

/**
 * 多type的ExpandableListView
 */

public class ExpandableListViewTypeAdapter extends BaseExpandableListAdapter {
    public static final int GROUP_TYPE_ONE = 2;
    public static final int GROUP_TYPE_TWO = 3;

    public static final int CHILDREN_TYPE_TWO = 2;
    public static final int CHILDREN_TYPE_THREE = 3;

    private List<GroupBean> groupBeanList;
    private List<List<ChildBean>> childBeanList;
    private Context mContext;

    public ExpandableListViewTypeAdapter(Context context, List<GroupBean> groupBeanList, List<List<ChildBean>> childBeanList) {
        this.mContext = context;
        this.groupBeanList = groupBeanList;
        this.childBeanList = childBeanList;
    }

    @Override
    public int getGroupCount() {
        return null == groupBeanList ? 0 : groupBeanList.size();
    }

    /**
     * 取得指定分组的子元素数.
     *
     * @param groupPosition 要取得子元素个数的分组位置.
     * @return 指定分组的子元素个数.
     */
    @Override
    public int getChildrenCount(int groupPosition) {
        if(getGroupType(groupPosition)==GROUP_TYPE_ONE){
            return 1;
        }
        return null == childBeanList ? 0 : null == childBeanList.get(groupPosition) ? 0 : childBeanList.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupBeanList.get(groupPosition);
    }

    /**
     * 取得与指定分组、指定子项目关联的数据.
     *
     * @param groupPosition 包含子视图的分组的位置.
     * @param childPosition 指定的分组中的子视图的位置.（子视图中的子视图的位置，上面的那个子视图衍化成了一个组）
     * @return 与子视图关联的数据.
     */
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childBeanList.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    /**
     * 取得给定分组中给定子视图的ID. 该组ID必须在组中是唯一的.必须不同于其他所有ID（分组及子项目的ID）.
     *
     * @param groupPosition 包含子视图的分组的位置
     * @param childPosition 要取得ID的指定的分组中的子视图的位置.
     * @return 与子视图关联的ID.
     */
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder groupHolder = null;

        if (getGroupType(groupPosition) == GROUP_TYPE_ONE) {
            if (null == convertView) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_group_one, parent, false);
                groupHolder = new GroupHolder();
                groupHolder.textView = (TextView) convertView.findViewById(R.id.item_group_text);
                convertView.setTag(groupHolder);
            } else {
                groupHolder = (GroupHolder) convertView.getTag();
            }
        }
        if (getGroupType(groupPosition) == GROUP_TYPE_TWO) {
            if (null == convertView) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_group_two, parent, false);
                groupHolder = new GroupHolder();
                groupHolder.textView = (TextView) convertView.findViewById(R.id.item_group_text);
                convertView.setTag(groupHolder);
            } else {
                groupHolder = (GroupHolder) convertView.getTag();
            }
        }
        if (null != groupHolder) {
            groupHolder.textView.setText(groupBeanList.get(groupPosition).getTitleName());
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder childHolder;
        int childType = getChildType(groupPosition, childPosition);

        if (childType == CHILDREN_TYPE_TWO) {
            if (null == convertView) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.region_monitor_main_item, parent, false);
//                childHolder = new ChildHolder();
//                childHolder.textView = (TextView) convertView.findViewById(R.id.item_child_text);
//                convertView.setTag(childHolder);
            } else {
//                childHolder = (ChildHolder) convertView.getTag();
            }
//            ChildBean childBean= (ChildBean) getChild(groupPosition,childPosition);
//            childHolder.textView.setText(childBean.getName());
        }
        if (childType == CHILDREN_TYPE_THREE) {
            ChildThreeHolder childThreeHolder;
            if (null == convertView) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_child_three, parent, false);
                childThreeHolder = new ChildThreeHolder();
                childThreeHolder.textView_01 = (TextView) convertView.findViewById(R.id.item_child_text_01);
                childThreeHolder.textView_02 = (TextView) convertView.findViewById(R.id.item_child_text_02);
                childThreeHolder.textView_03 = (TextView) convertView.findViewById(R.id.item_child_text_03);
                convertView.setTag(childThreeHolder);
            } else {
                childThreeHolder = (ChildThreeHolder) convertView.getTag();
            }
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder("先锋新材" + "\n");
            spannableStringBuilder.append(chanTxTStyle(mContext, "300163", R.style.quote_sub_text));
            childThreeHolder.textView_01.setText(spannableStringBuilder);
            childThreeHolder.textView_02.setText("13.15");
            childThreeHolder.textView_03.setText("+10.04%");
        }
        return convertView;
    }


    /**
     * 指定位置的子视图是否可选择.
     *
     * @param groupPosition 包含要取得子视图的分组位置
     * @param childPosition 分组中子视图的位置.
     * @return 子视图是否可选择.
     */
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public int getGroupType(int groupPosition) {
        return groupBeanList.get(groupPosition).getGroupType();
    }

    @Override
    public int getChildType(int groupPosition, int childPosition) {
        return childBeanList.get(groupPosition).get(childPosition).getChildType();
    }

    @Override
    public int getGroupTypeCount() {
        return 2;
    }

    @Override
    public int getChildTypeCount() {
        return 4;
    }

    //改变字体样式
    private SpannableString chanTxTStyle(Context context, String txt, int style) {
        SpannableString spannableString = new SpannableString(txt);
        spannableString.setSpan(new TextAppearanceSpan(context, style), 0, txt.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    private class GroupHolder {
        TextView textView;
    }

    private class ChildHolder {
        TextView textView;
    }

    private class ChildThreeHolder {
        TextView textView_01;
        TextView textView_02;
        TextView textView_03;
    }

}
