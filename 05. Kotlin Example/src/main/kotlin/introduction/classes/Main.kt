package introduction.classes

class Customer

class Contact(val id: Int, var email: String)

fun main() {
    val customer = Customer()
    val contact = Contact(1, "jayden@abc.com")

    println(contact.id)

    contact.email = "gglee@abc.com"
}