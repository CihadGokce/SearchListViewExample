package com.cihadgokce.searchitemlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cihadgokce.searchitemlist.core.BaseViewModel
import com.cihadgokce.searchitemlist.core.di.IoDispatcher
import com.cihadgokce.searchitemlist.core.service.ResponseState
import com.cihadgokce.searchitemlist.data.api.SatelliteRepository
import com.cihadgokce.searchitemlist.model.PositionsList
import com.cihadgokce.searchitemlist.model.SatelliteFullDetail
import com.cihadgokce.searchitemlist.model.SatelliteList
import com.cihadgokce.searchitemlist.model.SatelliteListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: SatelliteRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher) : BaseViewModel() {

    var index = MutableLiveData<Int>(0)
    val satelliteList =MutableLiveData<ArrayList<SatelliteListItem>>(arrayListOf())
    val satelliteDetail =MutableLiveData<SatelliteFullDetail>()
    var isLoading = MutableLiveData<Boolean>(false)

    val positionsList =MutableLiveData<PositionsList>()

    fun getSatelliteList() {
        viewModelScope.launch {
            repository.getSatelliteList().collect {
                when(it){
                    is ResponseState.Loading->{
                        isLoading.value = true
                    }
                    is ResponseState.Success->{
                        satelliteList.value =  (it as ResponseState.Success<SatelliteList>).data.dataList
                        isLoading.value = false
                    }
                }

            }
        }
    }

    fun getSatelliteDetail(id:Int) {
        viewModelScope.launch {
            repository.getSatelliteDetail(id).collect {
                when(it){
                    is ResponseState.Loading->{
                        isLoading.value = true
                    }
                    is ResponseState.Success->{
                        satelliteDetail.value =  (it as ResponseState.Success<SatelliteFullDetail>).data!!
                        isLoading.value = false
                    }
                }

            }
        }
    }

}