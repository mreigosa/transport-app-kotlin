package com.mreigar.transportapp.network.mapper

import com.mreigar.transportapp.data.model.TransportResourceEntity
import com.mreigar.transportapp.domain.model.TransportResourceType
import com.mreigar.transportapp.network.model.TransportResourceRemoteEntity
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class TransportResourceRemoteEntityMapperTest {

    @Test
    fun `that can map a remote transport resource entity to data entity`() {
        val mapper = TransportResourceRemoteEntityMapper()
        val remoteEntity = TransportResourceRemoteEntity("id", "name", 7.54f, -9.88f, 101)

        val mappedInstance: Any = mapper.mapFromRemote(remoteEntity)

        assertThat(mappedInstance is TransportResourceEntity).isTrue()
        assertThat((mappedInstance as TransportResourceEntity).id).isEqualTo(remoteEntity.id)
        assertThat(mappedInstance.name).isEqualTo(remoteEntity.name)
        assertThat(mappedInstance.latitude).isEqualTo(remoteEntity.latitude)
        assertThat(mappedInstance.longitude).isEqualTo(remoteEntity.longitude)
    }

    @Test
    fun `that can map company zone id to domain value`() {
        val mapper = TransportResourceRemoteEntityMapper()

        val transportUnknown = TransportResourceRemoteEntity("id", "name", 7.54f, -9.88f, 101)
        assertThat(mapper.mapFromRemote(transportUnknown).type).isEqualTo(TransportResourceType.RESOURCE_UNKNOWN)

        val transport402 = TransportResourceRemoteEntity("id", "name", 7.54f, -9.88f, 402)
        assertThat(mapper.mapFromRemote(transport402).type).isEqualTo(TransportResourceType.RESOURCE_402)

        val transport378 = TransportResourceRemoteEntity("id", "name", 7.54f, -9.88f, 378)
        assertThat(mapper.mapFromRemote(transport378).type).isEqualTo(TransportResourceType.RESOURCE_378)

        val transport382 = TransportResourceRemoteEntity("id", "name", 7.54f, -9.88f, 382)
        assertThat(mapper.mapFromRemote(transport382).type).isEqualTo(TransportResourceType.RESOURCE_382)

        val transport467 = TransportResourceRemoteEntity("id", "name", 7.54f, -9.88f, 467)
        assertThat(mapper.mapFromRemote(transport467).type).isEqualTo(TransportResourceType.RESOURCE_467)

        val transport473 = TransportResourceRemoteEntity("id", "name", 7.54f, -9.88f, 473)
        assertThat(mapper.mapFromRemote(transport473).type).isEqualTo(TransportResourceType.RESOURCE_473)

        val transport412 = TransportResourceRemoteEntity("id", "name", 7.54f, -9.88f, 412)
        assertThat(mapper.mapFromRemote(transport412).type).isEqualTo(TransportResourceType.RESOURCE_412)
    }
}