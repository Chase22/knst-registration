package de.chasenet.knst.meetup

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class MeetupService(
    private val meetupRepository: MeetupRepository
) {
    fun getById(id: Int): Meetup? = meetupRepository.findById(id).map(::Meetup).orElse(null)
    fun getAll(pageable: Pageable): Page<Meetup> = meetupRepository.findAll(pageable).map(::Meetup)
    fun createNew(newMeetup: NewMeetup) = meetupRepository.save(newMeetup.toEntity()).let(::Meetup)
    fun update(meetup: Meetup) = meetupRepository.save(meetup.toEntity()).let(::Meetup)
    fun deleteById(id: Int) = meetupRepository.deleteById(id)
}