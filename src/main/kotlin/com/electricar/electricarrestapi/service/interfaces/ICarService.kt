package com.electricar.electricarrestapi.service.interfaces

import com.electricar.electricarrestapi.model.entity.Car

interface ICarService {
    fun getAll(): List<Car>
    fun get(id: Long): Car
    fun insert(car: Car): Car
    fun update(car: Car, id: Long): Car
    fun delete(id: Long)
}