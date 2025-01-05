package org.example

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.server.ResponseStatusException

@RestControllerAdvice
class GroupControllerAdvice {
    @ExceptionHandler(GroupNotFoundException::class)
    fun handleGroupNotFoundException(ex: GroupNotFoundException): ResponseStatusException {
        return ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
    }
}