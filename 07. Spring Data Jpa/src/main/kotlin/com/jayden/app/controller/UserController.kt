package com.jayden.app.controller

import com.jayden.app.domain.User
import com.jayden.app.domain.UserRepoisotry
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/users"])
class UserController(private val userRepository: UserRepoisotry) {

    @GetMapping(value = ["/{userId}"])
    fun getUser(@PathVariable(value = "userId") userId: Long): User {
        return userRepository.findById(userId).orElseThrow {
            RuntimeException("Not Found User")
        }
    }
}