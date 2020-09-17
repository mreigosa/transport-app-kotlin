package com.mreigar.transportapp.data.datasource

import com.mreigar.transportapp.data.model.TransportResourceEntity

interface TransportRemoteDataSourceContract {
    fun getTransportsAroundLocation(leftLatLong: String, rightLatLong: String): List<TransportResourceEntity>
}