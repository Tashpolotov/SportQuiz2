package com.example.usecase

import com.example.repository.SportRepository

class SportUseCase(private val repository: SportRepository) {

    operator fun invoke(){
        repository.getHome()
        repository.getQuiz()
        repository.getQuestions()
        repository.getUser()
    }
}