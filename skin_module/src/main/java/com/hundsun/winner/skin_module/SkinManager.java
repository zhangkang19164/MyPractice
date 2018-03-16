package com.hundsun.winner.skin_module;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.View;


import com.hundsun.winner.skin_module.attr.SkinAttrSupport;
import com.hundsun.winner.skin_module.attr.SkinView;
import com.hundsun.winner.skin_module.callback.ISkinEndListener;
import com.hundsun.winner.skin_module.utils.PrefUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by william_zhang on 2017/9/18.
 */

public class SkinManager {

    private Context mContext;
    private Resources mResources;
    private ResourceManager mResourceManager;
    private PrefUtils mPrefUtils;
    private String mSuffix = "";
    //保存现在存活的activity
    private List<Activity> mActivities = new ArrayList<>();

    public ResourceManager getResourceManager(){
        mResourceManager =new ResourceManager(mContext.getResources(),mContext.getPackageName(),mSuffix);
        return mResourceManager;
    }


    private SkinManager() {
    }

    private static class SingletonHolder {
        static SkinManager sInstance = new SkinManager();
    }

    public static SkinManager getInstance() {//获得单利
        return SingletonHolder.sInstance;
    }

    //初始化   如果要实现插件化换肤 修改本类 mResourceManager
    public void init(Context context) {
        mContext = context.getApplicationContext();
        mPrefUtils = new PrefUtils(mContext);
        mSuffix = mPrefUtils.getSuffix();
    }

    /**
     * 内部换肤
     *
     * @param suffix
     */
    public void changeSkin(String suffix) {
        mSuffix = suffix;
        mPrefUtils.putPluginSuffix(suffix);
        notifyChanged();
    }

    public void changeSkin(String suffix, ISkinEndListener listener) {
        mSuffix = suffix;
        mPrefUtils.putPluginSuffix(suffix);
        notifyChanged();
        listener.end();
    }

    private void notifyChanged() {
        for (Activity a : mActivities) {
            apply(a);
        }
    }

    private void apply(Activity a) {
        List<SkinView> skinViews = SkinAttrSupport.getSkinViews(a);
        if (skinViews == null) return;
        for (SkinView skinView : skinViews) {
            skinView.apply();
        }
    }

    /**
     * 注册activity并换肤
     *
     * @param activity
     */
    public void register(final Activity activity) {
        mActivities.add(activity);
        activity.findViewById(android.R.id.content).post(new Runnable() {
            @Override
            public void run() {
                apply(activity);
            }
        });
    }

    /**
     * 注销activity 以后不换肤
     *
     * @param activity
     */
    public void unregister(Activity activity) {
        mActivities.remove(activity);
    }

    /**
     * 动态换肤
     *
     * @param view
     */
    public void injectSkin(View view) {
        List<SkinView> skinViews = new ArrayList<>();
        SkinAttrSupport.addSkinView(view, skinViews);
        for (SkinView skinView : skinViews) {
            skinView.apply();
        }
    }

    /**
     * 判断是否换肤
     * @param skin
     * @return
     */
    public boolean isChange(String skin){
        return !mPrefUtils.getSuffix().equals(skin);
    }

    public boolean isEqual(String skin){
        return mPrefUtils.getSuffix().equals(skin);
    }

    public String getmSuffix(){return mPrefUtils.getSuffix();}

}
