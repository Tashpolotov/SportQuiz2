package com.relise.opportuapp.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.relise.domain.QuestionsModel
import com.relise.opportuapp.R
import com.relise.opportuapp.activity.MainActivity
import com.relise.opportuapp.fragment.adapter.QuestionsAdapter
import com.relise.opportuapp.viewmodel.SportViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.relise.opportuapp.databinding.FragmentQuestionsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuestionsFragment : Fragment() {

    private lateinit var binding: FragmentQuestionsBinding
    private val viewModel by viewModels<SportViewModel>()
    private val adapter = QuestionsAdapter()
    private var isCurrentQuestionAnswered = false
    private var currentQuestionIndex = 0
    private var correctAnswersCount = 0
    private var questions: List<QuestionsModel>? = null
    private val wrongAnswersList = mutableListOf<String>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentQuestionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mainActivity = activity as MainActivity
        val bottomNavigationView = mainActivity.findViewById<BottomNavigationView>(R.id.bottom_nav_view) // Замените R.id.bottom_navigation на ID вашего BottomNavigationView
        bottomNavigationView.visibility = View.GONE // Скрываем BottomNavigationView
        val name = arguments?.getString("name")
        val img = arguments?.getInt("img")



        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.sport.collect {
                Log.d("QuestionsFragment", "Sport data received: $it")
                questions = it.questions.filter { it.nameTheme == name }
                adapter.submitList(listOf(questions?.get(currentQuestionIndex)))
                if (img != null) {
                    binding.img.setImageResource(img)
                }

            }
        }
        viewModel.loadQuestions()

        initAdapter()

    }

    private fun initAdapter() {
        binding.rv.adapter = adapter

        adapter.setOnAnswerClickListener(object : QuestionsAdapter.OnAnswerClickListener {
            override fun onAnswerClick(selectedIndex: Int) {
                isCurrentQuestionAnswered = true

                val currentQuestion = questions?.get(currentQuestionIndex)
                if (currentQuestion != null) {
                    val correctAnswer = currentQuestion.answer[currentQuestion.currentAnswer.toInt()]

                    if (currentQuestion.currentAnswer.toInt() != selectedIndex) {
                        val formattedWrongAnswer = "${currentQuestionIndex + 1}. $correctAnswer"
                        wrongAnswersList.add(formattedWrongAnswer)
                    } else {
                        correctAnswersCount++
                    }
                }
            }
        })

        adapter.clearAnsweredQuestions()
        binding.btnNext.setOnClickListener {
            if (currentQuestionIndex < questions?.size ?: 0 && isCurrentQuestionAnswered) {
                currentQuestionIndex++
                isCurrentQuestionAnswered = false
                adapter.clearAnsweredQuestions()
                updateQuestionCountText()
                if (currentQuestionIndex < questions?.size ?: 0) {
                    adapter.submitList(listOf(questions?.get(currentQuestionIndex)))
                }

                if (currentQuestionIndex == questions?.size ?: 0) {
                    Log.d("QuestionFragment", "Quiz completed. Correct answers: $correctAnswersCount")
                    val name = arguments?.getString("name")

                    val fragment = ResultFragment()
                    val args = Bundle()
                    args.putInt("correctAnswersCount", correctAnswersCount)
                    args.putInt("totalQuestions", questions?.size ?: 0)
                    args.putString("name", name)
                    args.putStringArrayList("wrongAnswersList", ArrayList(wrongAnswersList))
                    fragment.arguments = args

                    parentFragmentManager.beginTransaction()
                        .replace(R.id.fr_container, fragment)
                        .addToBackStack(null)
                        .commit()
                }
            }
        }
    }
    private fun updateQuestionCountText() {
        val currentQuestionNumber = currentQuestionIndex + 1
        val totalQuestions = questions?.size ?: 0
        val questionCountText = "$currentQuestionNumber/$totalQuestions"

    }

}