package com.example.conduit.model

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.sql.Timestamp
import java.time.Instant
import javax.persistence.*

/**
 * @author masker
 * @date 2021/12/5
 */
@Entity(name = "article")
@Cacheable
@EntityListeners(AuditingEntityListener::class)
class Article(
    var title: String,
    var description: String,
    var body: String,
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
) {
    var slug: String = toSlug(title)
    fun rebuildSlug() {
        slug += System.currentTimeMillis().toString()
    }
}

fun toSlug(title: String): String {
    return title.lowercase().replace("[\\&|[\\uFE30-\\uFFA0]|\\’|\\”|\\s\\?\\,\\.]+".toRegex(), "-")
}

