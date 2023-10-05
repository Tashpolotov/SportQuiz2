package com.example.sportquiz.fragment.adapter

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import com.example.domain.User
import com.example.repository.SportRepository
import com.example.sportquiz.R
import com.example.sportquiz.databinding.FragmentRegisterBinding
import com.example.sportquiz.fragment.LoginFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var sharedPreferences: SharedPreferences


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bottomNavigationView = requireActivity().findViewById<BottomNavigationView>(R.id.bottom_nav_view)
        bottomNavigationView.visibility = View.GONE
        sharedPreferences = requireActivity().getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)

        val usernameEditText = binding.usernameEditText
        val passwordEditText = binding.passwordEditText
        val registerButton = binding.registerButton

        registerButton.setOnClickListener {
            // Обработка события нажатия кнопки Зарегистрироваться
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Проверьте, что поля не пустые и выполните сохранение данных пользователя
            if (username.isNotBlank() && password.isNotBlank()) {
                saveUserData(username, password)


                // Добавьте нового пользователя в репозиторий
                navigateToLoginScreen()
            } else {
                // Пользователь не ввел имя пользователя или пароль, предупредите пользователя
            }
        }
    }

    private fun saveUserData(username: String, password: String) {
        val editor = sharedPreferences.edit()
        editor.putString("username", username)
        editor.putString("password", password)
        editor.apply()


    }

    private fun navigateToLoginScreen() {

        val loginFragment = LoginFragment()

        val transaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fr_container, loginFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}