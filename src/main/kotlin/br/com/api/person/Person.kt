package br.com.api.person

import io.micronaut.core.annotation.Introspected
import java.time.LocalDateTime
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

@Entity
@Introspected
 class Person{

    @Id
    @GeneratedValue
    lateinit var id: UUID

    @field:NotNull
    var name: String

    @field:NotNull
    @field:Min(18)
    var age: Int

    constructor(id: UUID, name: String, age: Int) : super(){
        this.id = id
        this.name = name
        this.age = age
    }

    constructor(name: String, age: Int) : super(){
        this.name = name
        this.age = age
    }

    val createdAt: LocalDateTime = LocalDateTime.now()
    var updatedAt: LocalDateTime = LocalDateTime.now()

    override fun toString(): String {
        return "${id} - ${name} - ${age}"
    }

}