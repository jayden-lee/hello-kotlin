package com.jayden.beanvalidation.service

import com.jayden.beanvalidation.entity.repository.UserRepository
import com.jayden.beanvalidation.model.CreateUserRequest
import org.springframework.stereotype.Service

@Service
class UserService(val userRepository: UserRepository) {

    fun create(request: CreateUserRequest) {
        val newUser = request.toEntity()
        userRepository.save(newUser)
    }
}