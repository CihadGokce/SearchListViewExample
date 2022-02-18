package com.cihadgokce.searchitemlist.model

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SatelliteDetail(

    var id: Int? = null,
    var costPerLaunch: Int? = null,
    var firstFlight: String? = null,
    var height: Int? = null,
    var mass: Int? = null

) : BaseResponseModel()

@JsonClass(generateAdapter = true)
data class SatelliteFullDetail(
    var detail: SatelliteDetail = SatelliteDetail(),
    var position: Position = Position()
) : BaseResponseModel()