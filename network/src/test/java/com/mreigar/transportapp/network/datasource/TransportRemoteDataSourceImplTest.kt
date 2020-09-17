package com.mreigar.transportapp.network.datasource

import com.mreigar.transportapp.data.datasource.TransportRemoteDataSourceContract
import com.mreigar.transportapp.network.api.NetworkApi
import com.mreigar.transportapp.network.api.TransportApi
import com.mreigar.transportapp.network.mock.MockServer
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest

class TransportRemoteDataSourceImplTest : AutoCloseKoinTest() {

    private lateinit var mockServer: MockServer
    private lateinit var transportDataSource: TransportRemoteDataSourceContract

    @Before
    fun before() {
        mockServer = MockServer.create()

        val transportApi = NetworkApi().provideApi(mockServer.start(), TransportApi::class.java)

        startKoin {
            modules(
                listOf(
                    module {
                        single(override = true) { transportApi }
                    }
                )
            )
        }

        transportDataSource = TransportRemoteDataSourceImpl(transportApi)
    }

    @After
    fun after() {
        mockServer.shutdown()
    }

    @Test
    fun `given success response transport list is retrieved`() {
        mockServer.enqueueFile("getTransportsAroundLocation.json")

        val response = transportDataSource.getTransportsAroundLocation("38.711046,-9.160096", "38.711046,-9.160096")

        assertThat(response).isNotNull
        assertThat(response).isNotEmpty
    }

    @Test(expected = Exception::class)
    fun `that cannot fetch transports`() {
        mockServer.enqueue(500)

        transportDataSource.getTransportsAroundLocation("38.711046,-9.160096", "38.711046,-9.160096")
    }
}