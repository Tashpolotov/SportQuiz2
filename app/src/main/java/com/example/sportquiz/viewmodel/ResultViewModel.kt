    package com.example.sportquiz.viewmodel

    import android.util.Log
    import androidx.lifecycle.ViewModel
    import com.example.domain.User
    import com.example.repository.SportRepository
    import dagger.hilt.android.lifecycle.HiltViewModel
    import kotlinx.coroutines.flow.MutableStateFlow
    import kotlinx.coroutines.flow.StateFlow
    import javax.inject.Inject

    @HiltViewModel
    class ResultViewModel @Inject constructor(val repository: SportRepository):ViewModel() {

        var testsPassed: Int = 0

        private val _result = MutableStateFlow(StateResult())
        val result: StateFlow<StateResult> = _result


        fun getUsers() {
            val user = repository.getUser()
            _result.value = _result.value.copy(user = user)
            Log.d("ResultViewModel", "Users retrieved - Count: ${user.size}")

        }

        @JvmName("getTestsPassed1")
        fun getTestsPassed(): Int {
            return testsPassed
        }
    }