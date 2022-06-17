package com.electricar.electricarrestapi.repository

import com.electricar.electricarrestapi.model.entity.Location
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ILocationRepository : JpaRepository<Location, Long>