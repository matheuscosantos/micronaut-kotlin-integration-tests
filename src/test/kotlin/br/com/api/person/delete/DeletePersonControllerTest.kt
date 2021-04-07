package br.com.api.person.delete

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
import javax.inject.Inject

@MicronautTest(transactional = false)
internal class DeletePersonControllerTest(
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
    fun `must delete a person by Id and return Status OK`() {
//      Cenário
        val person = Person("Matheus", 18)
        val savedPerson = repository.save(person)
        val idString = savedPerson.id.toString()

//      Ação
        val request = HttpRequest.DELETE<Any>("/api/v1/persons/${idString}")
        val response = client.toBlocking().exchange(request, Any::class.java)

//      Validação
        assertEquals(HttpStatus.NO_CONTENT, response.status)
    }

}