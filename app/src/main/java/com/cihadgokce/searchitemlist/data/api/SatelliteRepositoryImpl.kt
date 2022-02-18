package com.cihadgokce.searchitemlist.data.api

import android.content.Context
import com.cihadgokce.searchitemlist.core.di.IoDispatcher
import com.cihadgokce.searchitemlist.core.service.ResponseState
import com.cihadgokce.searchitemlist.core.service.fetch
import com.cihadgokce.searchitemlist.model.BaseResponseModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class SatelliteRepositoryImpl @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @ApplicationContext private val context: Context,
    private val dataSource: DataSource
) : SatelliteRepository {
    override suspend fun getSatelliteList(): Flow<ResponseState<BaseResponseModel>> {
        return fetch(context,dispatcher = ioDispatcher,0){
            dataSource.getSatelliteList()
        }
    }

    override suspend fun getSatelliteDetail(id:Int): Flow<ResponseState<BaseResponseModel>> {
        return fetch(context,dispatcher = ioDispatcher,id){
            dataSource.getSatelliteDetail(id)
        }
    }

}
