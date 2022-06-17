package com.electricar.electricarrestapi.service.interfaces

import com.electricar.electricarrestapi.model.entity.User
import org.springframework.security.core.userdetails.UserDetailsService

interface IUserService : UserDetailsService {
    fun getAll(): List<User>
    fun get(id: String): User
    fun register(user: User): User
    fun update(user: User, id: String): User
    fun delete(id: String)
}