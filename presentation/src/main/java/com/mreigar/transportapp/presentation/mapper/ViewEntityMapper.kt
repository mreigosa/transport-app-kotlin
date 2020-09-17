package com.mreigar.transportapp.presentation.mapper

interface ViewEntituyMapper<in D, out V> {
    fun mapToViewEntity(domainEntity: D): V
}