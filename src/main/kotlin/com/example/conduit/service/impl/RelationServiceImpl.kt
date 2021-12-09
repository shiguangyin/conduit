package com.example.conduit.service.impl

import com.example.conduit.model.Relation
import com.example.conduit.repository.RelationRepository
import com.example.conduit.service.RelationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author masker
 * @date 2021/12/9
 */
@Service
class RelationServiceImpl @Autowired constructor(
    private val relationRepository: RelationRepository
) : RelationService {
    override fun exist(from: Long, to: Long, type: Int): Boolean {
        return relationRepository.existsByFromAndToAndType(from, to, type)
    }

    override fun addRelation(from: Long, to: Long, type: Int) {
        val relation = Relation(from, to, type)
        relationRepository.save(relation)
    }

    override fun deleteRelation(from: Long, to: Long, type: Int) {
        relationRepository.deleteByFromAndToAndType(from, to, type)
    }
}