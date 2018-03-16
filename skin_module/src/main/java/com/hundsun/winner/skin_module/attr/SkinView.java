package com.hundsun.winner.skin_module.attr;

import android.view.View;

import java.util.List;

/**
 * Created by william_zhang on 2017/9/18.
 */

public class SkinView {
    //待换肤view
    public View view;
    //待换肤view中需要换的类型
    public List<SkinAttr> attrs;
    public SkinView(View view,List<SkinAttr> skinAttrs){
        this.view=view;
        this.attrs=skinAttrs;
    }
    public void apply(){
        if(view==null) return;
        for(SkinAttr attr:attrs){
            attr.apply(view);
        }
    }
}
