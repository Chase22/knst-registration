package de.chasenet.testutils

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import java.time.ZonedDateTime

data class Email(
    val subject: String,
    val text: String,
    val to: Value<List<Address>>,
    val from: Value<List<Address>>,
    val date: ZonedDateTime
)

data class Value<T>(
    val value: T
)

data class Address(
    val address: String,
    val name: String
)

val client = HttpClient(CIO) {
    install(Logging)
    install(JsonFeature) {
        serializer = JacksonSerializer {
            registerModule(JavaTimeModule())
            configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        }
    }
}

suspend fun getEmails(): List<Email> {
    return client.request("http://localhost:30201/api/emails")
}

suspend fun clearEmails() {
    return client.delete("http://localhost:30201/api/emails")
}