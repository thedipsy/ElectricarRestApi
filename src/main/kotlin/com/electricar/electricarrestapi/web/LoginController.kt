package com.electricar.electricarrestapi.web

import com.electricar.electricarrestapi.config.JwtTokenUtil
import com.electricar.electricarrestapi.model.entity.User
import com.electricar.electricarrestapi.model.jwt.JWTRequest
import com.electricar.electricarrestapi.model.jwt.JWTResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@RestController
@RequestMapping("/login")
class LoginController(
    private val jwtTokenUtil: JwtTokenUtil,
    private val authenticationManager: AuthenticationManager
) {

    @PostMapping
    fun login(@RequestBody request: JWTRequest): ResponseEntity<JWTResponse> {
        return try{
            val authToken = UsernamePasswordAuthenticationToken(request.username, request.password)
            val auth : Authentication = authenticationManager.authenticate(authToken)
            val user : User = auth.principal as User
            val jwtToken : String = jwtTokenUtil.generateAccessToken(user)
            val jwtResponse = JWTResponse(jwtToken, user.username)

            ResponseEntity.ok(jwtResponse)
        }catch (e : Exception){
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }


}