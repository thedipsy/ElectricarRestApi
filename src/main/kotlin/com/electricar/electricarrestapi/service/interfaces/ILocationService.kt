package com.electricar.electricarrestapi.service.interfaces

import com.electricar.electricarrestapi.model.entity.Location

interface ILocationService {
    fun getAll(): List<Location>
    fun get(id: Long): Location
    fun insert(location: Location): Location
    fun update(location: Location, id: Long): Location
    fun delete(id: Long)
}