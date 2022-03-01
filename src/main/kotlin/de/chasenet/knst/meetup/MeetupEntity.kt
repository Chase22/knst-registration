package de.chasenet.knst.meetup

import de.chasenet.knst.attendee.AttendeeEntity
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "meetup")
class MeetupEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false) var id: Int?,

    @Column(name = "active", nullable = false) var active: Boolean,
    @Column(name = "closed", nullable = false) var closed: Boolean,
    @Column(name = "date") var date: LocalDate,
    @Column(name = "extended_registration") var extendedRegistration: Boolean
) {
    @OneToMany(mappedBy = "meetupId", fetch = FetchType.EAGER)
    val attendees: Set<AttendeeEntity> = setOf()
}

