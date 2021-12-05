package com.example.conduit.model

import javax.persistence.*

/**
 * @author masker
 * @date 2021/12/5
 */
@Entity(name = "follow_relation")
class FollowRelation(
    @Column(name ="user_id")
    var userId: Long,

    @Column(name = "target_user_id")
    var targetUser: Long,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
)