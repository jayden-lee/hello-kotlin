package com.jayden.study.springkotlin.service

import com.jayden.study.springkotlin.dto.Customer
import org.springframework.stereotype.Service
import java.util.concurrent.ConcurrentHashMap

@Service
class CustomerService {

    companion object {
        val initialCustomers = arrayOf(
                Customer(1, "Kotlin"),
                Customer(2, "Java"),
                Customer(3, "Javascript"))
    }

    val customers = ConcurrentHashMap<Int, Customer>(initialCustomers.associateBy(Customer::id))

    fun getCustomer(id: Int) = customers[id]

    fun deleteCustomer(id: Int) {
        customers.remove(id)
    }

    fun createCustomer(customer: Customer) {
        customers[customer.id] = customer;
    }

    fun updateCustomer(id: Int, customer: Customer) {
        deleteCustomer(id)
        createCustomer(customer)
    }

    fun searchCustomers(nameFilter: String): List<Customer> =
            customers.filter {
                it.value.name.contains(nameFilter, true)
            }.map(Map.Entry<Int, Customer>::value).toList();
}