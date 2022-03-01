package de.chasenet.knst.meetup

import de.chasenet.knst.attendee.Attendee
import java.io.Serializable
import java.time.LocalDate

data class Meetup(
    val id: Int,
    val active: Boolean,
    val closed: Boolean,
    val date: LocalDate,
    val extendedRegistration: Boolean,
    val maxAttendees: Int?,
    val attendees: Set<Attendee> = emptySet()
) : Serializable {
    constructor(entity: MeetupEntity) : this(
        entity.id!!,
        entity.active,
        entity.closed,
        entity.date,
        entity.extendedRegistration,
        entity.maxAttendees,
        entity.attendees.map(::Attendee).toSet()
    )

    fun toEntity() = MeetupEntity(id, active, closed, date, extendedRegistration, maxAttendees)
}

data class NewMeetup(
    val active: Boolean,
    val closed: Boolean,
    val date: LocalDate,
    val extendedRegistration: Boolean,
    val maxAttendees: Int?
) {
    fun toEntity() = MeetupEntity(null, active, closed, date, extendedRegistration, maxAttendees)
}