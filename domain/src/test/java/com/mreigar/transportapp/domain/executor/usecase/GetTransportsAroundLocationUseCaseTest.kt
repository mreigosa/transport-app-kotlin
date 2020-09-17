package com.mreigar.transportapp.domain.executor.usecase

import com.mreigar.transportapp.domain.executor.Error
import com.mreigar.transportapp.domain.executor.Success
import com.mreigar.transportapp.domain.mock.GetTransportsAroundLocationUseCaseInstrument.givenGetTransportsAroundLocationUseCase
import com.mreigar.transportapp.domain.mock.RepositoryStatus
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class GetTransportsAroundLocationUseCaseTest {

    @Test
    fun `given a use case then invoke with success result`() {
        val useCase = givenGetTransportsAroundLocationUseCase(RepositoryStatus.SUCCESS)

        runBlocking {
            val result = useCase.run()

            assertThat(result).isNotNull()
            assertThat(result is Success).isTrue()
        }
    }

    @Test
    fun `given a use case then invoke with error result`() {
        val useCase = givenGetTransportsAroundLocationUseCase(RepositoryStatus.ERROR)

        runBlocking {
            val result = useCase.run()

            assertThat(result).isNotNull()
            assertThat(result is Error).isTrue()
        }
    }
}