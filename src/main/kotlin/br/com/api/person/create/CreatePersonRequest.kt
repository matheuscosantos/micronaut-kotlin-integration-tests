package br.com.api.person.create

import br.com.api.person.Person
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Introspected
class CreatePersonRequest(
    @field:NotNull
    @field:NotBlank
    val name: String,

    @field:NotNull
    @field:Min(18)
    val age: Int,
){
    fun toPerson(): Person {
        return Person(name, age)
    }
}