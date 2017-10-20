package com.example.dependencies.greendao;

import android.content.Context;
import android.graphics.Paint;
import android.util.Log;

import com.example.dependencies.gen.DaoMaster;
import com.example.dependencies.gen.UserInfoDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * create time : 2017/10/16
 * desc        : UserInfo数据库管理类
 */

public class UserInfoDaoUtils {
    private static final String TAG = "UserInfoDaoUtils";
    private DaoManager mDaoManager;

    public UserInfoDaoUtils(Context context) {
        mDaoManager = DaoManager.getInstance();
        mDaoManager.init(context);
    }

    public boolean insertUserInfo(UserInfo userInfo) {
        boolean isSuccess = false;
        isSuccess = mDaoManager.getDaoSession().getUserInfoDao().insert(userInfo) != -1;
        Log.i(TAG, "insertUserInfo: " + isSuccess + "-->" + userInfo.toString());
        return isSuccess;
    }
    /**
     * 插入多条数据，在子线程操作
     * @param userInfoList
     * @return
     */
    public boolean insertMultUserInfo(final List<UserInfo> userInfoList) {
        boolean flag = false;
        try {
            mDaoManager.getDaoSession().runInTx(new Runnable() {
                @Override
                public void run() {
                    for (UserInfo userInfo : userInfoList) {
                        mDaoManager.getDaoSession().insertOrReplace(userInfo);
                    }
                }
            });
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 修改一条数据
     * @param userInfo
     * @return
     */
    public boolean updateUserInfo(UserInfo userInfo){
        boolean flag = false;
        try {
            mDaoManager.getDaoSession().update(userInfo);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 删除单条记录
     * @param userInfo
     * @return
     */
    public boolean deleteUserInfo(UserInfo userInfo){
        boolean flag = false;
        try {
            //按照id删除
            mDaoManager.getDaoSession().delete(userInfo);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 删除所有记录
     * @return
     */
    public boolean deleteAll(){
        boolean flag = false;
        try {
            //按照id删除
            mDaoManager.getDaoSession().deleteAll(UserInfo.class);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 查询所有记录
     * @return
     */
    public List<UserInfo> queryAllUserInfo(){
        return mDaoManager.getDaoSession().loadAll(UserInfo.class);
    }

    /**
     * 查询所有记录
     * @return
     */
    public List<UserInfo> queryUserInfo(){
        DaoMaster daoMaster=new DaoMaster(mDaoManager.getDevOpenHelper().getWritableDb());
        return daoMaster.newSession().loadAll(UserInfo.class);
    }
    /**
     * 根据主键id查询记录
     * @param key
     * @return
     */
    public UserInfo queryUserInfoById(long key){
        return mDaoManager.getDaoSession().load(UserInfo.class, key);
    }

    /**
     * 使用native sql进行查询操作
     */
    public List<UserInfo> queryUserInfoByNativeSql(String sql, String[] conditions){
        return mDaoManager.getDaoSession().queryRaw(UserInfo.class, sql, conditions);
    }

    /**
     * 使用queryBuilder进行查询
     * @return
     */
    public List<UserInfo> queryUserInfoByQueryBuilder(long id){
        QueryBuilder<UserInfo> queryBuilder = mDaoManager.getDaoSession().queryBuilder(UserInfo.class);
        return queryBuilder.where(UserInfoDao.Properties._id.eq(id)).list();
    }
}
