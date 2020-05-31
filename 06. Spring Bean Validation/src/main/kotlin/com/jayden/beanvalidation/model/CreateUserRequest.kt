package com.jayden.beanvalidation.model

import com.jayden.beanvalidation.entity.User
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

data class CreateUserRequest(

    @field:NotBlank
    var name: String? = "",

    @field:Email
    @field:NotBlank
    var email: String? = "",

    @field:NotBlank
    var password: String? = ""
) {
    fun toEntity(): User {
        var user = User()
        user.name = name
        user.email = email
        user.password = password
        return user
    }
}