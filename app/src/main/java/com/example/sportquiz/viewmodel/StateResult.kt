package com.example.sportquiz.viewmodel

import com.example.domain.User

data class StateResult (
    val user : List<User> = emptyList()
        )