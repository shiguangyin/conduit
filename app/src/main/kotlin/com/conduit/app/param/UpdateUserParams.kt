package com.conduit.app.param

import com.fasterxml.jackson.annotation.JsonRootName

/**
 * @author masker
 * @date 2021/12/5
 */
@JsonRootName("user")
data class UpdateUserParams(
    val email: String,
    val bio: String?,
    val image: String?,
)