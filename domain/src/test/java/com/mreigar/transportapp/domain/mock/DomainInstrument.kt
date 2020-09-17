package com.mreigar.transportapp.domain.mock

import com.mreigar.transportapp.domain.executor.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

enum class RepositoryStatus {
    SUCCESS, ERROR
}

object DomainInstrument {

    fun givenAGenericSuccessResultUseCase() = object : UseCase<Unit, String>() {
        override suspend fun run(): Result<String> = Success("Awesome Result")
    }

    fun givenInvoker() = UseCaseInvoker(TestContextProvider())

    class TestContextProvider : DispatcherProvider {
        override val main: CoroutineDispatcher = Dispatchers.Unconfined
        override val background: CoroutineDispatcher = Dispatchers.Unconfined
    }
}