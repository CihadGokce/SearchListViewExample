package com.cihadgokce.searchitemlist.model

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class SatelliteListItem(

    var id: Int? = null,
    var active: Boolean? = null,
    var name: String? = null

) : BaseResponseModel()

@JsonClass(generateAdapter = true)
data class SatelliteList(

    var dataList: ArrayList<SatelliteListItem> = arrayListOf()

) : BaseResponseModel()