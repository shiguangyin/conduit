package com.conduit.app.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

/**
 * @author masker
 * @date 2021/12/5
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
class ResourceNotFoundException: Exception("resource not found") {
}