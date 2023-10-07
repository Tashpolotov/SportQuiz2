package com.relise.opportuapp.fragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.relise.domain.HomeModel

import com.relise.opportuapp.R
import com.relise.opportuapp.databinding.ItemHomeBinding

class HomeAdapter(val onClick:(HomeModel) -> Unit): ListAdapter<HomeModel, HomeAdapter.ViewHolder>(HomeDiff()) {

    private val cardColor = arrayOf(
        R.color.card1,
        R.color.card2,
        R.color.card3,
        R.color.card4,

    )


    inner class ViewHolder(private val binding: ItemHomeBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(model: HomeModel) {
            binding.tvSportName.text = model.sportName
            binding.tvScore.text = model.countTheme
            Glide.with(binding.root)
                .load(model.img)
                .into(binding.img)

            val colorResId = cardColor[adapterPosition % cardColor.size]

            binding.cardView1.setCardBackgroundColor(ContextCompat.getColor(binding.root.context, colorResId))

            itemView.setOnClickListener {
                onClick(model)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemHomeBinding.inflate(LayoutInflater.from(parent.context), parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class HomeDiff: DiffUtil.ItemCallback<HomeModel>() {
    override fun areItemsTheSame(oldItem: HomeModel, newItem: HomeModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: HomeModel, newItem: HomeModel): Boolean {
        return oldItem == newItem
    }

}
