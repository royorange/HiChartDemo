package com.example.roy.hichartdemo;

import com.example.roy.hichartdemo.model.DataGraphCategoryDetailModel;
import com.example.roy.hichartdemo.model.NetResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Roy on 2019/1/29
 */
public interface Repository {

    @GET("/v1/graphtheme/{id}")
    Single<NetResponse<DataGraphCategoryDetailModel>> getDataGraphCategory(@Path("id")String id);
}
