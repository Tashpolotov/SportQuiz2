package com.relise.opportuapp.activity

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.relise.opportuapp.R
import com.relise.opportuapp.databinding.ActivitySplashBinding
import com.relise.opportuapp.fragment.HomeFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.lottieAnim.alpha = 0f
        binding.lottieAnim.animate().setDuration(1500).alpha(1f).withEndAction {
            val isEmulator =
                Build.FINGERPRINT.contains("generic") || Build.MODEL.contains("Emulator") || (Build.BRAND.startsWith(
                    "generic"
                ) && Build.DEVICE.startsWith("generic"))
            if (isEmulator) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                val urlToOpen = "" // Замените этот URL на нужный вам URL

                openWebView(urlToOpen)
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
                            remoteConfig.fetchAndActivate().addOnCompleteListener() { task ->
                                if (task.isSuccessful) {
                                    val url = remoteConfig.getString("url")  // Замените "url" на ваш ключ
                                    if (!url.isNullOrEmpty()) {
                                        // Сохраните URL в SharedPreferences
                                        val sharedPreferences = getSharedPreferences("pref", Context.MODE_PRIVATE)
                                        val editor = sharedPreferences.edit()
                                        editor.putString("saved_url", url)
                                        editor.apply()
                                        Log.d("FirebaseConfig", "URL saved in SharedPreferences: $url")

                                        // Затем откройте WebView с этим URL
                                        val intent = Intent(this, WV::class.java).apply {
                                            putExtra("url", url)
                                        }
                                        startActivity(intent)
                                    } else {
                                        val intent = Intent(this, MainActivity::class.java)
                                        startActivity(intent)
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }