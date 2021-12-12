package com.conduit.app.model

import org.hibernate.annotations.CreationTimestamp
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.sql.Timestamp
import java.time.Instant
import javax.persistence.*

/**
 * @author masker
 * @date 2021/12/5
 */
@Entity(name = "relation")
@EntityListeners(AuditingEntityListener::class)
class Relation(
    @Column(name = "from_id")
    var from: Long,

    @Column(name = "to_id")
    var to: Long,

    @Column(name = "relation_type")
    var type: Int,

    @Column(name = "created_at")
    @CreationTimestamp
    var createdAt: Timestamp = Timestamp.from(Instant.now()),

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0
)