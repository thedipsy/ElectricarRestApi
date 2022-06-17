package com.electricar.electricarrestapi.model.entity

import javax.persistence.*

@Entity
@Table
data class Location (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long ,
    val address : String,
    val latitude : Float ,
    val longitude : Float
)