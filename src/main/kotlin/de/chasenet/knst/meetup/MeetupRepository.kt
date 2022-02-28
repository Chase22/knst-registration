package de.chasenet.knst.meetup

import org.springframework.data.jpa.repository.JpaRepository

interface MeetupRepository : JpaRepository<MeetupEntity, Int>