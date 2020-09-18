package com.mreigar.transportapp.view

import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.mreigar.transportapp.domain.model.TransportResourceType
import com.mreigar.transportapp.presentation.model.TransportResourceViewEntity
import com.mreigar.transportapp.view.transportresourcemap.TransportResourceMapEntity
import com.mreigar.transportapp.view.transportresourcemap.TransportResourceMapEntityMapper
import org.assertj.core.api.Assertions
import org.junit.Test

class TransportResourceMapEntityMapperTest {

    @Test
    fun `that can map a transport resource view entity to map entity`() {
        val mapper = TransportResourceMapEntityMapper()
        val viewEntity = TransportResourceViewEntity("id", "name", 7.54f, -9.88f, TransportResourceType.RESOURCE_402)

        val mappedInstance: Any = mapper.mapToMapEntity(viewEntity)

        Assertions.assertThat(mappedInstance is TransportResourceMapEntity).isTrue()
        Assertions.assertThat((mappedInstance as TransportResourceMapEntity).id).isEqualTo(viewEntity.id)
        Assertions.assertThat(mappedInstance.name).isEqualTo(viewEntity.name)
        Assertions.assertThat(mappedInstance.position.latitude).isEqualTo(viewEntity.latitude.toDouble())
        Assertions.assertThat(mappedInstance.position.longitude).isEqualTo(viewEntity.longitude.toDouble())
    }

    @Test
    fun `that resource type is mapped to map marker icon`() {
        val mapper = TransportResourceMapEntityMapper()

        val transportUnknown = TransportResourceViewEntity("id", "name", 7.54f, -9.88f, TransportResourceType.RESOURCE_UNKNOWN)
        Assertions.assertThat(mapper.mapToMapEntity(transportUnknown).color).isEqualTo(BitmapDescriptorFactory.HUE_ORANGE)

        val transport402 = TransportResourceViewEntity("id", "name", 7.54f, -9.88f, TransportResourceType.RESOURCE_402)
        Assertions.assertThat(mapper.mapToMapEntity(transport402).color).isEqualTo(BitmapDescriptorFactory.HUE_MAGENTA)

        val transport378 = TransportResourceViewEntity("id", "name", 7.54f, -9.88f, TransportResourceType.RESOURCE_378)
        Assertions.assertThat(mapper.mapToMapEntity(transport378).color).isEqualTo(BitmapDescriptorFactory.HUE_AZURE)

        val transport382 = TransportResourceViewEntity("id", "name", 7.54f, -9.88f, TransportResourceType.RESOURCE_382)
        Assertions.assertThat(mapper.mapToMapEntity(transport382).color).isEqualTo(BitmapDescriptorFactory.HUE_YELLOW)

        val transport467 = TransportResourceViewEntity("id", "name", 7.54f, -9.88f, TransportResourceType.RESOURCE_467)
        Assertions.assertThat(mapper.mapToMapEntity(transport467).color).isEqualTo(BitmapDescriptorFactory.HUE_GREEN)

        val transport473 = TransportResourceViewEntity("id", "name", 7.54f, -9.88f, TransportResourceType.RESOURCE_473)
        Assertions.assertThat(mapper.mapToMapEntity(transport473).color).isEqualTo(BitmapDescriptorFactory.HUE_CYAN)

        val transport412 = TransportResourceViewEntity("id", "name", 7.54f, -9.88f, TransportResourceType.RESOURCE_412)
        Assertions.assertThat(mapper.mapToMapEntity(transport412).color).isEqualTo(BitmapDescriptorFactory.HUE_BLUE)
    }
}