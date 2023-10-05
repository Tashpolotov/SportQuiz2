package com.example.sportquiz.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.example.sportquiz.R
import com.example.sportquiz.databinding.ActivityMainBinding
import com.example.sportquiz.fragment.*
import com.example.sportquiz.viewmodel.ResultViewModel
import com.example.sportquiz.viewmodel.SportViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding
    val viewModel: SportViewModel by viewModels()
    val viewModel1: ResultViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bottomNav()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fr_container, LoginFragment())
            .commit()
    }

    private fun bottomNav() {
        val bottomNav = binding.bottomNavView
        val iconColor = ContextCompat.getColorStateList(this, R.color.bottom_icon_colors)
        bottomNav.itemIconTintList = iconColor


        bottomNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home ->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fr_container, HomeFragment())
                        .addToBackStack(null)
                        .commit()
                    true
                }
                R.id.favourite ->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fr_container, FavouriteFragment())
                        .addToBackStack(null)
                        .commit()
                    true
                }
                R.id.result ->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fr_container, UserResultFragment())
                        .addToBackStack(null)
                        .commit()
                    true
                } else -> false
            }
        }
    }

    override fun onBackPressed() {
        finish()
    }
}