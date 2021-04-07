package br.com.api.person

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class PersonTest {

    @Test
    fun personMustHaveNameAndAge() {

        with(Person("Matheus Teste", 43)) {
            assertTrue(this.name.equals("Matheus Teste"))
            assertTrue(this.age.equals(43))
        }

    }

}