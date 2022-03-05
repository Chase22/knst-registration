package de.chasenet.knst

import io.kotest.core.spec.style.StringSpec
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("test")
class KnstRegistrationApplicationTests : StringSpec({
    "should load the application context" {}
})
