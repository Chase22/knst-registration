package de.chasenet.knst.infrastructure

import org.springframework.http.ResponseEntity

fun <T> T?.toResponseEntity(): ResponseEntity<T> =
    this?.let { ResponseEntity.ok(it) } ?: ResponseEntity.notFound().build()