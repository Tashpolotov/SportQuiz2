package com.relise.opportuapp.activity

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.relise.opportuapp.fragment.*
import com.relise.opportuapp.viewmodel.ResultViewModel
import com.relise.opportuapp.viewmodel.SportViewModel
import com.relise.opportuapp.R
import com.relise.opportuapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    val viewModel: SportViewModel by viewModels()
    val viewModel1: ResultViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bottomNav()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fr_container, HomeFragment())
            .commit()

    }

    private fun bottomNav() {
        val bottomNav = binding.bottomNavView
        val iconColor = ContextCompat.getColorStateList(this, R.color.bottom_icon_colors)
        bottomNav.itemIconTintList = iconColor


        bottomNav.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fr_container, HomeFragment())
                        .addToBackStack(null)
                        .commit()
                    true
                }
                R.id.favourite -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fr_container, FavouriteFragment())
                        .addToBackStack(null)
                        .commit()
                    true
                }
                else -> false
            }
        }
}

    override fun onBackPressed() {
        finish()
    }
}