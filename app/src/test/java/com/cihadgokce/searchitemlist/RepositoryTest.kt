package com.cihadgokce.searchitemlist

import android.content.Context
import com.cihadgokce.searchitemlist.core.service.ResponseState
import com.cihadgokce.searchitemlist.data.api.DataSource
import com.cihadgokce.searchitemlist.data.api.SatelliteRepositoryImpl
import com.google.common.truth.Truth
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class RepositoryTest {
    private val testDispatcher = TestCoroutineDispatcher()

    @RelaxedMockK
    private lateinit var repository: SatelliteRepositoryImpl
    private var dataSource = mockk<DataSource>()
    private var contex = mockk<Context>()

    @Before
    fun before() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcher)
        repository = SatelliteRepositoryImpl(
            testDispatcher, contex, dataSource
        )
    }

    @Test
    fun getSatelliteList_Success() = runBlocking {
        val expectedResponse = SatallitesFactory.getSatelliteFakeModel()
        coEvery {
            dataSource.getSatelliteList()
        } returns expectedResponse

        val result = repository.getSatelliteList().collect {
            when (it) {
                is ResponseState.Success -> {
                    Truth.assertThat(expectedResponse).isEqualTo(it)
                }
            }
        }

    }

    @After
    fun after() {

    }


}