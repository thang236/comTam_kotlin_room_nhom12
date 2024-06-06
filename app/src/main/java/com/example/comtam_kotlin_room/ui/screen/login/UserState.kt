package com.example.comtam_kotlin_room.ui.screen.login

import com.example.comtam_kotlin_room.data.entity.User

data class UserState(
    val users: List<User> = emptyList()
)
