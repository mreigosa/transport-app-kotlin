package com.mreigar.transportapp.domain.executor

import com.mreigar.transportapp.domain.mock.DomainInstrument
import com.mreigar.transportapp.domain.mock.DomainInstrument.givenAGenericSuccessResultUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineScope
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class UseCaseInvokerTest {

    @ExperimentalCoroutinesApi
    @Test
    fun `that can execute a use case`() {
        val invoker = UseCaseInvoker(DomainInstrument.TestContextProvider())
        val useCase = givenAGenericSuccessResultUseCase()

        invoker.execute(TestCoroutineScope(), useCase) {
            assertThat(it is Success).isTrue()
            assertThat((it as Success).data).isEqualTo("Awesome Result")
        }
    }
}