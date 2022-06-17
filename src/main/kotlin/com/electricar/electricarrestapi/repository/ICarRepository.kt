package com.electricar.electricarrestapi.repository

import com.electricar.electricarrestapi.model.entity.Car
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ICarRepository : JpaRepository<Car, Long>