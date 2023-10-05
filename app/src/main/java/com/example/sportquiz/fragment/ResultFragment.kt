package com.example.sportquiz.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.sportquiz.R
import com.example.sportquiz.databinding.FragmentResultBinding
import com.example.sportquiz.viewmodel.ResultViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResultFragment : Fragment() {

    private lateinit var binding: FragmentResultBinding
    private val resultViewModel: ResultViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val correctAnswersCount = arguments?.getInt("correctAnswersCount", 0) ?: 0
        val totalQuestions = arguments?.getInt("totalQuestions", 0) ?: 0
        binding.seekBar.isEnabled = false
        val resultText = "Questions $correctAnswersCount/$totalQuestions"
        binding.tvScore.text = resultText
        val wrongAnswersList = arguments?.getStringArrayList("wrongAnswersList")



        if (wrongAnswersList != null) {
            for (i in wrongAnswersList.indices) {
                val wrongAnswer = wrongAnswersList[i]
                Log.d("WrongAnswers", "Неправильный ответ ${i + 1}: $wrongAnswer")

                // Создайте и настройте CardView
                val cardView = when (i) {
                    0 -> binding.card1
                    1 -> binding.card2
                    2 -> binding.card3
                    3 -> binding.card4
                    4 -> binding.card5
                    5 -> binding.card6
                    // Добавьте дополнительные кейсы для других CardView, если это необходимо
                    else -> null
                }

                val textView = when (i) {
                    0 -> binding.tv1
                    1 -> binding.tv2
                    2 -> binding.tv3
                    3 -> binding.tv4
                    4 -> binding.tv5
                    5 -> binding.tv6
                    // Добавьте дополнительные кейсы для других TextView, если это необходимо
                    else -> null
                }

                // Если CardView и TextView существуют, установите текст и сделайте CardView видимым
                if (cardView != null && textView != null) {
                    textView.text = wrongAnswer
                    cardView.visibility = View.VISIBLE
                }
            }
        } else {
            // Если данных нет, скройте все CardView
            binding.card1.visibility = View.GONE
            binding.card2.visibility = View.GONE
            binding.card3.visibility = View.GONE
            binding.card4.visibility = View.GONE
            // Добавьте дополнительные CardView, если это необходимо
        }

        val percentageCorrect = if (totalQuestions > 0) {
            (correctAnswersCount * 100) / totalQuestions
        } else {
            0
        }

        val name = arguments?.getString("name")

        if (correctAnswersCount == totalQuestions) {

            val sharedPreferences = requireContext().getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
            val username = sharedPreferences.getString("username", "По умолчанию")
            Log.d("ResultFragment", "Имя пользователя из SharedPreferences: $username")
            resultViewModel.testsPassed++
     }


// Устанавливаем положение ползунка на основе процента правильных ответов
        binding.seekBar.progress = percentageCorrect

        binding.btnHome.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fr_container, HomeFragment())
                .addToBackStack(null)
                .commit()

        }
        binding.btnRestart.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fr_container, HomeFragment())
                .addToBackStack(null)
                .commit()

        }

    }
}