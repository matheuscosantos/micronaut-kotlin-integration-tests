package br.com.api.person.delete

import br.com.api.person.PersonRepository
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.PathVariable
import org.slf4j.LoggerFactory
import java.util.*

@Controller("/api/v1/persons/")
class DeletePersonController(
    val personRepository: PersonRepository
) {

    val LOGGER = LoggerFactory.getLogger(this::class.java)

    @Delete("{id}")
    fun delete(@PathVariable id: UUID): HttpResponse<Any> {

        LOGGER.info("Deleting Person with Id ${id}")
        val possiblePerson = personRepository.findById(id)

        if (possiblePerson.isEmpty()){
            LOGGER.info("Person with Id ${id} not found")
            return HttpResponse.notFound()
        }

        personRepository.deleteById(id)
        LOGGER.info("Person with Id ${id} deleted")
        return HttpResponse.noContent()
    }

}