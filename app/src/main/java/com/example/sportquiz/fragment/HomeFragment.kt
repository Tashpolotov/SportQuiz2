package com.example.sportquiz.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.domain.HomeModel
import com.example.sportquiz.R
import com.example.sportquiz.databinding.FragmentHomeBinding
import com.example.sportquiz.fragment.adapter.HomeAdapter
import com.example.sportquiz.viewmodel.SportViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding:FragmentHomeBinding
    private val adapter = HomeAdapter(this::onClick)
    private val viewModel by viewModels<SportViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        val bottomNavigationView = requireActivity().findViewById<BottomNavigationView>(R.id.bottom_nav_view)
        bottomNavigationView.visibility = View.VISIBLE
    }

    private fun initAdapter() {
        binding.rv1.adapter = adapter
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.sport.collect{
                adapter.submitList(it.home)
            }
        }
        viewModel.loadHome()
    }

    private fun onClick(model:HomeModel) {
        val fragment = QuizFragment()
        val bundle = Bundle()
        bundle.putString("name", model.sportName)
        bundle.putInt("img", model.img)
        fragment.arguments = bundle
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fr_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}