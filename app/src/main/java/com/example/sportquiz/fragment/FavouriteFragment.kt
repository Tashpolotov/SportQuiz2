package com.example.sportquiz.fragment

import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.domain.QuizModel
import com.example.sportquiz.R
import com.example.sportquiz.databinding.FragmentFavouriteBinding
import com.example.sportquiz.fragment.adapter.LikeAdapter
import com.example.sportquiz.viewmodel.SportViewModel
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouriteFragment : Fragment() {

    private lateinit var binding: FragmentFavouriteBinding
    private val viewModel: SportViewModel by activityViewModels()
    private val adapter = LikeAdapter(this::onClick)

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavouriteBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        loadQuizModels()
    }

    private fun initAdapter() {
        binding.rv.adapter = adapter

        adapter.setImageClickListener(object : LikeAdapter.OnImageClickListener {
            override fun onImageClick(quizModel: QuizModel) {
                val alertDialogBuilder = AlertDialog.Builder(requireContext())

                alertDialogBuilder.setTitle(getString(R.string.delete_item))
                alertDialogBuilder.setMessage(getString(R.string.are_sure_delete))

                alertDialogBuilder.setPositiveButton(getString(R.string.del)) { dialog, which ->
                    adapter.removeItem(quizModel)
                    viewModel.removeQuizModel(quizModel)
                    dialog.dismiss()

                    // После удаления элемента из адаптера, обновляем данные в SharedPreferences
                    val updatedQuizModels = adapter.currentList.toList()
                    save(updatedQuizModels) // Обновляем данные в SharedPreferences

                    Log.d(
                        "FavouriteFragment",
                        "Item deleted successfully: ${quizModel.toString()}"
                    )
                }

                alertDialogBuilder.setNegativeButton(getString(R.string.cansel)) { dialog, which ->
                    dialog.dismiss()
                }

                val alertDialog = alertDialogBuilder.create()
                alertDialog.show()
            }
        })

        val quizModels = viewModel.getQuizModels()


        // Проверьте, что selectedQuizModel не равен null
        if (quizModels.isNotEmpty()) {
            adapter.submitList(quizModels)
            save(quizModels)

        } else {
            save(quizModels)
        }
    }

    private fun onClick(model: QuizModel) {

    }

    private fun save(quizModels: List<QuizModel>) {
        sharedPreferences = requireContext().getSharedPreferences("my_pref", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        // Преобразование списка QuizModel в строку с использованием Gson
        val gson = Gson()
        val quizModelsJson = gson.toJson(quizModels)

        // Сохранение строки в SharedPreferences
        editor.putString("quizModelsKey", quizModelsJson)
        val isSaved = editor.commit() // Используем commit() и сохраняем результат в переменной

        if (isSaved) {
            Log.d(
                "FavouriteFragment",
                "Data saved successfully: $quizModelsJson"
            ) // Добавлен лог с данными
        } else {
            Log.e("FavouriteFragment", "Failed to save data") // Добавлен лог при ошибке сохранения
        }
    }

    private fun loadQuizModels() {
        sharedPreferences = requireContext().getSharedPreferences("my_pref", Context.MODE_PRIVATE)
        val quizModelsJson = sharedPreferences.getString("quizModelsKey", null)

        try {
            // Десериализация строки в список QuizModel с использованием Gson
            val gson = Gson()
            val quizModelsType = object : TypeToken<List<QuizModel>>() {}.type
            val loadedQuizModels = gson.fromJson<List<QuizModel>>(quizModelsJson, quizModelsType)

            if (loadedQuizModels != null) {
                Log.d("FavouriteFragment", "Data loaded successfully: $loadedQuizModels")
                // Обновляем данные в адаптере RecyclerView
                adapter.submitList(loadedQuizModels)

            } else {
                Log.e("FavouriteFragment", "No data found or failed to load data")
            }
        } catch (e: JsonSyntaxException) {
            Log.e("FavouriteFragment", "Error loading quiz models", e)
        }
    }
}