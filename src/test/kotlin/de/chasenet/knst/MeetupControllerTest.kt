package de.chasenet.knst

import de.chasenet.knst.attendee.AttendeeRepository
import de.chasenet.knst.meetup.Meetup
import de.chasenet.knst.meetup.MeetupController
import de.chasenet.knst.meetup.MeetupRepository
import de.chasenet.knst.meetup.NewMeetup
import de.chasenet.testutils.meetup
import de.chasenet.testutils.setupAttendees
import de.chasenet.testutils.setupMeetups
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.test.context.ActiveProfiles
import java.time.LocalDate

@SpringBootTest
@ActiveProfiles("test")
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

        val meetups = meetupController.getMeetups(null).content

        meetups.size shouldBe testMeetups.size

        meetups.forEach {
            val testMeetup = testMeetups.find { test -> it.id == test.id }!!

            with(it) {
                id shouldBe testMeetup.id
                active shouldBe testMeetup.active
                closed shouldBe testMeetup.closed
                date shouldBe testMeetup.date
                extendedRegistration shouldBe testMeetup.extendedRegistration
                maxAttendees shouldBe testMeetup.maxAttendees
                attendees shouldBe testMeetup.attendees
            }
        }

        meetups.sortedBy { it.id } shouldBe testMeetups.sortedBy { it.id }
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
        with(response.body!!) {
            id shouldBe meetup.id
            active shouldBe meetup.active
            closed shouldBe meetup.closed
            extendedRegistration shouldBe meetup.extendedRegistration
            date shouldBe meetup.date
            maxAttendees shouldBe meetup.maxAttendees
            attendees.sortedBy { it.id } shouldBe meetup.attendees.sortedBy { it.id }
        }
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
            extendedRegistration = false,
            maxAttendees = 50
        )

        val response = meetupController.addNewMeetup(newMeetup)

        response.statusCode shouldBe HttpStatus.CREATED
        with(response.body!!) {
            id shouldBe testMeetups.maxOf(Meetup::id) + 1
            active shouldBe newMeetup.active
            closed shouldBe newMeetup.closed
            extendedRegistration shouldBe newMeetup.extendedRegistration
            date shouldBe newMeetup.date
            maxAttendees shouldBe newMeetup.maxAttendees
        }
    }

    "PUT /api/meetups/{id} should update a given meetup" {
        val meetup = withContext(Dispatchers.IO) {
            meetupRepository.save(meetup().toEntity())
        }.let(::Meetup)

        val updatedMeetup = meetup.copy(closed = true, active = true, maxAttendees = 50)
        meetupController.updateMeeting(updatedMeetup, updatedMeetup.id)

        with(withContext(Dispatchers.IO) {
            meetupRepository.getById(updatedMeetup.id)
        }) {
            id shouldBe updatedMeetup.id
            date shouldBe updatedMeetup.date
            active shouldBe updatedMeetup.active
            closed shouldBe updatedMeetup.closed
            extendedRegistration shouldBe updatedMeetup.extendedRegistration
            maxAttendees shouldBe updatedMeetup.maxAttendees
        }
    }

    "PUT /api/meetups/{id} will reject a body with a wrong id" {
        Array(20) {
            meetup(id = it + 1, date = LocalDate.of(2022, 1, 1).plusMonths(it.toLong()))
        }.toList().forEach { meetupRepository.save(it.toEntity()) }

        meetupController.updateMeeting(meetup(id = 5), 1).statusCode shouldBe HttpStatus.BAD_REQUEST
    }

    "DELETE /api/meetups/{id} should delete a given id" {
        val meetup = meetup()
        val id = withContext(Dispatchers.IO) {
            meetupRepository.save(meetup.toEntity())
        }.id!!

        meetupController.deleteMeetup(id) shouldBe HttpStatus.OK
        withContext(Dispatchers.IO) {
            meetupRepository.existsById(id)
        } shouldBe false
    }
})


