package com.example.conduit.model

import org.springframework.security.core.AuthenticatedPrincipal
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

/**
 * @author masker
 * @date 2021/12/5
 */
@Entity(name = "user")
class User(
    var username: String,
    var email: String,
    var password: String,
    var bio: String = "",
    var image: String? = null,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
): AuthenticatedPrincipal {
    override fun getName(): String {
        return id.toString()
    }

}
