package com.cihadgokce.searchitemlist.model

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Position(

    var id: Int? = null,
    var positions: ArrayList<PositionItem> = arrayListOf()

) : BaseResponseModel()


@JsonClass(generateAdapter = true)
data class PositionItem(
    var posX: Double? = null,
    var posY: Double? = null

) : BaseResponseModel()