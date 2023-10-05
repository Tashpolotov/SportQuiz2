    package com.example.sportquiz.fragment.adapter

    import android.util.Log
    import android.view.LayoutInflater
    import android.view.ViewGroup
    import androidx.recyclerview.widget.DiffUtil
    import androidx.recyclerview.widget.ListAdapter
    import androidx.recyclerview.widget.RecyclerView
    import com.example.domain.User
    import com.example.sportquiz.databinding.ItemResultBinding

    class RatingAdapter : ListAdapter<User, RatingAdapter.ViewHolder>(RatinDiff()) {
        private var passedTests: Int = 0

        inner class ViewHolder(private val binding: ItemResultBinding) : RecyclerView.ViewHolder(binding.root) {
            fun bind(model: User) {
                binding.tvName.text = model.username
                binding.tvRating.text = "Пройдено тестов: $passedTests"


                Log.d("RatingAdapter", "Item bound - Username: ${model.username}, Completed Test: ${model.complitedTest}, Rating: ${model.rating}")
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(ItemResultBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(getItem(position))
        }

        fun setPassedTests(passedTests: Int) {
            this.passedTests = passedTests
            notifyDataSetChanged()
        }
    }

    class RatinDiff : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }
