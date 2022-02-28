package de.chasenet.knst.attendee

import java.io.Serializable
import java.time.LocalDate

data class Attendee(
    val id: Int,
    val meetup: Int,
    val username: String,
    val firstName: String?,
    val lastName: String?,
    val dateOfBirth: LocalDate?,
    val registryDate: LocalDate,
    val attendeeStatus: String,
    val companies: Int
) : Serializable {
    constructor(entity: AttendeeEntity) : this(
        entity.id!!,
        entity.meetup,
        entity.username,
        entity.firstName,
        entity.lastName,
        entity.dateOfBirth,
        entity.registryDate,
        entity.attendeeStatus,
        entity.companies
    )

    fun toEntity() = AttendeeEntity(
        id,
        meetup,
        username,
        firstName,
        lastName,
        dateOfBirth,
        registryDate,
        attendeeStatus,
        companies
    )
}

data class NewAttendee(
    val meetup: Int,
    val username: String,
    val firstName: String?,
    val lastName: String?,
    val dateOfBirth: LocalDate?,
    val registryDate: LocalDate,
    val attendeeStatus: String,
    val companies: Int
) {
    fun toEntity() = AttendeeEntity(
        null,
        meetup,
        username,
        firstName,
        lastName,
        dateOfBirth,
        registryDate,
        attendeeStatus,
        companies
    )
}
