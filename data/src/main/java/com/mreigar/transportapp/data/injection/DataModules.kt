package com.mreigar.transportapp.data.injection

import com.mreigar.transportapp.data.repository.TransportRepository
import com.mreigar.transportapp.domain.repository.TransportRepositoryContract
import org.koin.dsl.module

object DataModules {

    val repositoryModule = module {
        factory<TransportRepositoryContract> { TransportRepository(get()) }
    }
}