package com.mreigar.transportapp.network.mapper

import com.mreigar.transportapp.data.model.TransportResourceEntity
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
        assertThat(mappedInstance.companyZoneId).isEqualTo(remoteEntity.companyZoneId)
    }
}