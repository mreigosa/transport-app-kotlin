package com.mreigar.transportapp.domain.executor

import kotlinx.coroutines.CoroutineDispatcher

interface DispatcherProvider {
    val main: CoroutineDispatcher
    val background: CoroutineDispatcher
}