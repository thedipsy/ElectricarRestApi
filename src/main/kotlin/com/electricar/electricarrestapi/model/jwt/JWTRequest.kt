package com.electricar.electricarrestapi.model.jwt

data class JWTRequest(
    val username: String,
    val password: String
)