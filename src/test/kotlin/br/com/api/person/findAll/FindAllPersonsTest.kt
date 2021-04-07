package br.com.api.person.findAll

import br.com.api.person.Person
import br.com.api.person.PersonRepository
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.net.URI
import javax.inject.Inject

@MicronautTest(transactional = false)
internal class FindAllPersonsTest(
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
    fun `must find all person and return Status OK`() {
//      Cenário
        val person1 = Person("Matheus", 31)
        repository.save(person1)

        val person2 = Person("Carla", 25)
        repository.save(person2)

//     Ação
        val request = HttpRequest.GET<Any>(URI.create("/api/v1/persons/"))
        val response = client.toBlocking().exchange(request, List::class.java)

//      Validação
        assertEquals(HttpStatus.OK, response.status)
        assertNotNull(response.body())
        assertEquals(response.body()!!.size, 2)
    }

}