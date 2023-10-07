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
        val isEmulator = Build.FINGERPRINT.contains("generic") || Build.MODEL.contains("Emulator") || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic"))
        if (isEmulator) {

        }
         else{
            val urlToOpen = "" // Замените этот URL на нужный вам URL

            openWebView(urlToOpen)
        }



        supportFragmentManager.beginTransaction()
            .replace(R.id.fr_container, HomeFragment())
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

    fun openWebView(url: String?) {
        try {
            if (!url.isNullOrEmpty()) {
                val intent = Intent(this, WV::class.java).apply {
                    putExtra("url", url)
                }
                startActivity(intent)
            } else {
                val remoteConfig = FirebaseRemoteConfig.getInstance()
                val configSettings = FirebaseRemoteConfigSettings.Builder()
                    .setMinimumFetchIntervalInSeconds(3600) // Настройте интервал получения данных по своему усмотрению
                    .build()
                remoteConfig.setConfigSettingsAsync(configSettings)
                remoteConfig.fetch().addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        remoteConfig.activate().addOnCompleteListener { activationTask ->
                            if (activationTask.isSuccessful) {
                                val urlFromRemoteConfig = remoteConfig.getString("url") // Замените "url" на ваш ключ
                                if (!urlFromRemoteConfig.isNullOrEmpty()) {
                                    // Сохраните URL в SharedPreferences
                                    val sharedPreferences = getSharedPreferences("pref", Context.MODE_PRIVATE)
                                    val editor = sharedPreferences.edit()
                                    editor.putString("saved_url", urlFromRemoteConfig)
                                    editor.apply()
                                    Log.d("FirebaseConfig", "URL saved in SharedPreferences: $urlFromRemoteConfig")

                                    // Затем откройте WebView с этим URL
                                    val intent = Intent(this, WV::class.java).apply {
                                        putExtra("url", urlFromRemoteConfig)
                                    }
                                    startActivity(intent)
                                } else {
                                    // Ошибка: URL из Firebase Remote Config пустой
                                    Log.d("FirebaseConfig", "URL from Firebase Remote Config is empty.")
                                    // Здесь вы можете показать заглушку или выполнить другие действия в случае пустого URL
                                }
                            } else {
                                // Ошибка: Не удалось активировать Firebase Remote Config
                                Log.e("FirebaseConfig", "Failed to activate Firebase Remote Config: ${activationTask.exception}")
                                // Здесь вы можете показать заглушку или выполнить другие действия в случае ошибки активации
                            }
                        }
                    } else {
                        // Ошибка: Не удалось получить Firebase Remote Config
                        Log.e("FirebaseConfig", "Failed to fetch Firebase Remote Config: ${task.exception}")
                        // Здесь вы можете показать заглушку или выполнить другие действия в случае ошибки получения данных
                    }
                }
            }
        } catch (e: Exception) {
            // Ошибка: Произошла общая ошибка, которую нужно обработать
            Log.e("FirebaseConfig", "An error occurred: ${e.message}")
            // Здесь вы можете показать заглушку или выполнить другие действия в случае общей ошибки
        }
}

    override fun onBackPressed() {
        finish()
    }
}