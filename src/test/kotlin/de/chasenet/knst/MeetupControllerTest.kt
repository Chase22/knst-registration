package de.chasenet.knst

import de.chasenet.knst.attendee.Attendee
import de.chasenet.knst.attendee.AttendeeEntity
import de.chasenet.knst.attendee.AttendeeRepository
import de.chasenet.knst.meetup.Meetup
import de.chasenet.knst.meetup.MeetupController
import de.chasenet.knst.meetup.MeetupRepository
import de.chasenet.knst.meetup.NewMeetup
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import java.time.LocalDate

@SpringBootTest
class MeetupControllerTest(
    meetupRepository: MeetupRepository,
    attendeeRepository: AttendeeRepository,
    meetupController: MeetupController
) : StringSpec({
    beforeTest {
        withContext(Dispatchers.IO) {
            attendeeRepository.deleteAll()
            meetupRepository.deleteAll()
        }
    }

    "GET /api/meetups with no data should return and empty list" {
        val response = meetupController.getMeetups(null)

        response.size shouldBe 0
    }

    "GET /api/meetups should return all meetups with no pagination" {
        val testMeetups = setupMeetups(meetupRepository)

        meetupController.getMeetups(null).content shouldBe testMeetups
    }

    "GET /api/meetups should return paged meetups with pagination" {
        val meetups = setupMeetups(meetupRepository).chunked(10)

        meetupController.getMeetups(PageRequest.of(0, 10)).content shouldBe meetups[0]
        meetupController.getMeetups(PageRequest.of(1, 10)).content shouldBe meetups[1]
    }

    "Get /api/meetups/{id} returns the correct entity" {
        val testMeetups = setupMeetups(meetupRepository)
        val id = testMeetups.first().id
        val attendees = setupAttendees(attendeeRepository, id)
        val meetup = testMeetups.find { it.id == id }!!.copy(attendees = attendees.toSet())

        val response = meetupController.getMeetupById(id)
        response.statusCode shouldBe HttpStatus.OK
        response.body shouldBe meetup
    }

    "Get /api/meetups/{id} returns 404 for an unknown id" {
        setupMeetups(meetupRepository)

        val response = meetupController.getMeetupById(999)
        response.statusCode shouldBe HttpStatus.NOT_FOUND
        response.body shouldBe null
    }

    "POST /api/meetups should create a new meetup" {
        val testMeetups = setupMeetups(meetupRepository)
        val newMeetup = NewMeetup(
            active = false,
            closed = true,
            date = LocalDate.of(2021, 12, 12),
            extendedRegistration = false
        )

        val response = meetupController.addNewMeetup(newMeetup)

        response.statusCode shouldBe HttpStatus.CREATED
        with(response.body!!) {
            id shouldBe testMeetups.maxOf(Meetup::id) + 1
            active shouldBe newMeetup.active
            closed shouldBe newMeetup.closed
            extendedRegistration shouldBe newMeetup.extendedRegistration
            date shouldBe newMeetup.date
        }
    }

    "PUT /api/meetups/{id} should update a given meetup" {
        val meetup = withContext(Dispatchers.IO) {
            meetupRepository.save(meetup().toEntity())
        }.let(::Meetup)

        val updatedMeetup = meetup.copy(closed = true, active = true)
        meetupController.updateMeeting(updatedMeetup, updatedMeetup.id)

        with(withContext(Dispatchers.IO) {
            meetupRepository.getById(updatedMeetup.id)
        }) {
            id shouldBe updatedMeetup.id
            date shouldBe updatedMeetup.date
            active shouldBe updatedMeetup.active
            closed shouldBe updatedMeetup.closed
            extendedRegistration shouldBe updatedMeetup.extendedRegistration
        }
    }

    "PUT /api/meetups/{id} will reject a body with a wrong id" {
        testMeetups().forEach { meetupRepository.save(it.toEntity()) }

        meetupController.updateMeeting(meetup(id = 5), 1).statusCode shouldBe HttpStatus.BAD_REQUEST
    }
})

fun setupAttendees(repository: AttendeeRepository, meetupId: Int): List<Attendee> {
    return Array(10) {
        repository.save(AttendeeEntity(it, meetupId, it.toString(), null, null, null, LocalDate.now(), "ATTENDING", 0))
    }.map(::Attendee).toList()
}

fun setupMeetups(repository: MeetupRepository): List<Meetup> =
    testMeetups().map { repository.save(it.toEntity()) }.map(::Meetup)


fun testMeetups() = Array(20) {
    meetup(id = it + 1, date = LocalDate.of(2022, 1, 1).plusMonths(it.toLong()))
}.toList()

fun meetup(
    id: Int = 1,
    active: Boolean = false,
    closed: Boolean = false,
    date: LocalDate = LocalDate.of(2022, 2, 2),
    extendedRegistration: Boolean = false
) = Meetup(id, active, closed, date, extendedRegistration)

