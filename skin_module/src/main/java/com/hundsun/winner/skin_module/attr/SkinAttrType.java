package com.hundsun.winner.skin_module.attr;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.hundsun.winner.skin_module.ResourceManager;
import com.hundsun.winner.skin_module.SkinManager;
import com.hundsun.winner.skin_module.utils.L;


/**
 * Created by william_zhang on 2017/9/18.
 */

public enum SkinAttrType {
    BACKGROUND("background") {
        @Override
        public void apply(View view, String resName) {
            Integer color = getResourceManager().getColor(resName);
            if (color != null) {
                view.setBackgroundColor(color);
            } else {
                Drawable drawable = getResourceManager().getDrawableByName(resName);
                if (drawable != null)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        view.setBackground(drawable);
                    }else{
                        view.setBackgroundDrawable(drawable);
                    }
            }
        }
    }, COLOR("textColor") {
        @Override
        public void apply(View view, String resName) {
            ColorStateList colorList = getResourceManager().getColorStateList(resName);
            if (colorList == null) {
                colorList = getResourceManager().getDrawableStateList(resName);
                if (colorList == null) {
                    return;
                }
            }
            ((TextView) view).setTextColor(colorList);
        }
    }, SRC("src") {
        @Override
        public void apply(View view, String resName) {
            if (view instanceof ImageView) {
                Drawable drawable = getResourceManager().getDrawableByName(resName);
                if (drawable == null) return;
                ((ImageView) view).setImageDrawable(drawable);
            }
            if (view instanceof ImageButton) {
                Drawable drawable = getResourceManager().getDrawableByName(resName);
                if (drawable == null) return;
                ((ImageButton) view).setImageDrawable(drawable);
            }
        }
    }, DIVIDER("divider") {
        @Override
        public void apply(View view, String resName) {
            if (view instanceof ListView) {
                Drawable divider = getResourceManager().getDrawableByName(resName);
                if (divider == null) {
                    try {
                        Integer color = getResourceManager().getColor(resName);
                        ((ListView) view).setDivider(new ColorDrawable(color));
                        ((ListView) view).setDividerHeight(2);
                    } catch (Resources.NotFoundException ex) {

                    }
                } else {
                    ((ListView) view).setDivider(divider);
                    ((ListView) view).setDividerHeight(2);
                }
            }
        }
    }, ALPHA("alhpa") {
        @Override
        public void apply(View view, String resName) {
            try {
                String string = getResourceManager().getString(resName);
                view.setAlpha(Float.valueOf(string));
            } catch (Exception e) {
                L.e("透明度度数失败");
            }
        }
    }, CHILDDIVIDER("childdivider") {
        @Override
        public void apply(View view, String resName) {
            if (view instanceof ExpandableListView) {
                Drawable divider = getResourceManager().getDrawableByName(resName);
                if (divider == null) {
                    try {
                        Integer color = getResourceManager().getColor(resName);
                        ((ExpandableListView) view).setChildDivider(new ColorDrawable(color));
                        ((ExpandableListView) view).setDividerHeight(2);
                    } catch (Resources.NotFoundException ex) {

                    }
                } else {
                    ((ExpandableListView) view).setDivider(divider);
                    ((ExpandableListView) view).setDividerHeight(2);
                }
            }
        }
    };
    String attrType;

    SkinAttrType(String attrType) {
        this.attrType = attrType;
    }

    public String getAttrType() {
        return attrType;
    }

    /**
     * @param view    待修改view
     * @param resName 待修改view 的标记 例如tc_444444_999999
     */
    public abstract void apply(View view, String resName);

    public ResourceManager getResourceManager() {
        return SkinManager.getInstance().getResourceManager();
    }
}
