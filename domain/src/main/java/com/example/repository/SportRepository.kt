    package com.example.repository

    import com.example.domain.HomeModel
    import com.example.domain.QuestionsModel
    import com.example.domain.QuizModel
    import com.example.domain.User

    interface SportRepository {


        fun getHome():List<HomeModel>
        fun getQuiz():List<QuizModel>

        fun getQuestions():List<QuestionsModel>

        fun getUser():List<User>

        fun addUser(user: User)

        // Если необходимо, добавьте другие методы для работы с пользователями, например, для обновления информации о пользователе
        fun updateUser(user: User)
    }