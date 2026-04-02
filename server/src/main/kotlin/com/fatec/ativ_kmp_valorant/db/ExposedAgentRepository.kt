package com.fatec.ativ_kmp_valorant.db

import com.fatec.ativ_kmp_valorant.model.Ability
import com.fatec.ativ_kmp_valorant.repository.AgentRepository
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

class ExposedAgentRepository : AgentRepository {

    private fun ResultRow.toLesson() = Ability(
        id = this[Lessons.id].value,
        courseId = this[Lessons.courseId].value,
        title = this[Lessons.title],
        description = this[Lessons.description],
        order = this[Lessons.order],
//        difficultyLevel = this[Lessons.difficultyLevel]
    )

    override suspend fun getByCourseId(courseId: Int): List<Ability> = newSuspendedTransaction {
        Lessons.selectAll()
            .where { Lessons.courseId eq courseId }
            .orderBy(Lessons.order)
            .map { it.toLesson() }
    }

    override suspend fun getById(id: Int): Ability? = newSuspendedTransaction {
        Lessons.selectAll()
            .where { Lessons.id eq id }
            .map { it.toLesson() }
            .singleOrNull()
    }
}