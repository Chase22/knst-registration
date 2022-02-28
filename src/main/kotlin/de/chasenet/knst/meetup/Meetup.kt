package de.chasenet.knst.meetup

import java.io.Serializable
import java.time.LocalDate

data class Meetup(
    val id: Int,
    val active: Boolean,
    val close: Boolean,
    val date: LocalDate,
    val extendedRegistration: Boolean
) : Serializable {
    constructor(entity: MeetupEntity) : this(
        entity.id!!,
        entity.active,
        entity.close,
        entity.date,
        entity.extendedRegistration
    )

    fun toEntity() = MeetupEntity(id, active, close, date, extendedRegistration)
}

data class NewMeetup(
    val active: Boolean,
    val close: Boolean,
    val date: LocalDate,
    val extendedRegistration: Boolean
) {
    fun toEntity() = MeetupEntity(null, active, close, date, extendedRegistration)
}