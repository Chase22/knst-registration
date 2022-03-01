package de.chasenet.knst

import de.chasenet.knst.meetup.MeetupService
import io.kotest.core.spec.style.StringSpec
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.Pageable

@SpringBootTest
class KnstRegistrationApplicationTests(meetupService: MeetupService) : StringSpec({
    "should load the application context" {
        println(meetupService.getAll(Pageable.unpaged()).get())
    }
})
