package com.mreigar.transportapp.presentation.mock

import com.mreigar.transportapp.domain.executor.Error
import com.mreigar.transportapp.domain.executor.Result
import com.mreigar.transportapp.domain.executor.Success
import com.mreigar.transportapp.domain.executor.usecase.GetTransportsAroundLocationUseCase
import com.mreigar.transportapp.domain.executor.usecase.GetTransportsAroundLocationUseCaseParams
import com.mreigar.transportapp.domain.mock.RepositoryStatus
import com.mreigar.transportapp.domain.model.TransportResource
import com.mreigar.transportapp.domain.model.TransportResourceType
import com.mreigar.transportapp.domain.repository.TransportRepositoryContract

object GetTransportsAroundLocationUseCaseInstrument {

    fun givenGetTransportsAroundLocationUseCase(repositoryStatus: RepositoryStatus): GetTransportsAroundLocationUseCase {
        val useCase = GetTransportsAroundLocationUseCase(
            transportRepository = object : TransportRepositoryContract {
                override fun getTransportsAroundLocation(leftLatLong: String, rightLatLong: String): Result<List<TransportResource>> =
                    when (repositoryStatus) {
                        RepositoryStatus.SUCCESS -> Success(listOf(TransportResource("id", "name", 9.88f, 7.45f, TransportResourceType.RESOURCE_402)))
                        RepositoryStatus.ERROR -> Error()
                    }
            }
        ) withParams GetTransportsAroundLocationUseCaseParams("", "")

        return useCase as GetTransportsAroundLocationUseCase
    }
}