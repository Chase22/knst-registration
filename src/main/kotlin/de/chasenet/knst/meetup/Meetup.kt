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
    val attendees: Set<Attendee> = emptySet()
) : Serializable {
    constructor(entity: MeetupEntity) : this(
        entity.id!!,
        entity.active,
        entity.closed,
        entity.date,
        entity.extendedRegistration,
        entity.attendees.map(::Attendee).toSet()
    )

    fun toEntity() = MeetupEntity(id, active, closed, date, extendedRegistration)
}

data class NewMeetup(
    val active: Boolean,
    val closed: Boolean,
    val date: LocalDate,
    val extendedRegistration: Boolean
) {
    fun toEntity() = MeetupEntity(null, active, closed, date, extendedRegistration)
}