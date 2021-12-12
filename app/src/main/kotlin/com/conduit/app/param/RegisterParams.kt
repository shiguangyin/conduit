package com.conduit.app.param

import com.fasterxml.jackson.annotation.JsonRootName

/**
 * @author masker
 * @date 2021/12/5
 */
@JsonRootName("user")
data class RegisterParams(
    val username: String,
    val email: String,
    val password: String,
)