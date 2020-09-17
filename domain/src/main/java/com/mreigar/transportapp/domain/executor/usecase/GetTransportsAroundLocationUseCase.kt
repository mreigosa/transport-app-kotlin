package com.mreigar.transportapp.domain.executor.usecase

import com.mreigar.transportapp.domain.executor.Result
import com.mreigar.transportapp.domain.executor.UseCase
import com.mreigar.transportapp.domain.model.TransportResource
import com.mreigar.transportapp.domain.repository.TransportRepositoryContract

class GetTransportsAroundLocationUseCase(
    private val transportRepository: TransportRepositoryContract
) : UseCase<GetTransportsAroundLocationUseCaseParams, List<TransportResource>>() {

    override suspend fun run(): Result<List<TransportResource>> {
        return transportRepository.getTransportsAroundLocation(useCaseParams.leftLatLong, useCaseParams.rightLatLong)
    }
}

data class GetTransportsAroundLocationUseCaseParams(
    val leftLatLong: String,
    val rightLatLong: String
)