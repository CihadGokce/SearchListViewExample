package com.cihadgokce.searchitemlist.model

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SatelliteDetail  (

    @SerializedName("id"              ) var id            : Int?    = null,
    @SerializedName("cost_per_launch" ) var costPerLaunch : Int?    = null,
    @SerializedName("first_flight"    ) var firstFlight   : String? = null,
    @SerializedName("height"          ) var height        : Int?    = null,
    @SerializedName("mass"            ) var mass          : Int?    = null

): BaseResponseModel()

@JsonClass(generateAdapter = true)
data class  SatelliteFullDetail(
    var detail: SatelliteDetail = SatelliteDetail(),
    var position: Position = Position()
) :BaseResponseModel()