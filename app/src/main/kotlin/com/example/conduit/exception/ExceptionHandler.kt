package com.example.conduit.exception

import com.example.conduit.dto.newValidateExceptionDTO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

/**
 * @author masker
 * @date 2021/12/5
 */
@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(InvalidAuthenticationException::class)
    fun handleValidateException(exception: Exception): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
            .body(newValidateExceptionDTO(exception.message))
    }

}