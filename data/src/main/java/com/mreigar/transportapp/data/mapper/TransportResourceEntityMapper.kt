package com.mreigar.transportapp.data.mapper

import com.mreigar.transportapp.data.model.TransportResourceEntity
import com.mreigar.transportapp.domain.model.TransportResource

class TransportResourceEntityMapper : EntityMapper<TransportResourceEntity, TransportResource> {

    override fun mapFromEntity(dataEntity: TransportResourceEntity) = with(dataEntity) {
        TransportResource(id = id, name = name, latitude = latitude, longitude = longitude, type = type)
    }

    override fun mapToEntity(domainEntity: TransportResource) = with(domainEntity) {
        TransportResourceEntity(id = id, name = name, latitude = latitude, longitude = longitude, type = type)
    }
}