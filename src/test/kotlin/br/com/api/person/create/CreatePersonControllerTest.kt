package br.com.api.person.create

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
internal class CreatePersonControllerTest(
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
    fun `must create a person 18 years old and return Status CREATED`() {
//      Cenário
        val personRequest = CreatePersonRequest("Matheus", 18)

//      Ação
        val request = HttpRequest.POST("/api/v1/persons/", personRequest)
        val response = client.toBlocking().exchange(request, CreatePersonRequest::class.java)

//      Validação
        assertEquals(HttpStatus.CREATED, response.status)
    }


}