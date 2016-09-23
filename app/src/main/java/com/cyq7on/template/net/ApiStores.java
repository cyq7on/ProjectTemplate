package com.cyq7on.template.net;

import com.cyq7on.template.bean.BaseBean;
import com.cyq7on.template.bean.HomeInfo;
import com.cyq7on.template.bean.LoginInfo;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * @Description: Retrofit配置接口
 * @author: cyq7on
 * @date: 2016/8/2 0002 17:54
 * @version: V1.0
 */
public interface ApiStores {

    @GET("index")
    Call<HomeInfo> getHomeInfo(@QueryMap Map<String,String> params);
    @GET("users/{userShow}")
    Call<Object> getUserInfo(@Path("userShow") String userShow
            , @QueryMap Map<String,String> map);

    @GET()
    Call<Object> getUnreadInfo(@QueryMap Map<String,String> map);

    @GET("statuses/{type}")
    Call<Object> getWeiboInfo(@Path("type") String type
            , @QueryMap Map<String,String> map);

    @POST("call.json")
    @FormUrlEncoded
    Call<LoginInfo> login(@FieldMap Map<String,String> map);

    @POST("call.json")
    @FormUrlEncoded
    Call<BaseBean> request(@FieldMap Map<String,String> map);

    @POST("comments/create.json")
    @FormUrlEncoded
    Call<Object> comments(@FieldMap Map<String,String> map);

    @POST("statuses/update.json")
    @FormUrlEncoded
    Call<Object> publish(@FieldMap Map<String,String> map);

    @POST()
    @Multipart
    Call<Object> publishImage(@PartMap Map<String, RequestBody> map
            ,@Part MultipartBody.Part file);
}
