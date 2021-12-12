package com.conduit.app.service.impl

import com.conduit.app.model.Relation
import com.conduit.app.repository.RelationRepository
import com.conduit.app.service.RelationService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service

/**
 * @author masker
 * @date 2021/12/9
 */
@Service
class RelationServiceImpl @Autowired constructor(
    private val relationRepository: RelationRepository,
    private val redisTemplate: RedisTemplate<String, String>,
) : RelationService {

    private val logger = LoggerFactory.getLogger(RelationServiceImpl::class.java)

    override fun exist(from: Long, to: Long, type: Int): Boolean {
        return relationRepository.existsByFromAndToAndType(from, to, type)
    }

    override fun addRelation(from: Long, to: Long, type: Int) {
        val relation = Relation(from, to, type)
        relationRepository.save(relation)
        redisTemplate.opsForValue().increment(counterKey(from, type), 1)
        redisTemplate.opsForValue().increment(reverseCounterKey(to, type), 1)
    }

    override fun deleteRelation(from: Long, to: Long, type: Int) {
        relationRepository.deleteByFromAndToAndType(from, to, type)
        redisTemplate.opsForValue().decrement(counterKey(from, type), 1)
        redisTemplate.opsForValue().decrement(reverseCounterKey(to, type), 1)
    }

    override fun getRelationCount(from: Long, type: Int): Long {
        val value = redisTemplate.opsForValue().get(counterKey(from, type))
        return value?.toLong() ?: 0
    }

    override fun getRelationReverseCount(to: Long, type: Int): Long {
        val value = redisTemplate.opsForValue().get(reverseCounterKey(to, type))
        return value?.toLong() ?: 0
    }


    private fun counterKey(from: Long, type: Int): String {
        return "relation#$type#$from"
    }

    private fun reverseCounterKey(to: Long, type: Int): String {
        return "relation_rev#$type#$to"
    }
}