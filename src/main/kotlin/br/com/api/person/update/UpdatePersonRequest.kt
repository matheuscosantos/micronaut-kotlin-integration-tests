package br.com.api.person.update

import br.com.api.person.Person
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Introspected
class UpdatePersonRequest(
    @field:NotNull
    @field:NotBlank
    val name: String,

    @field:NotNull
    @field:Min(1)
    val age: Int,
){
    fun toPerson(): Person {
        return Person(name, age)
    }
}