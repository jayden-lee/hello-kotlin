package com.jayden.beanvalidation.controller

import com.jayden.beanvalidation.model.CreateUserRequest
import com.jayden.beanvalidation.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/users")
class UserController(val userService: UserService) {

    @PostMapping
    fun create(@Valid @RequestBody request: CreateUserRequest): ResponseEntity<Any> {
        userService.create(request)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }
}