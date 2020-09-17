package com.mreigar.transportapp.network.datasource

import com.mreigar.transportapp.data.datasource.TransportRemoteDataSourceContract
import com.mreigar.transportapp.data.model.TransportResourceEntity
import com.mreigar.transportapp.network.api.TransportApi
import com.mreigar.transportapp.network.executeCall
import com.mreigar.transportapp.network.mapper.TransportResourceRemoteEntityMapper
import retrofit2.http.Query

class TransportRemoteDataSourceImpl(
    private val api: TransportApi
) : TransportRemoteDataSourceContract {

    private val transportResourceMapper = TransportResourceRemoteEntityMapper()

    override fun getTransportsAroundLocation(leftLatLong: String, rightLatLong: String): List<TransportResourceEntity> {
        val response = api.getTransportsAroundLocation(leftLatLong, rightLatLong).executeCall()
        return response?.let {
            it.map { postRemoteEntity -> transportResourceMapper.mapFromRemote(postRemoteEntity) }
        } ?: throw Exception()
    }

}