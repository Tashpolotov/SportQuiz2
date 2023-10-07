package com.relise.usecase

import com.relise.repository.SportRepository

class SportUseCase(private val repository: SportRepository) {

    operator fun invoke(){
        repository.getHome()
        repository.getQuiz()
        repository.getQuestions()
        repository.getUser()
    }
}