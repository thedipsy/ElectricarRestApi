package com.electricar.electricarrestapi.model.jwt

data class JWTResponse (
    val jwtToken : String,
    val username: String
    )