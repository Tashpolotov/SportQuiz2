package com.relise.opportuapp.viewmodel

import com.relise.domain.User

data class StateResult (
    val user : List<User> = emptyList()
        )