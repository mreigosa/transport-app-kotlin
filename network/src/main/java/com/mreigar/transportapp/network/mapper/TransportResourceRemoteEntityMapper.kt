package com.mreigar.transportapp.network.mapper

import com.mreigar.transportapp.data.model.TransportResourceEntity
import com.mreigar.transportapp.domain.model.TransportResourceType
import com.mreigar.transportapp.network.model.TransportResourceRemoteEntity

class TransportResourceRemoteEntityMapper : RemoteEntityMapper<TransportResourceRemoteEntity, TransportResourceEntity> {

    override fun mapFromRemote(remoteEntity: TransportResourceRemoteEntity) = with(remoteEntity) {
        TransportResourceEntity(id = id, name = name, latitude = latitude, longitude = longitude, type = getResourceType(companyZoneId))
    }

    private fun getResourceType(companyZoneId: Int): TransportResourceType {
        return when (companyZoneId) {
            402 -> TransportResourceType.RESOURCE_402
            378 -> TransportResourceType.RESOURCE_378
            382 -> TransportResourceType.RESOURCE_382
            467 -> TransportResourceType.RESOURCE_467
            473 -> TransportResourceType.RESOURCE_473
            412 -> TransportResourceType.RESOURCE_412
            else -> TransportResourceType.RESOURCE_UNKNOWN
        }
    }
}