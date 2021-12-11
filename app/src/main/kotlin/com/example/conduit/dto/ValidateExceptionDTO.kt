package com.example.conduit.dto

/**
 * @author masker
 * @date 2021/12/5
 */

data class ValidateExceptionBody(
    val body: List<String?>
)

data class ValidateExceptionDTO(
    val errors: ValidateExceptionBody,
)


fun newValidateExceptionDTO(msg: String?): ValidateExceptionDTO {
    return ValidateExceptionDTO(ValidateExceptionBody(listOf(msg)))
}