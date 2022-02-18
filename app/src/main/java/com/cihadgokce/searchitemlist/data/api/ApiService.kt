package com.cihadgokce.searchitemlist.data.api

import com.cihadgokce.searchitemlist.model.BaseResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/list/")
    suspend fun getSatelliteList() : BaseResponseModel

    @GET("/detail/")
    suspend fun getSatelliteDetail(@Query ("id")  id:Int ) : BaseResponseModel


}