package com.electricar.electricarrestapi.web

import com.electricar.electricarrestapi.model.entity.User
import com.electricar.electricarrestapi.service.interfaces.IUserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/register")
class RegisterController(private val userService: IUserService) {

    @PostMapping
    fun register(@RequestBody user: User) : ResponseEntity<User>
            = ResponseEntity.ok(userService.register(user))

}