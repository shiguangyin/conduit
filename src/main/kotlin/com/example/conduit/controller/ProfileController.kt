package com.example.conduit.controller

import com.example.conduit.dto.ProfileDTO
import com.example.conduit.exception.ResourceNotFoundException
import com.example.conduit.model.FollowRelation
import com.example.conduit.model.User
import com.example.conduit.repository.FollowRepository
import com.example.conduit.repository.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

/**
 * @author masker
 * @date 2021/12/5
 */
@RestController
@RequestMapping("/api/profiles/{username}")
class ProfileController @Autowired constructor(
    private val userRepository: UserRepository,
    private val followRepository: FollowRepository,

    ) {

    private val logger = LoggerFactory.getLogger(ProfileController::class.java)

    @GetMapping("")
    fun getProfile(
        @PathVariable("username") username: String,
        @AuthenticationPrincipal currentUser: User?
    ): ResponseEntity<Any> {
        val user = userRepository.findUserByUsername(username) ?: throw ResourceNotFoundException()
        var following = false
        currentUser?.id?.let { currentUserId ->
            followRepository.findFollowRelationByUserIdAndTargetUser(currentUserId, user.id!!)?.let {
                following = true
            }
        }

        val dto = ProfileDTO(
            user.username,
            user.bio,
            image = user.image,
            following = following,
        )
        return ResponseEntity.ok(mapOf("profile" to dto))
    }

    @PostMapping("/follow")
    fun follow(
        @PathVariable("username") username: String,
        @AuthenticationPrincipal currentUser: User
    ): ResponseEntity<Any> {
        val user = userRepository.findUserByUsername(username) ?: throw ResourceNotFoundException()
        val currentUserId = currentUser.id ?: throw ResourceNotFoundException()
        var relation = followRepository.findFollowRelationByUserId(currentUserId)
        if (relation == null) {
            relation = FollowRelation(currentUserId, user.id!!)
            followRepository.save(relation)
        }
        val dto = ProfileDTO(
            user.username,
            user.bio,
            true,
            user.image,
        )
        return ResponseEntity.ok("profile" to dto)
    }


    @DeleteMapping("/follow")
    fun unfollow(
        @PathVariable("username") username: String,
        @AuthenticationPrincipal currentUser: User,
    ): ResponseEntity<Any> {
        val user = userRepository.findUserByUsername(username) ?: throw ResourceNotFoundException()
        val currentUserId = currentUser.id ?: throw ResourceNotFoundException()
        val rows = followRepository.deleteByUserIdAndTargetUser(currentUserId, user.id!!)
        logger.info("delete rows : $rows")
        logger
        val dto = ProfileDTO(
            user.username,
            user.bio,
            false,
            user.image,
        )
        return ResponseEntity.ok("profile" to dto)
    }

}