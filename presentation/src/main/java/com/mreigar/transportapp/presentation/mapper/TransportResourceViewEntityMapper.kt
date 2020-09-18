package com.mreigar.transportapp.presentation.mapper

import com.mreigar.transportapp.domain.model.TransportResource
import com.mreigar.transportapp.presentation.model.TransportResourceViewEntity

class TransportResourceViewEntityMapper : ViewEntituyMapper<TransportResource, TransportResourceViewEntity> {

    override fun mapToViewEntity(domainEntity: TransportResource) = with(domainEntity) {
        TransportResourceViewEntity(id, name, latitude, longitude, type)
    }

}