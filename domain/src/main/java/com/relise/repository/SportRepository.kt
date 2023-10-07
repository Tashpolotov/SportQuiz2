    package com.relise.repository

    import com.relise.domain.HomeModel
    import com.relise.domain.QuestionsModel
    import com.relise.domain.QuizModel
    import com.relise.domain.User

    interface SportRepository {


        fun getHome():List<HomeModel>
        fun getQuiz():List<QuizModel>

        fun getQuestions():List<QuestionsModel>

        fun getUser():List<User>

        fun addUser(user: User)

        // Если необходимо, добавьте другие методы для работы с пользователями, например, для обновления информации о пользователе
        fun updateUser(user: User)
    }