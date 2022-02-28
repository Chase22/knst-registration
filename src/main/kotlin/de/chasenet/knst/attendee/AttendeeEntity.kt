package de.chasenet.knst.attendee

import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "attendees")
class AttendeeEntity(
    @Id
    @Column(name = "id", nullable = false)
    val id: Int? = null,

    @Column(name = "meetup_id") val meetup: Int,
    @Column(name = "username", nullable = false) val username: String,
    @Column(name = "first_name") val firstName: String?,
    @Column(name = "last_name") val lastName: String?,
    @Column(name = "date_of_birth") val dateOfBirth: LocalDate?,
    @Column(name = "registry_date", nullable = false) val registryDate: LocalDate,
    @Column(name = "attendee_status") val attendeeStatus: String,
    @Column(name = "companies") val companies: Int,
)