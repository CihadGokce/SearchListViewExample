package com.cihadgokce.searchitemlist

import com.cihadgokce.searchitemlist.model.SatelliteList
import com.cihadgokce.searchitemlist.model.SatelliteListItem

object SatallitesFactory {

    fun getSatelliteFakeModel() = SatelliteList(
        dataList = arrayListOf(
            getSatelliteItemFakeModel()
        )
    )

    fun getSatelliteItemFakeModel() = SatelliteListItem(
        id = 0,
        active = false,
        name = "test"
    )

}