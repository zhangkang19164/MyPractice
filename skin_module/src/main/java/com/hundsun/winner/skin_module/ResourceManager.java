package com.hundsun.winner.skin_module;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;

import com.hundsun.winner.skin_module.utils.L;


/**
 * Created by william_zhang on 2017/9/18.
 */

public class ResourceManager {
    private static final String DRAWABLE = "drawable";
    private static final String COLOR = "color";
    private static final String STRING = "string";
    private Resources mResources;
    private String mPluginPackageName;
    private String mSuffix;

    /**
     * 资源管理类  通过String 获得 Drawable color ColorStateList等对应对象或者数值
     *
     * @param resources
     * @param pluginPackageName
     * @param suffix
     */
    public ResourceManager(Resources resources, String pluginPackageName, String suffix) {
        mResources = resources;
        mPluginPackageName = pluginPackageName;
        if (suffix == null) {
            suffix = "";
        }
        mSuffix = suffix;
    }

    /**
     * 样式资源
     *
     * @param name
     * @return
     */
    public Drawable getDrawableByName(String name) {
        try {
            name = appendSuffix(name);
            L.e("name = " + name + " , " + mPluginPackageName);
            return mResources.getDrawable(mResources.getIdentifier(name, DRAWABLE, mPluginPackageName));
        } catch (Resources.NotFoundException e) {
            return null;
        }
    }

    /**
     * 获得颜色资源中对应的数值
     *
     * @param name
     * @return
     * @throws Resources.NotFoundException
     */
    public Integer getColor(String name) throws Resources.NotFoundException {
        try {
            name = appendSuffix(name);
            L.e("name = " + name);
            return mResources.getColor(mResources.getIdentifier(name, COLOR, mPluginPackageName));
        }catch (Exception e){
            return null;
        }
    }

    public String getString(String name) throws Resources.NotFoundException {
        try {
            name = appendSuffix(name);
            L.e("name = " + name);
            return mResources.getString(mResources.getIdentifier(name, STRING, mPluginPackageName));
        } catch (Exception e) {
            return "";
        }
    }

    public ColorStateList getColorStateList(String name) {
        try {
            name = appendSuffix(name);
            L.e("name = " + name);
            return mResources.getColorStateList(mResources.getIdentifier(name, COLOR, mPluginPackageName));
        } catch (Resources.NotFoundException e) {
            return null;
        }
    }

    public ColorStateList getDrawableStateList(String name) {
        try {
            name = appendSuffix(name);
            L.e("name = " + name);
            return mResources.getColorStateList(mResources.getIdentifier(name, DRAWABLE, mPluginPackageName));
        } catch (Resources.NotFoundException e) {
            return null;
        }
    }

    private String appendSuffix(String name) {
        if (!TextUtils.isEmpty(mSuffix))
            return name += "_" + mSuffix;
        return name;
    }

}
