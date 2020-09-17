package com.mreigar.transportapp.data.mapper

interface EntityMapper<E, D> {
    fun mapFromEntity(dataEntity: E): D
    fun mapToEntity(domainEntity: D): E
}