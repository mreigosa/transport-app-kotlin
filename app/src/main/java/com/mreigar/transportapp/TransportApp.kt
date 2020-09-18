package com.mreigar.transportapp

import android.app.Application
import com.mreigar.transportapp.data.injection.DataModules
import com.mreigar.transportapp.domain.injection.DomainModules
import com.mreigar.transportapp.injection.AppModules
import com.mreigar.transportapp.network.injection.NetworkModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TransportApp : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoinModules()
    }

    private fun initKoinModules() {
        startKoin {
            androidContext(this@TransportApp)
            modules(
                NetworkModules.networkModule,
                DataModules.repositoryModule,
                DomainModules.domainModule,
                AppModules.presentationModules
            )
        }
    }
}