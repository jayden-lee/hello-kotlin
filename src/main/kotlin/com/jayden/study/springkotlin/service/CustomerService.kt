package com.jayden.study.springkotlin.service

import com.jayden.study.springkotlin.dto.Customer
import com.jayden.study.springkotlin.dto.Customer.Telephone
import com.jayden.study.springkotlin.error.CustomerExistException
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.core.publisher.toFlux
import reactor.core.publisher.toMono
import java.util.concurrent.ConcurrentHashMap

/**
 * 도메인 비즈니스 로직 구현
 */
@Service
class CustomerService {

    companion object {
        val initialCustomers = arrayOf(
                Customer(1, "Kotlin", Telephone("+82", "12345678")),
                Customer(2, "Java"),
                Customer(3, "Javascript", Telephone("+44", "8765432")))
    }

    val customers = ConcurrentHashMap<Int, Customer>(initialCustomers.associateBy(Customer::id))

    fun getCustomer(id: Int) = customers[id]?.toMono() ?: Mono.empty()

    fun deleteCustomer(id: Int) {
        customers.remove(id)
    }

    fun createCustomer(customerMono: Mono<Customer>) =
        customerMono.flatMap {
            if (customers[it.id] == null) {
                customers[it.id] = it
                it.toMono()
            } else {
                Mono.error(CustomerExistException("Customer ${it.id} already exist"))
            }
        }

    fun updateCustomer(id: Int, customerMono: Mono<Customer>) {
        deleteCustomer(id)
        createCustomer(customerMono)
    }

    fun searchCustomers(nameFilter: String) =
            customers.filter {
                it.value.name.contains(nameFilter, true)
            }.map(Map.Entry<Int, Customer>::value).toFlux();
}