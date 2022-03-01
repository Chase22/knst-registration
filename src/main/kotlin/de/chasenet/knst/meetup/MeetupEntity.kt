package de.chasenet.knst.meetup

import de.chasenet.knst.attendee.AttendeeEntity
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "meetup")
class MeetupEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false) val id: Int?,

    @Column(name = "active", nullable = false) val active: Boolean,
    @Column(name = "closed", nullable = false) val closed: Boolean,
    @Column(name = "date", nullable = false) val date: LocalDate,
    @Column(name = "extended_registration", nullable = false) val extendedRegistration: Boolean,
    @Column(name = "max_attendees") val maxAttendees: Int?
) {
    @OneToMany(mappedBy = "meetupId", fetch = FetchType.EAGER)
    val attendees: Set<AttendeeEntity> = setOf()
}

