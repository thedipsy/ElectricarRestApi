package com.electricar.electricarrestapi.model.entity

import javax.persistence.*

@Entity
@Table
data class Car(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,
        val carModel: String,
        val carPlate: String,

        @ManyToOne
        //@JoinColumn(name = "user")
        val user: User
)