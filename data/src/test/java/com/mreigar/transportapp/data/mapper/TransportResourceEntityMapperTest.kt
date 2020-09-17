package com.mreigar.transportapp.data.mapper

import com.mreigar.transportapp.data.model.TransportResourceEntity
import com.mreigar.transportapp.domain.model.TransportResource
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class TransportResourceEntityMapperTest {

    @Test
    fun `given a data entity, map to domain is correct`() {
        val dataEntity = TransportResourceEntity("id", "name", 7.55f, -9.88f, 101)

        val mappedInstance: Any = TransportResourceEntityMapper().mapFromEntity(dataEntity)

        assertThat(mappedInstance is TransportResource).isTrue()
        assertThat((mappedInstance as TransportResource).id).isEqualTo(dataEntity.id)
        assertThat(mappedInstance.name).isEqualTo(dataEntity.name)
        assertThat(mappedInstance.latitude).isEqualTo(dataEntity.latitude)
        assertThat(mappedInstance.longitude).isEqualTo(dataEntity.longitude)
        assertThat(mappedInstance.companyZoneId).isEqualTo(dataEntity.companyZoneId)
    }

    @Test
    fun `given a domain entity, map to entity is correct`() {
        val domainEntity = TransportResource("id", "name", 6.33f, -5.75f, 202)

        val mappedInstance: Any = TransportResourceEntityMapper().mapToEntity(domainEntity)

        assertThat(mappedInstance is TransportResourceEntity).isTrue()
        assertThat((mappedInstance as TransportResourceEntity).id).isEqualTo(domainEntity.id)
        assertThat(mappedInstance.name).isEqualTo(domainEntity.name)
        assertThat(mappedInstance.latitude).isEqualTo(domainEntity.latitude)
        assertThat(mappedInstance.longitude).isEqualTo(domainEntity.longitude)
        assertThat(mappedInstance.companyZoneId).isEqualTo(domainEntity.companyZoneId)
    }
}