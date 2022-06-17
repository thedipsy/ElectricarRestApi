package com.electricar.electricarrestapi.model.entity

import java.sql.Timestamp
import javax.persistence.*

@Entity
@Table
data class Reservation (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long,
    val date_time : Timestamp,

    @ManyToOne
    val location : Location,
    @ManyToOne
    val user : User,
    @ManyToOne
    val car : Car
)