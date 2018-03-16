package com.hundsun.winner.skin_module.attr;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;


import com.hundsun.winner.skin_module.constant.SkinConfig;
import com.hundsun.winner.skin_module.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by william_zhang on 2017/9/18.
 */

public class SkinAttrSupport {
    /**
     * 传入activity，找到content元素，递归遍历所有的子View，根据tag命名，记录需要换肤的View
     *
     * @param activity
     */
    public static List<SkinView> getSkinViews(Activity activity) {
        List<SkinView> skinViews = new ArrayList<>();
        ViewGroup context = (ViewGroup) activity.findViewById(android.R.id.content);
        addSkinView(context, skinViews);
        return skinViews;
    }

    /**
     * 遍历所有子view 判定当前view是否换肤
     *
     * @param view
     * @param skinViews
     */
    public static void addSkinView(View view, List<SkinView> skinViews) {
        SkinView skinView = getSkinView(view);
        if (skinView != null) skinViews.add(skinView);
        if (view instanceof ViewGroup) {
            ViewGroup container = (ViewGroup) view;
            for (int i = 0; i < container.getChildCount(); i++) {
                View child = container.getChildAt(i);
                addSkinView(child, skinViews);
            }
        }
    }

    /**
     * 根据tag判定传入view是否 为焕肤view
     *
     * @param view
     * @return
     */
    private static SkinView getSkinView(View view) {
        Object tag = view.getTag(R.id.skin_tag_id);
        if (tag == null) {
            tag = view.getTag();
        }
        if (tag == null) return null;
        if (!(tag instanceof String)) return null;
        String tagStr = (String) tag;
        List<SkinAttr> skinAttrs = parseTag(tagStr);
        if (!skinAttrs.isEmpty()) {
            Object tag01 = view.getTag(R.id.skin_tag_id);
            if (tag01 == null) {
                tag01 = view.getTag();
                view.setTag(R.id.skin_tag_id, tag01);
                view.setTag(null);
            }
            return new SkinView(view, skinAttrs);
        }
        return null;
    }

    //skin:left_menu_icon:src|skin:color_red:textColor
    private static List<SkinAttr> parseTag(String tagStr) {
        List<SkinAttr> skinAttrs = new ArrayList<SkinAttr>();
        if (TextUtils.isEmpty(tagStr)) return skinAttrs;

        String[] items = tagStr.split("[|]");
        for (String item : items) {
            if (!item.startsWith(SkinConfig.SKIN_PREFIX))
                continue;
            String[] resItems = item.split(":");
            if (resItems.length != 3)
                continue;

            String resName = resItems[1];
            String resType = resItems[2];

            SkinAttrType attrType = getSupportAttrType(resType);
            if (attrType == null) continue;
            SkinAttr attr = new SkinAttr(resName, attrType);
            skinAttrs.add(attr);
        }
        return skinAttrs;
    }

    private static SkinAttrType getSupportAttrType(String resType) {
        for (SkinAttrType attrType : SkinAttrType.values()) {
            if (resType.equals(attrType.getAttrType())) {
                return attrType;
            }
        }
        return null;
    }
}
