package de.chasenet.knst

import de.chasenet.knst.attendee.Attendee
import de.chasenet.knst.attendee.AttendeeRepository
import de.chasenet.knst.attendee.NewAttendee
import de.chasenet.knst.meetup.Meetup
import de.chasenet.knst.meetup.MeetupRepository
import de.chasenet.knst.meetup.NewMeetup
import java.time.LocalDate

fun setupAttendees(repository: AttendeeRepository, meetupId: Int): List<Attendee> {
    return Array(10) {
        attendee(id = it + 1, username = it.toString()).toNewAttendee().toEntity(meetupId)
    }.toList().let { repository.saveAll(it) }.map(::Attendee)
}

fun setupAttendee(repository: AttendeeRepository, meetupId: Int): Attendee =
    repository.save(attendee(id = 1, meetupId = meetupId, username = "Username").toEntity()).let(::Attendee)

fun setupMeetups(repository: MeetupRepository): List<Meetup> =
    Array(20) {
        meetup(id = it + 1, date = LocalDate.of(2022, 1, 1).plusMonths(it.toLong())).toNewMeetup().toEntity()
    }.toList().let { repository.saveAll(it) }.map(::Meetup)

fun attendee(
    id: Int = 1,
    meetupId: Int = 1,
    companies: Int = 0,
    username: String = "Username",
    firstName: String? = null,
    lastName: String? = null,
    attendeeStatus: String = "ATTENDING",
    registryDate: LocalDate = LocalDate.now(),
    dateOfBirth: LocalDate? = null,
) = Attendee(id, meetupId, username, firstName, lastName, dateOfBirth, registryDate, attendeeStatus, companies)

fun meetup(
    id: Int = 1,
    active: Boolean = false,
    closed: Boolean = false,
    date: LocalDate = LocalDate.of(2022, 2, 2),
    extendedRegistration: Boolean = false,
    maxAttendees: Int? = null
) = Meetup(id, active, closed, date, extendedRegistration, maxAttendees)

fun Meetup.toNewMeetup(): NewMeetup = NewMeetup(
    active, closed, date, extendedRegistration, maxAttendees
)

fun Attendee.toNewAttendee() =
    NewAttendee(username, firstName, lastName, dateOfBirth, registryDate, attendeeStatus, companies)