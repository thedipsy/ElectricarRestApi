package com.electricar.electricarrestapi.service.interfaces

import com.electricar.electricarrestapi.model.entity.User
import com.electricar.electricarrestapi.model.request.RegisterRequest
import org.springframework.security.core.userdetails.UserDetailsService

interface IUserService : UserDetailsService {
    fun getAll(): List<User>
    fun get(id: String): User
    fun register(registerRequest: RegisterRequest): User
    fun update(user: User, id: String): User
    fun delete(id: String)
}