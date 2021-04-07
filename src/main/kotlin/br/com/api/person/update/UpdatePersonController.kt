package br.com.api.person.update

import br.com.api.person.PersonRepository
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Put
import io.micronaut.validation.Validated
import org.slf4j.LoggerFactory
import java.time.LocalDateTime
import java.util.*
import javax.inject.Singleton
import javax.transaction.Transactional
import javax.validation.Valid

@Transactional
@Validated
@Controller("/api/v1/persons/")
class UpdatePersonController(private val personRepository: PersonRepository) {

    private val LOGGER = LoggerFactory.getLogger(this::class.java)

    @Put("{id}")
    fun update(@Body @Valid request: UpdatePersonRequest, @PathVariable id: UUID): HttpResponse<Any> {

        LOGGER.info("Updating a person with Id ${id}")

        val possiblePerson = personRepository.findById(id)

        if (possiblePerson.isEmpty()){
            LOGGER.info("Person with Id ${id} not found")
            return HttpResponse.notFound()
        }

        val person = possiblePerson.get()
        person.name = request.name
        person.age = request.age
        person.updatedAt = LocalDateTime.now()

        personRepository.update(person)

        LOGGER.info("Person: ${person.toString()} updated at ${person.updatedAt}")
        return HttpResponse.ok()
    }

}