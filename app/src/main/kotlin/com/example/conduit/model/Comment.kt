package com.example.conduit.model

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.sql.Timestamp
import java.time.Instant
import javax.persistence.*

/**
 * @author masker
 * @date 2021/12/11
 */
@Entity(name = "comment")
@EntityListeners(AuditingEntityListener::class)
class Comment(
    var body: String,

    @Column(name = "object_id")
    var objectId: Long,

    @Column(name = "object_type")
    var objectType: Int,

    var author: Long,

    @Column(name = "created_at")
    @CreationTimestamp
    var createdAt: Timestamp = Timestamp.from(Instant.now()),
    @Column(name = "updated_at")
    @UpdateTimestamp
    var updatedAt: Timestamp = Timestamp.from(Instant.now()),

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
)