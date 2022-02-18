package com.cihadgokce.searchitemlist

import androidx.lifecycle.MutableLiveData
import com.cihadgokce.searchitemlist.core.BaseViewModel
import com.cihadgokce.searchitemlist.core.di.IoDispatcher
import com.cihadgokce.searchitemlist.core.service.ResponseState
import com.cihadgokce.searchitemlist.data.api.SatelliteRepository
import com.cihadgokce.searchitemlist.model.BaseResponseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import com.cihadgokce.searchitemlist.model.SatelliteFullDetail
import com.cihadgokce.searchitemlist.model.SatelliteList
import com.cihadgokce.searchitemlist.model.SatelliteListItem
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: SatelliteRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher) : BaseViewModel() {

    val satelliteList =MutableLiveData<ArrayList<SatelliteListItem>>(arrayListOf())
    val satelliteDetail =MutableLiveData<SatelliteFullDetail>()

    fun getSatelliteList() {
        viewModelScope.launch {
            repository.getSatelliteList().collect {
                when(it){
                    is ResponseState.Loading->{

                    }
                    is ResponseState.Success->{
                        satelliteList.value =  (it as ResponseState.Success<SatelliteList>).data.dataList

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

                    }
                    is ResponseState.Success->{
                        satelliteDetail.value =  (it as ResponseState.Success<SatelliteFullDetail>).data!!
                    }
                }

            }
        }
    }

}