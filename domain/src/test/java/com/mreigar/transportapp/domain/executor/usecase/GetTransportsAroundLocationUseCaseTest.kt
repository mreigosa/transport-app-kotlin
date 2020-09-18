package com.mreigar.transportapp.domain.executor.usecase

import com.mreigar.transportapp.domain.executor.Error
import com.mreigar.transportapp.domain.executor.Result
import com.mreigar.transportapp.domain.executor.Success
import com.mreigar.transportapp.domain.model.TransportResource
import com.mreigar.transportapp.domain.model.TransportResourceType
import com.mreigar.transportapp.domain.repository.TransportRepositoryContract
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class GetTransportsAroundLocationUseCaseTest {

    @Test
    fun `given a use case then invoke with success result`() {
        val useCase = GetTransportsAroundLocationUseCase(
            transportRepository = object : TransportRepositoryContract {
                override fun getTransportsAroundLocation(leftLatLong: String, rightLatLong: String): Result<List<TransportResource>> =
                    Success(listOf(TransportResource("id", "name", 9.88f, 7.45f, TransportResourceType.RESOURCE_402)))
            }
        ) withParams GetTransportsAroundLocationUseCaseParams("", "")

        runBlocking {
            val result = useCase.run()

            assertThat(result).isNotNull()
            assertThat(result is Success).isTrue()
        }
    }

    @Test
    fun `given a use case then invoke with error result`() {
        val useCase = GetTransportsAroundLocationUseCase(
            transportRepository = object : TransportRepositoryContract {
                override fun getTransportsAroundLocation(leftLatLong: String, rightLatLong: String): Result<List<TransportResource>> = Error()
            }
        ) withParams GetTransportsAroundLocationUseCaseParams("", "")

        runBlocking {
            val result = useCase.run()

            assertThat(result).isNotNull()
            assertThat(result is Error).isTrue()
        }
    }
}