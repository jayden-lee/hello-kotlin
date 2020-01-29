package com.jayden.study.springkotlin

import com.jayden.study.springkotlin.dto.Customer
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.util.concurrent.ConcurrentHashMap

@SpringBootApplication
class SpringKotlinApplication {
    companion object {
        val initialCustomers = arrayOf(
				Customer(1, "Kotlin"),
				Customer(2, "Java"),
				Customer(3, "Javascript"))
    }

	@Bean
	fun customers() = ConcurrentHashMap<Int, Customer>(initialCustomers.associateBy(Customer::id))
}

fun main(args: Array<String>) {
    runApplication<SpringKotlinApplication>(*args)
}
