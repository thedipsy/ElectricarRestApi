package com.electricar.electricarrestapi.service.implementation

import com.electricar.electricarrestapi.model.entity.User
import com.electricar.electricarrestapi.model.enumeration.Role
import com.electricar.electricarrestapi.model.exception.UserNotFoundException
import com.electricar.electricarrestapi.model.request.RegisterRequest
import com.electricar.electricarrestapi.repository.IUserRepository
import com.electricar.electricarrestapi.service.interfaces.IUserService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: IUserRepository,
    private val passwordEncoder: PasswordEncoder
) : IUserService {

    override fun getAll(): List<User> = userRepository.findAll()

    override fun get(id: String): User = userRepository.findById(id).orElseThrow()


    override fun register(registerRequest: RegisterRequest): User {
        val user = User(
            registerRequest.email,
            passwordEncoder.encode(registerRequest.password),
            registerRequest.name,
            registerRequest.surname,
            registerRequest.phone,
            Role.ROLE_USER
        )
        return userRepository.save(user)
    }


    override fun update(user: User, id: String): User {
        return if(user.username == id && userRepository.existsById(id)){
            userRepository.save(user)
        }
        else throw UserNotFoundException(String.format("User with id %d is not found", id))
    }

    override fun delete(id: String) {
        if(userRepository.existsById(id)){
            userRepository.deleteById(id)
        }
        throw UserNotFoundException(String.format("User with id %d is not found", id))
    }

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(s: String): UserDetails {
        return userRepository.findByUsername(s).orElseThrow { UsernameNotFoundException(s) }
    }


}