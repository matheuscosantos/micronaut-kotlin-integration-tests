package br.com.api.person.update

import br.com.api.person.Person
import br.com.api.person.PersonRepository
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.net.URI
import javax.inject.Inject

@MicronautTest(transactional = false)
internal class UpdatePersonControllerTest(
    val repository: PersonRepository,
) {

    @field:Inject
    @field:Client("/")
    lateinit var client: HttpClient

    @BeforeEach
    fun setup() {
        repository.deleteAll()
    }

    @Test
    fun `must update a person and return Status OK`() {
//      Cenário
        val person = Person("Matheus", 18)
        val savedPerson = repository.save(person)
        val updatePersonRequestBody = UpdatePersonRequest("José", 31)
        val idString = savedPerson.id.toString()

//      Ação
        val request = HttpRequest.PUT<Any>(URI.create("/api/v1/persons/${idString}"), updatePersonRequestBody)
        val response = client.toBlocking().exchange(request, Any::class.java)
        val updatedPerson = repository.findById(savedPerson.id)

//      Validação
        assertEquals(HttpStatus.OK, response.status)
        assertEquals("José", updatedPerson.get().name)
        assertEquals(31, updatedPerson.get().age)
    }

}