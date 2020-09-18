package com.mreigar.transportapp.data.repository

import com.mreigar.transportapp.data.datasource.TransportRemoteDataSourceContract
import com.mreigar.transportapp.data.model.TransportResourceEntity
import com.mreigar.transportapp.domain.executor.Error
import com.mreigar.transportapp.domain.executor.Success
import com.mreigar.transportapp.domain.model.TransportResourceType
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class TransportRepositoryTest {

    @Test
    fun `given transport repository, when transport resources are retrieved from remote, then success result`() {
        val repository = TransportRepository(
            remoteDataSource = object : TransportRemoteDataSourceContract {
                override fun getTransportsAroundLocation(leftLatLong: String, rightLatLong: String): List<TransportResourceEntity> {
                    return listOf(TransportResourceEntity("id", "name", 7.55f, -9.88f, TransportResourceType.RESOURCE_402))
                }
            }
        )

        val result = repository.getTransportsAroundLocation("", "")

        assertThat(result is Success).isTrue()
        assertThat((result as Success).data).isNotNull()
        assertThat(result.data).isNotEmpty()
    }

    @Test
    fun `given transport repository, when transport resources are not retrieved from remote, then error result`() {
        val repository = TransportRepository(
            remoteDataSource = object : TransportRemoteDataSourceContract {
                override fun getTransportsAroundLocation(leftLatLong: String, rightLatLong: String): List<TransportResourceEntity> {
                    throw Exception()
                }
            }
        )

        val result = repository.getTransportsAroundLocation("", "")

        assertThat(result is Error).isTrue()
    }
}