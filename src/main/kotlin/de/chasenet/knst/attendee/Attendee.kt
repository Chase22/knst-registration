package de.chasenet.knst.attendee

import java.io.Serializable
import java.time.LocalDate

data class Attendee(
    val id: Int,
    val meetupId: Int,
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
        entity.meetupId,
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
        meetupId,
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
    val username: String,
    val firstName: String?,
    val lastName: String?,
    val dateOfBirth: LocalDate?,
    val registryDate: LocalDate,
    val attendeeStatus: String,
    val companies: Int
) {
    fun toEntity(meetupId: Int) = AttendeeEntity(
        null,
        meetupId,
        username,
        firstName,
        lastName,
        dateOfBirth,
        registryDate,
        attendeeStatus,
        companies
    )
}
