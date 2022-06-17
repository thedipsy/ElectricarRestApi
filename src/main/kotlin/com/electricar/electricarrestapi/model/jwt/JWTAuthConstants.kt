package com.electricar.electricarrestapi.model.jwt

object JwtAuthConstants {
    const val SECRET : String = "s3cr3tt0k3n"
    const val EXPIRATION_TIME: Long = 864000000 // 10 days
    const val TOKEN_PREFIX : String = "Bearer "
    const val HEADER_STRING : String = "Authorization"
}
