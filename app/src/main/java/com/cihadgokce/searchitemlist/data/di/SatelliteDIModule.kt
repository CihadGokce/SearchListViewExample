package com.cihadgokce.searchitemlist.data.di

import com.cihadgokce.searchitemlist.data.api.ApiService
import com.cihadgokce.searchitemlist.data.api.SatelliteRepository
import com.cihadgokce.searchitemlist.data.api.SatelliteRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
abstract class SatelliteDIModule {

    @Binds
    abstract fun provideWalletRepository(
        walletRepository: SatelliteRepositoryImpl
    ):SatelliteRepository

    companion object {
        @Provides
        fun provideWalletApi(
            retrofit: Retrofit
        )  : ApiService = retrofit.create(ApiService::class.java)
    }
}