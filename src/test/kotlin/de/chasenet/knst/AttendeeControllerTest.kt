package de.chasenet.knst

import de.chasenet.knst.attendee.*
import de.chasenet.knst.meetup.Meetup
import de.chasenet.knst.meetup.MeetupRepository
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("test")
class AttendeeControllerTest(
    meetupRepository: MeetupRepository,
    attendeeRepository: AttendeeRepository,
    attendeeController: AttendeeController
) : StringSpec({
    beforeTest {
        withContext(Dispatchers.IO) {
            attendeeRepository.deleteAll()
            meetupRepository.deleteAll()
        }
    }

    "GET /api/meetups/{id}/attendees should return all attendees with no pagination" {
        val meetup = setupMeetup(meetupRepository)
        val attendees = setupAttendees(attendeeRepository, meetup.id)

        attendeeController.getAttendees(
            meetup.id,
            null
        ).content.sortedBy { it.id } shouldBe attendees.sortedBy { it.id }
    }

    "GET /api/meetups/{id}/attendees should return paged attendees with pagination" {
        val meetupId = setupMeetup(meetupRepository).id
        val attendees = setupAttendees(attendeeRepository, meetupId).chunked(5)

        attendeeController.getAttendees(meetupId, PageRequest.of(0, 5)).content shouldBe attendees[0]
        attendeeController.getAttendees(meetupId, PageRequest.of(1, 5)).content shouldBe attendees[1]
    }

    "GET /api/meetups/{id}/attendees should return 404 for an unknown meetup id" {
        val meetupId = setupMeetup(meetupRepository).id
        setupAttendees(attendeeRepository, meetupId)

        shouldThrow<NotFoundException> {
            attendeeController.getAttendees(-1, null)
        }
    }

    "Get /api/attendees/{id} returns the correct entity" {
        val meetupId = setupMeetup(meetupRepository).id
        val attendee = setupAttendees(attendeeRepository, meetupId).first()
        val id = attendee.id

        val response = attendeeController.getAttendeeById(id)
        response.statusCode shouldBe HttpStatus.OK
        with(response.body!!) {
            id shouldBe attendee.id
            meetupId shouldBe attendee.meetupId
            username shouldBe attendee.username
            firstName shouldBe attendee.firstName
            lastName shouldBe attendee.lastName
            dateOfBirth shouldBe attendee.dateOfBirth
            registryDate shouldBe attendee.registryDate
            attendeeStatus shouldBe attendee.attendeeStatus
            companies shouldBe attendee.companies
        }
    }

    "Get /api/attendees/{id} returns 404 for an unknown id" {
        val meetupId = setupMeetup(meetupRepository).id
        setupAttendees(attendeeRepository, meetupId)

        val response = attendeeController.getAttendeeById(-1)
        response.statusCode shouldBe HttpStatus.NOT_FOUND
        response.body shouldBe null
    }

    "POST /api/meetups/{id}/attendees" {
        val meetupId = setupMeetup(meetupRepository).id
        val newAttendee = attendeeController.addNewAttendee(
            meetupId,
            attendee(meetupId = meetupId).let {
                NewAttendee(
                    it.username,
                    it.firstName,
                    it.lastName,
                    it.dateOfBirth,
                    it.registryDate,
                    it.attendeeStatus,
                    it.companies
                )
            }
        ).body!!

        withContext(Dispatchers.IO) {
            attendeeRepository.findByMeetupId(meetupId, Pageable.unpaged())
        }.content.map(::Attendee) shouldBe listOf(newAttendee)
    }

    "POST /api/meetups/{id}/attendees rejects a new attendee if the maximum attendee is exceeded" {
        val meetupId = withContext(Dispatchers.IO) {
            meetupRepository.save(meetup(maxAttendees = 5).toEntity()).id!!
        }

        shouldThrow<AttendeeMaximumExceededException> {
            attendeeController.addNewAttendee(
                meetupId,
                attendee(meetupId = meetupId).let {
                    NewAttendee(
                        it.username,
                        it.firstName,
                        it.lastName,
                        it.dateOfBirth,
                        it.registryDate,
                        it.attendeeStatus,
                        it.companies
                    )
                }
            )
        }
    }
    "PUT /api/attendees/{id} should update a given attendee" {
        val meetupId = setupMeetup(meetupRepository).id
        val attendee = setupAttendee(attendeeRepository, meetupId)

        val updatedAttendee = attendee.copy(attendeeStatus = "NOT_ATTENDING")
        attendeeController.updateMeeting(updatedAttendee, attendee.id).body shouldBe updatedAttendee
    }

    "PUT /api/attendees/{id} will reject a body with a wrong id" {
        val meetupId = setupMeetup(meetupRepository).id
        val attendee = setupAttendee(attendeeRepository, meetupId)

        val updatedAttendee = attendee.copy(attendeeStatus = "NOT_ATTENDING")
        attendeeController.updateMeeting(updatedAttendee, -1).statusCode shouldBe HttpStatus.BAD_REQUEST
    }

    "DELETE /api/attendees/{id} should delete a given id" {
        val meetupId = setupMeetup(meetupRepository).id
        val attendee = setupAttendee(attendeeRepository, meetupId)

        attendeeController.deleteAttendee(attendee.id) shouldBe HttpStatus.OK
        withContext(Dispatchers.IO) {
            attendeeRepository.existsById(attendee.id)
        } shouldBe false
    }
})

private suspend fun setupMeetup(meetupRepository: MeetupRepository) =
    withContext(Dispatchers.IO) {
        meetupRepository.save(meetup().toEntity())
    }.let(::Meetup)


