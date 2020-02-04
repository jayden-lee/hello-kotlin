package com.jayden.study.springkotlin.service

import com.jayden.study.springkotlin.domain.Customer
import com.jayden.study.springkotlin.repository.CustomerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

/**
 * 도메인 비즈니스 로직 구현
 */
@Service
class CustomerService {

    @Autowired
    lateinit var customerRepository: CustomerRepository

    fun getCustomer(id: Int) = customerRepository.findById(id)

    fun deleteCustomer(id: Int) =
        customerRepository.deleteById(id).map { it.deletedCount > 0 }

    fun createCustomer(customer: Mono<Customer>) =
        customerRepository.create(customer)

    fun updateCustomer(id: Int, customerMono: Mono<Customer>) {
        deleteCustomer(id)
        createCustomer(customerMono)
    }

    fun searchCustomers(nameFilter: String) =
        customerRepository.findCustomer(nameFilter)
}