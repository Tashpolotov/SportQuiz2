/*
    package com.example.sportquiz.fragment

    import android.content.Context
    import android.content.SharedPreferences
    import android.os.Bundle
    import androidx.fragment.app.Fragment
    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import androidx.fragment.app.viewModels
    import com.example.domain.User
    import com.example.repository.SportRepository
    import com.example.sportquiz.R
    import com.example.sportquiz.databinding.FragmentLoginBinding
    import com.example.sportquiz.fragment.adapter.RegisterFragment
    import com.example.sportquiz.viewmodel.LoginViewModel
    import com.google.android.material.bottomnavigation.BottomNavigationView
    import dagger.hilt.android.AndroidEntryPoint
    import javax.inject.Inject

    @AndroidEntryPoint
    class LoginFragment : Fragment() {

        private lateinit var binding: FragmentLoginBinding
        private lateinit var sharedPreferences: SharedPreferences

        private val loginViewModel: LoginViewModel by viewModels()

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            // Inflate the layout for this fragment
            binding = FragmentLoginBinding.inflate(inflater, container, false)
            return binding.root
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            val bottomNavigationView = requireActivity().findViewById<BottomNavigationView>(R.id.bottom_nav_view)
            bottomNavigationView.visibility = View.GONE
            sharedPreferences = requireActivity().getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)

            val usernameEditText = binding.usernameEditText
            val passwordEditText = binding.passwordEditText
            val loginButton = binding.loginButton
            val registerButton = binding.registerButton

            loginButton.setOnClickListener {
                val username = usernameEditText.text.toString()
                val password = passwordEditText.text.toString()

                loginViewModel.loginUser(
                    username,
                    password
                )
            }

            loginViewModel.authenticationSuccess.observe(viewLifecycleOwner) { isSuccess ->
                if (isSuccess) {
                    // Вход успешен
                    // Выполните необходимые действия после успешной аутентификации
                    // Например, перейдите на другой экран
                    val user = User(username = usernameEditText.text.toString(), complitedTest = "", rating = 0)
                    val transaction = requireActivity().supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.fr_container, HomeFragment())
                    transaction.addToBackStack(null)
                    transaction.commit()
                }
            }

            registerButton.setOnClickListener {
                // Обработка события нажатия кнопки Зарегистрироваться
                // Переключение на фрагмент для регистрации
                val transaction = requireActivity().supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fr_container, RegisterFragment())
                transaction.addToBackStack(null)
                transaction.commit()
            }
        }
    }*/
