package com.mreigar.transportapp.network.api

import com.mreigar.transportapp.network.model.TransportResourceRemoteEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TransportApi {

    companion object {
        const val BASE_URL = "https://apidev.meep.me"
    }

    @GET("tripplan/api/v1/routers/lisboa/resources")
    fun getTransportsAroundLocation(
        @Query("lowerLeftLatLon") leftLatLong: String,
        @Query("upperRightLatLon") rightLatLong: String
    ): Call<List<TransportResourceRemoteEntity>>

}