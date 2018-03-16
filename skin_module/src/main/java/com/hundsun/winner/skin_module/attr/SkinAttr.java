package com.hundsun.winner.skin_module.attr;

import android.view.View;

/**
 * Created by william_zhang on 2017/9/18.
 */

public class SkinAttr {
    public String resName;
    public SkinAttrType attrType;

    public SkinAttr(String resName, SkinAttrType attrType) {
        this.resName = resName;
        this.attrType = attrType;
    }
    public void apply(View view){
        attrType.apply(view,resName);
    }
}
