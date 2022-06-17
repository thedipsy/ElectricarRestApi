package com.electricar.electricarrestapi.repository

import com.electricar.electricarrestapi.model.entity.Reservation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface IReservationRepository : JpaRepository<Reservation, Long>