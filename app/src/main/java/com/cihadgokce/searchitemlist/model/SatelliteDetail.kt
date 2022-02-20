package com.cihadgokce.searchitemlist.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SatelliteDetail(

    var id: Int? = null,
    var cost_per_launch: Int? = null,
    var first_flight: String? = null,
    var height: Int? = null,
    var mass: Int? = null

) : BaseResponseModel()

@JsonClass(generateAdapter = true)
data class SatelliteFullDetail(
    var detail: SatelliteDetail = SatelliteDetail(),
    var list : PositionsList = PositionsList(),

) : BaseResponseModel()

