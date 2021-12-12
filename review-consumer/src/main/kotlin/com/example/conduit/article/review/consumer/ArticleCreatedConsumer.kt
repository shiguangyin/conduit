package com.example.conduit.article.review.consumer

import com.conduit.pb_gen.message.ArticleCreatedMessage
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

/**
 * @author masker
 * @date 2021/12/12
 */
@Component
class ArticleCreatedConsumer {

    private val logger = LoggerFactory.getLogger(ArticleCreatedConsumer::class.java)

    @KafkaListener(id = "article_review_consumer", topics = ["article_created"])
    fun onArticleCreated(data: ByteArray) {
        val msg = ArticleCreatedMessage.parseFrom(data)
        logger.info("consume msg : $msg")
    }
}