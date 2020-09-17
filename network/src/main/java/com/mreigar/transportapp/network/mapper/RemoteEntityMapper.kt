package com.mreigar.transportapp.network.mapper

interface RemoteEntityMapper<in R, out D> {
    fun mapFromRemote(remoteEntity: R): D
}