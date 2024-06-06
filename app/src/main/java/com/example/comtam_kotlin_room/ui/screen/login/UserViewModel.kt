package com.example.comtam_kotlin_room.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comtam_kotlin_room.ui.screen.login.UserEvent
import com.example.comtam_kotlin_room.ui.screen.login.UserState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
//
//class UserViewModel(private val dao: UserDao) : ViewModel() {
//    private val userFlow = dao.getAll().stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
//
//    private val _state = MutableStateFlow(UserState())
//    val state = combine(_state, userFlow) { state, users ->
//        state.copy(users = users)
//    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(10000), UserState())
//
//    fun onEvent(event: UserEvent) {
//        when (event) {
//            is UserEvent.DeleteUser -> {
//                viewModelScope.launch {
//                    dao.deleteUser(event.user)
//                }
//            }
//            is UserEvent.InsertUser -> {
//                viewModelScope.launch {
//                    dao.insertUser(event.user)
//                }
//            }
//            is UserEvent.EditUser -> {
//                viewModelScope.launch {
//                    dao.updateUser(event.user)
//                }
//                _state.update {
//                    it.copy(users = it.users.map { user ->
//                        if (user.id == event.user.id) event.user else user
//                    })
//                }
//            }
//        }
//    }
//}
