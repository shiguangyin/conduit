package com.conduit.app.repository

import com.conduit.app.model.Relation
import org.springframework.data.repository.CrudRepository
import javax.transaction.Transactional

/**
 * @author masker
 * @date 2021/12/5
 */
interface RelationRepository : CrudRepository<Relation, Long> {

    fun existsByFromAndToAndType(from: Long, to: Long, type: Int): Boolean

    @Transactional
    fun deleteByFromAndToAndType(from: Long, to: Long, type: Int): Long
}