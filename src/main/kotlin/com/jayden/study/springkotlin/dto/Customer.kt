package com.jayden.study.springkotlin.dto

data class Customer(var id: Int = 0, var name: String = "", val telephone: Telephone? = null) {
    data class Telephone(var countryCode: String = "", var telephoneNumber: String = "")
}