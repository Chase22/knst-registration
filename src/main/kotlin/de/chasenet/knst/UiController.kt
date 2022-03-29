package de.chasenet.knst

import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class UiController {
    @Suppress("SpringMVCViewInspection")
    @GetMapping(value = ["/", "/ui/**"], produces = [MediaType.TEXT_HTML_VALUE])
    fun index(): String = "index"
}
