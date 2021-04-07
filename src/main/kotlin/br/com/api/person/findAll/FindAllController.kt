package br.com.api.person.findAll

import br.com.api.person.Person
import br.com.api.person.PersonRepository
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import org.slf4j.LoggerFactory

@Controller("/api/v1/persons/")
class FindAllController(
    private val personRepository: PersonRepository
) {
    private val LOGGER = LoggerFactory.getLogger(this::class.java)

    @Get
    fun findAll(): MutableList<Person> {
        return personRepository.findAll()
    }
}