package com.cihadgokce.searchitemlist.data.api

import com.cihadgokce.searchitemlist.core.service.ResponseState
import com.cihadgokce.searchitemlist.model.BaseResponseModel
import kotlinx.coroutines.flow.Flow

interface SatelliteRepository {

    suspend fun getSatelliteList() : Flow<ResponseState<BaseResponseModel>>
    suspend fun getSatelliteDetail(id:Int) : Flow<ResponseState<BaseResponseModel>>

}