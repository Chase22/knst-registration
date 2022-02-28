package de.chasenet.knst.attendee

import org.springframework.data.jpa.repository.JpaRepository

interface AttendeeRepository : JpaRepository<AttendeeEntity, Int>