package com.cihadgokce.searchitemlist.data.api

import javax.inject.Inject

class DataSource @Inject constructor(
    private val api: ApiService
) {
    suspend fun getSatelliteList() = api.getSatelliteList()

    suspend fun getSatelliteDetail(id: Int) = api.getSatelliteDetail(id)

}