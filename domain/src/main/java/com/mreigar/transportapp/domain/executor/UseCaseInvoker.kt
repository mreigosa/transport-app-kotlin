package com.mreigar.transportapp.domain.executor

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UseCaseInvoker(
    private val dispatcherProvider: DispatcherProvider = DispatcherProviderImpl()
) : Invoker {

    override fun <P : Any, T> execute(scope: CoroutineScope, useCase: UseCase<P, T>, callback: ((Result<T>) -> Unit)?) {
        scope.launch(dispatcherProvider.main) {
            try {
                val result = withContext(dispatcherProvider.background) { useCase.run() }
                callback?.invoke(result)
            } catch (e: Exception) {
                Error(e)
            }
        }
    }

}