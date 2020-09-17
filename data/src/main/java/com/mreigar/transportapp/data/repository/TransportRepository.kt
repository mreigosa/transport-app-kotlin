package com.mreigar.transportapp.data.repository

import com.mreigar.transportapp.data.datasource.TransportRemoteDataSourceContract
import com.mreigar.transportapp.data.mapper.TransportResourceEntityMapper
import com.mreigar.transportapp.domain.executor.Error
import com.mreigar.transportapp.domain.executor.Result
import com.mreigar.transportapp.domain.executor.Success
import com.mreigar.transportapp.domain.model.TransportResource
import com.mreigar.transportapp.domain.repository.TransportRepositoryContract

class TransportRepository(
    private val remoteDataSource: TransportRemoteDataSourceContract,
) : TransportRepositoryContract {

    private val transportResourceMapper: TransportResourceEntityMapper = TransportResourceEntityMapper()

    override fun getTransportsAroundLocation(leftLatLong: String, rightLatLong: String): Result<List<TransportResource>> {
        return try {
            val remoteResponse = remoteDataSource.getTransportsAroundLocation(leftLatLong, rightLatLong)
            Success(remoteResponse.map { transportResourceMapper.mapFromEntity(it) })
        } catch (e: Exception) {
            Error(e)
        }
    }

}