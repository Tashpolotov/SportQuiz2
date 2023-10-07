package com.relise.opportuapp.viewmodel

import androidx.lifecycle.ViewModel
import com.relise.domain.QuizModel
import com.relise.repository.SportRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SportViewModel @Inject constructor( val repository: SportRepository):ViewModel() {


    private val _sport = MutableStateFlow(SportState())
    val sport : StateFlow<SportState> = _sport.asStateFlow()

    private val quizModels: MutableList<QuizModel> = mutableListOf()
    private val uniqueTestNames: MutableSet<String> = mutableSetOf()


    fun loadQuestions(){
        val questions = repository.getQuestions()
        _sport.value = _sport.value.copy(questions = questions)
    }

    fun loadHome(){
        val home = repository.getHome()
        _sport.value = _sport.value.copy(home = home)
    }

    fun loadQuiz() {
        val quiz = repository.getQuiz()
        _sport.value = _sport.value.copy(quiz = quiz)
    }
    fun setQuizModels(quizModels: List<QuizModel>) {
        for (quizModel in quizModels) {
            if (!uniqueTestNames.contains(quizModel.themeName)) {
                this.quizModels.add(quizModel)
                uniqueTestNames.add(quizModel.themeName)
            }
        }
    }

    fun removeQuizModel(quizModel: QuizModel) {
        if (quizModels.remove(quizModel)) {
            uniqueTestNames.remove(quizModel.themeName)
        }
    }

    fun getQuizModels(): List<QuizModel> {
        val selectedQuizModels = quizModels.filter { it.isSelectedColor }
        return selectedQuizModels
    }


}