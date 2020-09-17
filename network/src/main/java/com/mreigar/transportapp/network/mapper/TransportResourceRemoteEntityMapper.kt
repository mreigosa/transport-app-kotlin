package com.mreigar.transportapp.network.mapper

import com.mreigar.transportapp.data.model.TransportResourceEntity
import com.mreigar.transportapp.network.model.TransportResourceRemoteEntity

class TransportResourceRemoteEntityMapper : RemoteEntityMapper<TransportResourceRemoteEntity, TransportResourceEntity> {

    override fun mapFromRemote(remoteEntity: TransportResourceRemoteEntity) = with(remoteEntity) {
        TransportResourceEntity(id = id, name = name, latitude = latitude, longitude = longitude, companyZoneId = companyZoneId)
    }
}