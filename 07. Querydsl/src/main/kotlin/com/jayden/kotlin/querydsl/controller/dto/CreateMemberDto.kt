package com.jayden.kotlin.querydsl.controller.dto

import javax.validation.constraints.NotEmpty

data class CreateMemberDto(

    @NotEmpty
    val name: String,

    @NotEmpty
    val email: String,

    @NotEmpty
    val teamId: Long

)
