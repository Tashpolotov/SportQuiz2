package com.relise.domain

data class QuizModel(
    val Img:Int,
    val sportName:String,
    val themeName:String,
    val scoreQuestions:String,
    var isSelectedColor: Boolean = false
)
