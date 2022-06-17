package com.electricar.electricarrestapi.repository

import com.electricar.electricarrestapi.model.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface IUserRepository : JpaRepository<User, String>{
    fun findByUsernameAndPassword(username: String, password:String) : Optional<User>
    fun findByUsername(username: String) : Optional<User>
}