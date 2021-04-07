package br.com.api.person.findById

import br.com.api.person.PersonRepository
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import org.slf4j.LoggerFactory
import java.util.*

@Controller("/api/v1/persons/")
class FindByIdController(private val personRepository: PersonRepository) {
    private val LOGGER = LoggerFactory.getLogger(this::class.java)

    @Get("{id}")
    fun findById(@PathVariable id: UUID): HttpResponse<Any> {

        val possiblePerson = personRepository.findById(id)

        if (possiblePerson.isEmpty) {
            return HttpResponse.notFound()
        }

        return HttpResponse.ok(possiblePerson)
    }
}