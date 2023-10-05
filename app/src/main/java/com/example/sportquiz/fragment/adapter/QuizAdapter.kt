package com.example.sportquiz.fragment.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.QuizModel
import com.example.sportquiz.R
import com.example.sportquiz.databinding.ItemQuizBinding

class QuizAdapter(val onClick:(QuizModel)->Unit):ListAdapter<QuizModel, QuizAdapter.ViewHolder>(
    QuizDiff()
) {

    private var imageClickListener: OnImageClickListener? = null

    interface OnImageClickListener {
        fun onImageClick(quizModel: QuizModel)
    }
    fun setImageClickListener(listener: OnImageClickListener) {
        imageClickListener = listener
    }

    inner class ViewHolder(private val binding: ItemQuizBinding):RecyclerView.ViewHolder(binding.root) {
        private var isImageClicked = false
        @SuppressLint("NotifyDataSetChanged")
        fun bind(model: QuizModel) {

            binding.tvTestName.text = "Test Name: \n${model.themeName}"
            binding.tvScore.text = "Questions: \n${model.scoreQuestions}"


            if (model.isSelectedColor) {
                binding.img.setColorFilter(
                    ContextCompat.getColor(itemView.context, R.color.red),
                    PorterDuff.Mode.SRC_IN
                )
            } else {
                binding.img.clearColorFilter()
            }

            binding.img.setOnClickListener {
                model.isSelectedColor = !model.isSelectedColor // Обновляем состояние в модели
                imageClickListener?.onImageClick(model)
                notifyDataSetChanged()
                val sharedPreferences = itemView.context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putBoolean("color_${model.themeName}", model.isSelectedColor)
                editor.apply()
            }

            itemView.setOnClickListener {
                onClick(model)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemQuizBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = getItem(position)
        // Восстанавливаем состояние цвета из SharedPreferences
        val sharedPreferences = holder.itemView.context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
        val isSelectedColor = sharedPreferences.getBoolean("color_${model.themeName}", false)

        model.isSelectedColor = isSelectedColor

        holder.bind(model)
    }
}

class QuizDiff:DiffUtil.ItemCallback<QuizModel>() {
    override fun areItemsTheSame(oldItem: QuizModel, newItem: QuizModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: QuizModel, newItem: QuizModel): Boolean {
        return oldItem == newItem
    }

}
