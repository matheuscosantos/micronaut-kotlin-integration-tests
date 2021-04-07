package br.com.api.person

import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import java.util.*

@Repository
interface PersonRepository : JpaRepository<Person, UUID>