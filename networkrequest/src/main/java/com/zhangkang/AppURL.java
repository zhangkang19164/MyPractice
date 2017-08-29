package com.zhangkang;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * create time : 2017/07/26
 * desc        :
 */

public interface AppURL {

    @GET("in_theaters")
    Call<ResponseBody> getInTheaters(@Query("apikey")String apikey,@Query("city")String city,@Query("start")int start,
                                     @Query("count")int count,@Query("client")String client,@Query("udid")String udid);

}
