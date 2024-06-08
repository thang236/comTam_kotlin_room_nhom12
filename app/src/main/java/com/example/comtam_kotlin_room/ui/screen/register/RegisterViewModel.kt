package com.example.comtam_kotlin_room.ui.screen.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comtam_kotlin_room.data.DAO.UserDAO
import com.example.comtam_kotlin_room.data.entity.User
import kotlinx.coroutines.launch

class RegisterViewModel(private val dao: UserDAO) : ViewModel() {

    private val _username = MutableLiveData<String>()
    val username: LiveData<String> = _username

    private val _isAuthenticated = MutableLiveData<Boolean?>()
    val isAuthenticated: LiveData<Boolean?> = _isAuthenticated

    private val _isShowPassword = MutableLiveData<Boolean>()
    val isShowPassword: LiveData<Boolean> = _isShowPassword

    private val _isShowRePassword = MutableLiveData<Boolean>()
    val isRepassVisible: LiveData<Boolean> = _isShowRePassword

    fun register(user: User) {
        viewModelScope.launch {
            dao.insertUser(user)
        }
    }
    fun resetAuthenticationState() {
        _isAuthenticated.value = null
    }

}