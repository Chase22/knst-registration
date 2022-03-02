package de.chasenet.knst.attendee

import de.chasenet.knst.infrastructure.toResponseEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class AttendeeController(
    private val attendeeService: AttendeeService
) {

    @GetMapping("/meetups/{meetupId}/attendee")
    fun getAttendees(@PathVariable meetupId: Int, pageable: Pageable?): Page<Attendee> =
        attendeeService.getByMeetup(meetupId, pageable ?: Pageable.unpaged())

    @PostMapping("/meetups/{meetupId}/attendee")
    fun addNewAttendee(@PathVariable meetupId: Int, @RequestBody newAttendee: NewAttendee): ResponseEntity<Attendee> =
        ResponseEntity.status(HttpStatus.CREATED).body(attendeeService.newAttendee(newAttendee, meetupId))

    @GetMapping("/attendee/{id}")
    fun getAttendeeById(@PathVariable id: Int) = attendeeService.getById(id).toResponseEntity()

    @PutMapping("/attendee/{id}")
    fun updateMeeting(@RequestBody attendee: Attendee, @PathVariable id: Int) =
        if (attendee.id != id) {
            ResponseEntity.badRequest().build()
        } else {
            attendeeService.update(attendee).toResponseEntity()
        }

    @DeleteMapping("/attendee/{id}")
    fun deleteAttendee(@PathVariable id: Int): HttpStatus {
        attendeeService.deleteById(id)
        return HttpStatus.OK
    }
}