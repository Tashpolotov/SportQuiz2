package com.example.sportquiz.fragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.QuizModel
import com.example.sportquiz.databinding.ItemLikeBinding

class LikeAdapter(val onClick:(QuizModel)->Unit): ListAdapter<QuizModel, LikeAdapter.ViewHolder>(LikeDiff()) {

    private var imageClickListener: OnImageClickListener? = null

    interface OnImageClickListener {
        fun onImageClick(quizModel: QuizModel)
    }
    fun setImageClickListener(listener: LikeAdapter.OnImageClickListener) {
        imageClickListener = listener
    }

    inner class ViewHolder(private val binding: ItemLikeBinding): RecyclerView.ViewHolder(binding.root) {
        private var isImageClicked = false

        fun bind(model: QuizModel) {
            binding.tvTestName.text = "Test Name: \n${model.sportName}"
            binding.tvScore.text = "Questions: \n${model.scoreQuestions}"

            binding.img.setOnClickListener {

                imageClickListener?.onImageClick(model)

            }


            itemView.setOnClickListener {
                onClick(model)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemLikeBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    fun removeItem(quizModel: QuizModel) {
        val position = currentList.indexOf(quizModel)
        if (position != RecyclerView.NO_POSITION) {
            val newList = currentList.toMutableList()
            newList.removeAt(position)
            submitList(newList)
        }
    }
}

class LikeDiff: DiffUtil.ItemCallback<QuizModel>() {
    override fun areItemsTheSame(oldItem: QuizModel, newItem: QuizModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: QuizModel, newItem: QuizModel): Boolean {
        return oldItem == newItem
    }

}
