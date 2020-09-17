package com.mreigar.transportapp.domain.injection

import com.mreigar.transportapp.domain.executor.DispatcherProvider
import com.mreigar.transportapp.domain.executor.DispatcherProviderImpl
import com.mreigar.transportapp.domain.executor.Invoker
import com.mreigar.transportapp.domain.executor.UseCaseInvoker
import com.mreigar.transportapp.domain.executor.usecase.GetTransportsAroundLocationUseCase
import org.koin.dsl.module

object DomainModules {

    val useCaseModule = module {
        factory<Invoker> { UseCaseInvoker(get()) }
        single<DispatcherProvider> { DispatcherProviderImpl() }

        factory { GetTransportsAroundLocationUseCase(get()) }
    }
}
