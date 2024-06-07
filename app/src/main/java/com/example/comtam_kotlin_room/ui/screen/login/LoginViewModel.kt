package com.example.comtam_kotlin_room.ui.screen.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    private val _username = MutableLiveData<String>()
    val username: LiveData<String> = _username

    private val _isAuthenticated = MutableLiveData<Boolean?>()
    val isAuthenticated: LiveData<Boolean?> = _isAuthenticated

    private val _isShowPassword = MutableLiveData<Boolean>()
    val isShowPassword: LiveData<Boolean> = _isShowPassword

    fun login(username: String, password: String) {
        if (username.equals("admin") && password.equals("123")) {
            _isAuthenticated.value = true
        } else {
            _isAuthenticated.value = false
        }
    }

    fun resetAuthenticationState() {
        _isAuthenticated.value = null
    }

}