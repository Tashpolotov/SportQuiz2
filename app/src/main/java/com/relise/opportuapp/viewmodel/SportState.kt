package com.relise.opportuapp.viewmodel

import com.relise.domain.HomeModel
import com.relise.domain.QuestionsModel
import com.relise.domain.QuizModel

data class SportState(

    val home : List<HomeModel> = emptyList(),
    val quiz : List<QuizModel> = emptyList(),
    val questions : List<QuestionsModel> = emptyList(),
)