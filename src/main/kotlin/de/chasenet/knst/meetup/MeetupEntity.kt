package de.chasenet.knst.meetup

import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "meetup")
class MeetupEntity(
    @Id
    @Column(name = "id", nullable = false) var id: Int?,
    @Column(name = "active", nullable = false) var active: Boolean,
    @Column(name = "close", nullable = false) var close: Boolean,
    @Column(name = "date") var date: LocalDate,
    @Column(name = "extended_registration") var extendedRegistration: Boolean
)