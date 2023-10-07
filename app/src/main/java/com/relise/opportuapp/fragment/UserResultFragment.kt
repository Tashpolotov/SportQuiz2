package com.relise.opportuapp.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.relise.opportuapp.fragment.adapter.RatingAdapter
import com.relise.opportuapp.viewmodel.ResultViewModel
import com.relise.opportuapp.databinding.FragmentUserResultBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserResultFragment : Fragment() {

    private lateinit var binding: FragmentUserResultBinding
    private val adapter = RatingAdapter()
    private val resultViewModel: ResultViewModel by activityViewModels()
    private var passedTests: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeUserResults()
    }

    private fun setupRecyclerView() {
        binding.rv.adapter = adapter
    }

    private fun observeUserResults() {
        viewLifecycleOwner.lifecycleScope.launch {
            resultViewModel.result.collect { userResults ->
                // Логируем результаты
                for (user in userResults.user) {
                    Log.d("UserResultFragment", "Username: ${user.username}, Completed Tests: ${user.complitedTest}, Rating: ${user.rating}")
                }
                // Обновляем адаптер
                passedTests = resultViewModel.getTestsPassed()
                adapter.setPassedTests(passedTests)
                adapter.submitList(userResults.user)

                // В этом блоке вы можете использовать данные пользователя, например:
                // val username = userResults.user.username
                // val completedTests = userResults.user.complitedTest
                // val rating = userResults.user.rating
            }
        }
        resultViewModel.getUsers()
    }
}