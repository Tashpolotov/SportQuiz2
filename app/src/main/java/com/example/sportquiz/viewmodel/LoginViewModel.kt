package com.example.sportquiz.viewmodel

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.User
import com.example.repository.SportRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: SportRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val sharedPreferences: SharedPreferences

    private val _authenticationSuccess = MutableLiveData<Boolean>()
    val authenticationSuccess: LiveData<Boolean> = _authenticationSuccess

    private val _authenticationError = MutableLiveData<String>()
    val authenticationError: LiveData<String> = _authenticationError

    init {
        sharedPreferences = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
    }

    fun loginUser(username: String, password: String) {
        viewModelScope.launch {
            val savedUsername = sharedPreferences.getString("username", null)
            val savedPassword = sharedPreferences.getString("password", null)

            if (username == savedUsername && password == savedPassword) {
                // Вход успешен
                val user = User(username = username, complitedTest = "", rating = 0)
                repository.addUser(user)
                _authenticationSuccess.postValue(true)
            } else {
                // Вход не удался
                _authenticationError.postValue("Ошибка аутентификации. Пожалуйста, проверьте логин и пароль.")
            }
        }
    } }



