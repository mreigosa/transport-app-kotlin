package com.mreigar.transportapp.domain.repository

import com.mreigar.transportapp.domain.executor.Result
import com.mreigar.transportapp.domain.model.TransportResource

interface TransportRepositoryContract {
    fun getTransportsAroundLocation(leftLatLong: String, rightLatLong: String): Result<List<TransportResource>>
}