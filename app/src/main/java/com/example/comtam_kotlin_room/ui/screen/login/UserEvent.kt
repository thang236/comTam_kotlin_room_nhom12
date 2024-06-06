package com.example.comtam_kotlin_room.ui.screen.login

import com.example.comtam_kotlin_room.data.entity.User

sealed interface UserEvent {
    val user: User

    data class InsertUser(
        override val user: User
    ) : UserEvent

    data class DeleteUser(
        override val user: User
    ) : UserEvent

    data class EditUser(
        override val user: User
    ) : UserEvent
}
