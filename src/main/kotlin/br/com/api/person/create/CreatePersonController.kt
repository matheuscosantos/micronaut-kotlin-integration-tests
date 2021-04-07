package br.com.api.person.create

import br.com.api.person.Person
import br.com.api.person.PersonRepository
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import org.slf4j.LoggerFactory
import javax.inject.Singleton
import javax.validation.Valid

@Singleton
@Validated
@Controller("/api/v1/persons/")
class CreatePersonController(private val personRepository: PersonRepository) {

    private val LOGGER = LoggerFactory.getLogger(this::class.java)

    @Post
    fun create(@Body @Valid request: CreatePersonRequest): HttpResponse<Any> {
        LOGGER.info("Registering new person with name ${request.name} with ${request.age} years old")
        val person = request.toPerson()
        personRepository.save(person).also {
            LOGGER.info("New Person: ${it.toString()} created at ${it.createdAt}")
            return HttpResponse.status(HttpStatus.CREATED)
        }
    }
}