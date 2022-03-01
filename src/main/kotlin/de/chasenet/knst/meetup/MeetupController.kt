package de.chasenet.knst.meetup

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
@RequestMapping("/api/meetups")
class MeetupController(
    private val meetupService: MeetupService
) {

    @GetMapping
    fun getMeetups(pageable: Pageable?): Page<Meetup> = meetupService.getAll(pageable ?: Pageable.unpaged())

    @GetMapping("/{id}")
    fun getMeetupById(@PathVariable id: Int) = meetupService.getById(id).toResponseEntity()

    @PostMapping
    fun addNewMeetup(@RequestBody newMeetup: NewMeetup) =
        ResponseEntity.status(HttpStatus.CREATED).body(meetupService.createNew(newMeetup))

    @PutMapping("/{id}")
    fun updateMeeting(@RequestBody meetup: Meetup, @PathVariable id: Int) =
        if (meetup.id != id) {
            ResponseEntity.badRequest().build()
        } else {
            meetupService.update(meetup).toResponseEntity()
        }

    @DeleteMapping("/{id}")
    fun deleteMeetup(@PathVariable id: Int): HttpStatus {
        meetupService.deleteById(id)
        return HttpStatus.OK
    }
}