package com.fatec.ativ_kmp_valorant.repository

import com.fatec.ativ_kmp_valorant.model.Question

interface QuestionRepository {
    suspend fun getByLessonId(lessonId: Int): List<Question>
    suspend fun getById(id: Int): Question?
}
