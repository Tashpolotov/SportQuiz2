package com.example.sportquiz.viewmodel

import com.example.domain.HomeModel
import com.example.domain.QuestionsModel
import com.example.domain.QuizModel

data class SportState(

    val home : List<HomeModel> = emptyList(),
    val quiz : List<QuizModel> = emptyList(),
    val questions : List<QuestionsModel> = emptyList(),
)