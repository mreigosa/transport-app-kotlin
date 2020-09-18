package com.mreigar.transportapp.presentation.test

import com.mreigar.transportapp.domain.model.TransportResource
import com.mreigar.transportapp.domain.model.TransportResourceType
import com.mreigar.transportapp.presentation.mapper.TransportResourceViewEntityMapper
import com.mreigar.transportapp.presentation.model.TransportResourceViewEntity
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class TransportResourceViewEntityMapperTest {

    @Test
    fun `that can map a transport resource to presentation entity`() {
        val mapper = TransportResourceViewEntityMapper()
        val domainEntity = TransportResource("id", "name", 7.54f, -9.88f, TransportResourceType.RESOURCE_402)

        val mappedInstance: Any = mapper.mapToViewEntity(domainEntity)

        assertThat(mappedInstance is TransportResourceViewEntity).isTrue()
        assertThat((mappedInstance as TransportResourceViewEntity).id).isEqualTo(domainEntity.id)
        assertThat(mappedInstance.name).isEqualTo(domainEntity.name)
        assertThat(mappedInstance.latitude).isEqualTo(domainEntity.latitude)
        assertThat(mappedInstance.longitude).isEqualTo(domainEntity.longitude)
        assertThat(mappedInstance.type).isEqualTo(domainEntity.type)
    }
}