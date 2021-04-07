package br.com.api.person.findById

import br.com.api.person.Person
import br.com.api.person.PersonRepository
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.net.URI
import javax.inject.Inject

@MicronautTest(transactional = false)
internal class FindPersonByIdControllerTest(
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
    fun `must find person by id and return Status OK`() {
//      Cenário
        val person = Person("Matheus", 18)
        val savedPerson = repository.save(person)

//     Ação
        val id = savedPerson.id.toString()
        val request = HttpRequest.GET<Any>(URI.create("/api/v1/persons/${id}"))
        val response = client.toBlocking().exchange(request, Any::class.java)

//      Validação
        assertEquals(HttpStatus.OK, response.status)
        assertNotNull(response.body())
    }

}