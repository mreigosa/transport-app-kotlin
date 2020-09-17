package com.mreigar.transportapp.network.injection

import com.mreigar.transportapp.data.datasource.TransportRemoteDataSourceContract
import com.mreigar.transportapp.network.api.NetworkApi
import com.mreigar.transportapp.network.api.TransportApi
import com.mreigar.transportapp.network.datasource.TransportRemoteDataSourceImpl
import org.koin.dsl.module

object NetworkModules {

    val networkModule = module {
        single { NetworkApi().provideApi(TransportApi.BASE_URL, TransportApi::class.java) }

        factory<TransportRemoteDataSourceContract> { TransportRemoteDataSourceImpl(get()) }
    }
}