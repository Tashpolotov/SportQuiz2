package com.relise.domain

data class QuestionsModel (
        val nameTheme:String,
        val questions:String,
        val answer:List<String>,
        val currentAnswer:String
        )