package de.chasenet.knst.attendee

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface AttendeeRepository : JpaRepository<AttendeeEntity, Int> {
    fun findByMeetupId(meetupId: Int, pageable: Pageable): Page<AttendeeEntity>
}