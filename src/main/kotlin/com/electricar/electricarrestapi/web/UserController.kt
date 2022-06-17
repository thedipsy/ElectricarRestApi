package com.electricar.electricarrestapi.web

import com.electricar.electricarrestapi.model.entity.User
import com.electricar.electricarrestapi.service.interfaces.IUserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(private val userService: IUserService) {

    @GetMapping
    fun getAllUsers() : ResponseEntity<List<User>>
        = ResponseEntity.ok(userService.getAll())

    @GetMapping("{id}")
    fun getUserById(@PathVariable id: String) : ResponseEntity<User>
        = ResponseEntity.ok(userService.get(id))

    @PutMapping("/{id}")
    fun updateUser(@PathVariable("id") username: String, @RequestBody user: User) : ResponseEntity<User>
        = ResponseEntity.ok(userService.update(user, username))

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable("id") username:String) : ResponseEntity<Unit>
        = ResponseEntity.ok(userService.delete(username))

}