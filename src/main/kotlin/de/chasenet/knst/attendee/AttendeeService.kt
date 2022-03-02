package de.chasenet.knst.attendee

import de.chasenet.knst.meetup.MeetupService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpStatusCodeException

@Service
class AttendeeService(
    private val attendeeRepository: AttendeeRepository,
    private val meetupService: MeetupService
) {
    fun newAttendee(newAttendee: NewAttendee, meetupId: Int): Attendee {
        val meetup = meetupService.getById(meetupId) ?: throw NotFoundException(meetupId)
        if (meetup.maxAttendees != null && meetup.maxAttendees >= meetup.attendees.size)
            throw AttendeeMaximumExceededException()

        return attendeeRepository.save(newAttendee.toEntity(meetupId)).let(::Attendee)
    }

    fun update(attendee: Attendee): Attendee = attendeeRepository.save(attendee.toEntity()).let(::Attendee)

    fun getById(id: Int): Attendee? = attendeeRepository.findById(id).map(::Attendee).orElse(null)

    fun getByMeetup(meetupId: Int, pageable: Pageable): Page<Attendee> {
        if (!meetupService.existsById(meetupId)) throw NotFoundException(meetupId)
        return attendeeRepository.findByMeetupId(meetupId, pageable).map(::Attendee)
    }

    fun deleteById(id: Int) = attendeeRepository.deleteById(id)
}

class NotFoundException(meetupId: Int) :
    HttpStatusCodeException(HttpStatus.NOT_FOUND, "Not meetup with id $meetupId found")

class AttendeeMaximumExceededException : HttpStatusCodeException(HttpStatus.PRECONDITION_FAILED)