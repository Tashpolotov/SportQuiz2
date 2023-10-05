package com.example.sportquiz.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.domain.QuizModel
import com.example.sportquiz.R
import com.example.sportquiz.activity.MainActivity
import com.example.sportquiz.databinding.FragmentQuizBinding
import com.example.sportquiz.fragment.adapter.QuizAdapter
import com.example.sportquiz.viewmodel.SportViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class QuizFragment : Fragment() {

    private lateinit var binding: FragmentQuizBinding
    private val adapter = QuizAdapter(this::onClick)
    private val viewModel: SportViewModel by activityViewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initBtn()
        (activity as? MainActivity)?.binding?.bottomNavView?.visibility = View.VISIBLE
    }

    private fun initBtn() {
        val bottomnav = requireActivity().findViewById<BottomNavigationView>(R.id.bottom_nav_view)
        binding.nescroll.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->

            if(scrollY > oldScrollY){
                bottomnav.visibility = View.GONE
            } else if(scrollY < oldScrollY) {
                bottomnav.visibility = View.VISIBLE
            }
        }
    }

    @SuppressLint("SuspiciousIndentation")
    private fun initAdapter() {
        val name = arguments?.getString("name")
        val img = arguments?.getInt("img")

        // Устанавливаем слушатель клика до загрузки данных и установки адаптера
        adapter.setImageClickListener(object : QuizAdapter.OnImageClickListener {
            override fun onImageClick(quizModel: QuizModel) {
                val isSelected = quizModel.isSelectedColor
                // Вызываем метод setQuizModels вашей ViewModel для сохранения списка QuizModel
                viewModel.setQuizModels(listOf(quizModel))
                // Передаем одиночный объект в виде списка
                Log.d("QuizFragment", "Selected Quiz Model: $quizModel") // Добавляем лог при клике

                if (isSelected) {
                    // Если элемент ранее был выбран (имел красный цвет) и пользователь отменяет выбор,
                    // проверьте, есть ли еще элементы с красным цветом в адаптере
                    val hasRedItems = adapter.currentList.any { it.isSelectedColor }
                    if (!hasRedItems) {

                        viewModel.setQuizModels(viewModel.getQuizModels())
                        Log.d("QuizFragment", "Removed Quiz Model: $quizModel")
                    }
                }
            }
        })

        binding.rv.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            // Переместите логику загрузки данных внутрь этой корутины
            viewModel.loadQuiz()

            // Переместите логику обновления RecyclerView сюда
            viewModel.sport.collect { quizState ->
                val testName = quizState.quiz.filter { it.sportName == name }
                adapter.submitList(testName)
                Log.d("QuizFragment", "Quiz data loaded and RecyclerView updated") // Добавляем лог после обновления RecyclerView
            }
        }
    }

    private fun onClick(model:QuizModel) {

        val img = arguments?.getInt("img")
        val fragment = QuestionsFragment()
        val bundle = Bundle()
        bundle.putString("name", model.themeName)
        if (img != null) {
            bundle.putInt("img", img)
        }
        fragment.arguments = bundle
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fr_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}
