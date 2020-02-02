package com.jayden.study.springkotlin.error

class CustomerExistException(override val message: String) : Exception(message)