package com.cihadgokce.searchitemlist.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Positions(
    var posX: Double? = null,
    var posY: Double? = null

) : BaseResponseModel()

@JsonClass(generateAdapter = true)
data class PositionsList(

    var id: String? = null,
    var positions: List<Positions> = listOf()

) : BaseResponseModel()
