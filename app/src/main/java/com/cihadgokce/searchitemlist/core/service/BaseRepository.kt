package com.cihadgokce.searchitemlist.core.service

import android.content.Context
import com.cihadgokce.searchitemlist.core.helper.NetworkHelper
import com.cihadgokce.searchitemlist.core.util.Utils
import com.cihadgokce.searchitemlist.model.*
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.json.JSONException
import org.json.JSONObject
import retrofit2.HttpException
import java.lang.reflect.Type

// servis çağırımı tetiklendiği yer manipule ediliyor json gönderiliyor


suspend fun <T> fetch(
    context: Context,
    dispatcher: CoroutineDispatcher,
    detailId:Int = 0,
    apiCall: suspend () -> T
): Flow<ResponseState<BaseResponseModel>> = flow {
    emit(ResponseState.Loading)
    if (NetworkHelper.isNetworkConnection(context)) {
        if(detailId>0){
            emit(ResponseState.Success(getSatelliteDetail(context,detailId)))
        }else{
            emit(ResponseState.Success(getSatelliteList(context)))
        }

    } else {
        emit(ResponseState.Error("0", "Internet Baglantınızı kontrol ediniz!"))
    }
}.catch {
    when (it) {
        is java.io.IOException -> emit(ResponseState.Error("1", it.message.toString()))
        is HttpException -> {
            emit(ResponseState.Error("2", it.response()?.errorBody().toString()))
        }
        else -> {
            emit(ResponseState.Error("3", "İşleminiz yapılırken anlık hata alındı"))
        }
    }
}.flowOn(dispatcher)

fun getSatelliteList(context: Context): SatelliteList {

    try {
        // JSON nesnemiz olduğu için nesneyi alıyoruz
        //Burada JSON nesnesini döndüren bir Yöntemi çağırıyoruz.

        val obj = JSONObject(Utils(context).getJSONFromAssets("Satellite-list.Json")!!)
        // getJSONArray kullanarak JSONArray adlı kullanıcıları getir
        val satellitesArray = obj.getJSONArray("satellites").toString()

        val moshi = Moshi.Builder().build()
        val type: Type = Types.newParameterizedType(
            List::class.java,
            SatelliteListItem::class.java
        )

        val adapter: JsonAdapter<List<SatelliteListItem>> =
            moshi.adapter<List<SatelliteListItem>>(type)
        val satList: List<SatelliteListItem>? = adapter.fromJson(satellitesArray)
        val response = SatelliteList(satList!!.toCollection(ArrayList()))
        return response


    } catch (e: JSONException) {
        e.printStackTrace()
    }

    return SatelliteList()
}

fun getSatelliteDetail(context: Context,id: Int): SatelliteFullDetail {
    var satelDetail = SatelliteFullDetail()

    try {
        val obj = JSONObject(Utils(context).getJSONFromAssets("Satellite-detail.Json")!!)
        // getJSONArray kullanarak JSONArray adlı kullanıcıları getir
        val satellitesArray = obj.getJSONArray("satelliteDetail").toString()

        val moshi = Moshi.Builder().build()
        val type: Type = Types.newParameterizedType(
            List::class.java,
            SatelliteDetail::class.java
        )
        val adapter: JsonAdapter<List<SatelliteDetail>> =
            moshi.adapter<List<SatelliteDetail>>(type)
        val satList: List<SatelliteDetail> =
            adapter.fromJson(satellitesArray)!!.toCollection(ArrayList())

        for (item in satList) {

            if (item.id == id) {
                satelDetail.detail = item
                break
            }
        }
    } catch (e: JSONException) {
        e.printStackTrace()
    }
    satelDetail.list = getPositionDetail(context,id)
    return satelDetail
}

fun getPositionDetail(context: Context,id: Int): PositionsList {

    var position = PositionsList()

    try {
        val obj = JSONObject(Utils(context).getJSONFromAssets("Positions.Json")!!)
        // getJSONArray kullanarak JSONArray adlı kullanıcıları getir
        val positionsArray = obj.getJSONArray("list").toString()

        val moshi = Moshi.Builder().build()
        val type: Type = Types.newParameterizedType(
            List::class.java,
            PositionsList::class.java
        )

        val adapter: JsonAdapter<List<PositionsList>> =
            moshi.adapter<List<PositionsList>>(type)
        val satList: List<PositionsList> = adapter.fromJson(positionsArray)!!.toCollection(ArrayList())

        for (item in satList) {

            if (item.id == id.toString()) {
                position = item
                break
            }
        }
    } catch (e: JSONException) {
        e.printStackTrace()
    }
    return position
}






